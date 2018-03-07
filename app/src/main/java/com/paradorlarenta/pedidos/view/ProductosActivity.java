package com.paradorlarenta.pedidos.view;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemProducto;
import com.paradorlarenta.pedidos.models.PedidoModel;
import com.paradorlarenta.pedidos.models.ProductoModel;
import com.github.juanlabrador.badgecounter.BadgeCounter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductosActivity extends AppCompatActivity {

    private static final String LOG_ACTIVITY = "ProductosActivity";
    private RVAdapterItemProducto adapter;
    private List<ProductoModel> productoModelList;
    private Activity activity;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.rcv_productos_activity)
    RecyclerView rvProductos;

    @BindString(R.string.str_productos)
    String strTitulos;

    @BindView(R.id.search_view)
    MaterialSearchView searchView;

    //MaterialSearchView searchView = (MaterialSearchView) findViewById(R.id.search_view);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        ButterKnife.bind(this);
        activity = this;
        productoModelList = new ArrayList<>();
        setupToolbar(strTitulos);
        setupSearchView();

    }

    private void setupSearchView() {


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic

                Log.d(LOG_ACTIVITY, "setOnQueryTextListener");
                Log.d(LOG_ACTIVITY, "setOnQueryTextListener:" + query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Do some magic

                Log.d(LOG_ACTIVITY, "onQueryTextChange");
                Log.d(LOG_ACTIVITY, "onQueryTextChange:" + newText);


                newText = newText.toLowerCase();
                ArrayList<ProductoModel> newList = new ArrayList<>();

                for (ProductoModel channel : productoModelList) {
                    String channelName = channel.getNombreProducto().toLowerCase();
                    if (channelName.contains(newText)) {
                        newList.add(channel);
                    }
                }
                adapter.setFilter(newList);

                return false;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                Log.d(LOG_ACTIVITY, "onSearchViewShown");
                //adapter.getFilter().filter("");
                updateListProductosModel();
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                Log.d(LOG_ACTIVITY, "onSearchViewClosed");
                //adapter.getFilter().filter("");
                updateListProductosModel();
            }
        });

    }

    public void setupToolbar(String tittle) {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        setupRVProductos();

    }

    private void setupRVProductos() {

        //Obtener datos de los servicios disponibles
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        // rvServicios.setHasFixedSize(true);
        rvProductos.setLayoutManager(llm);


        CallBackItemProducto callBackItemProducto = new CallBackItemProducto() {
            @Override
            public void OnCallbackTouchContainer(final ProductoModel productoModel) {


                final MaterialDialog dialog = new MaterialDialog.Builder(activity)
                        .title(productoModel.getNombreProducto())
                        .theme(Theme.LIGHT)
                        .customView(R.layout.dialog_producto, true)
                        .show();

                View view = dialog.getCustomView();

                final TextView txtCantidad = view.findViewById(R.id.cantidad_dialog_producto);
                TextView txtValorProducto = view.findViewById(R.id.precio_dialog_producto);

                txtValorProducto.setText(("$ "+String.format( new Locale("es_CO"),"%,.2f", productoModel.getValorProducto())));

                SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String dataCarrito = sharedPref.getString("dataCarrito", "");
                Log.d(LOG_ACTIVITY, "dataCarrito : " + dataCarrito);
                if (!dataCarrito.equals("")) {
                    Log.d(LOG_ACTIVITY, "dataCarrito no es null ");
                    Type type = new TypeToken<List<PedidoModel>>() {
                    }.getType();
                    List<PedidoModel> pedidoModels = gson.fromJson(dataCarrito, type);

                    Boolean existe = false;
                    PedidoModel productoModel1 = null;
                    for (PedidoModel pm : pedidoModels) {
                        if (pm.getProductoModel().getIdProducto() == productoModel.getIdProducto()) {
                            existe = true;
                            productoModel1 = pm;

                        }
                    }

                    if (existe) {
                        Log.d(LOG_ACTIVITY, "existe ");
                        txtCantidad.setText("" + productoModel1.getCantidad());
                    } else {
                        Log.d(LOG_ACTIVITY, " no existe ");
                        txtCantidad.setText("1");
                    }

                } else {
                    Log.d(LOG_ACTIVITY, " no existe es vacio ");
                    txtCantidad.setText("1");

                }


                Button btnMas = view.findViewById(R.id.btn_sumar_dialog_producto);
                Button btnMenos = view.findViewById(R.id.btn_restar_dialog_producto);
                Button btnAgregar = view.findViewById(R.id.btn_agregar_dialog_producto);


                btnMas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int cantidad = Integer.parseInt(txtCantidad.getText().toString());
                        ++cantidad;
                        txtCantidad.setText(cantidad + "");

                    }
                });

                btnMenos.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        int cantidad = Integer.parseInt(txtCantidad.getText().toString());

                        if (cantidad > 1) {
                            --cantidad;
                        }
                        txtCantidad.setText(cantidad + "");

                    }
                });

                btnAgregar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //TODO: Agreagr logica de carrito de compras

                        int cantidad = Integer.parseInt(txtCantidad.getText().toString());

                        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                        Gson gson = new Gson();

                        String dataCarrito = sharedPref.getString("dataCarrito", "");

                        PedidoModel pedidoModel = new PedidoModel(productoModel, cantidad);


                        if (dataCarrito.equals("")) {

                            List<PedidoModel> pedidoModels = new ArrayList<>();

                            pedidoModels.add(pedidoModel);
                            String data = gson.toJson(pedidoModels);

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("dataCarrito", data);
                            editor.commit();

                        } else {

                            Type type = new TypeToken<List<PedidoModel>>() {
                            }.getType();
                            List<PedidoModel> pedidoModels = gson.fromJson(dataCarrito, type);
                            // validar si el producto ya esta en el carrito

                            Boolean existe = false;
                            PedidoModel productoModel1 = null;
                            for (PedidoModel pm : pedidoModels) {
                                if (pm.getProductoModel().getIdProducto() == productoModel.getIdProducto()) {
                                    existe = true;
                                    productoModel1 = pm;

                                }
                            }

                            if (existe) {
                                productoModel1.setCantidad(cantidad);
                            } else {
                                pedidoModels.add(pedidoModel);
                            }


                            String data = gson.toJson(pedidoModels);

                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("dataCarrito", data);
                            editor.commit();

                        }


                        dataCarrito = sharedPref.getString("dataCarrito", "");
                        invalidateOptionsMenu();
                        dialog.dismiss();

                        //log
                        Type type = new TypeToken<List<PedidoModel>>() {
                        }.getType();
                        List<PedidoModel> pedidoModels = gson.fromJson(dataCarrito, type);

                        for (PedidoModel pm : pedidoModels) {
                            Log.d(LOG_ACTIVITY, "Producto:" + pm.getProductoModel().getNombreProducto());
                            Log.d(LOG_ACTIVITY, "cantidad:" + pm.getCantidad());
                        }

                    }
                });

            }
        };

        adapter = new RVAdapterItemProducto(productoModelList, this, callBackItemProducto);
        rvProductos.setAdapter(adapter);

        updateListProductosModel();
    }


    private void updateListProductosModel() {


        List<ProductoModel> lisTem = new ArrayList<>();
        productoModelList = new ArrayList<>();
        ProductoModel pm1 = new ProductoModel(1, "Producto 1", 20000.0, "");
        ProductoModel pm2 = new ProductoModel(2, "Producto 2", 10000.0, "");
        ProductoModel pm3 = new ProductoModel(3, "Producto 3", 30000.0, "");
        ProductoModel pm4 = new ProductoModel(4, "Producto 4", 40000.0, "");
        ProductoModel pm5 = new ProductoModel(5, "Producto 5", 90000.0, "");

        lisTem.add(pm1);
        lisTem.add(pm2);
        lisTem.add(pm3);
        lisTem.add(pm4);
        lisTem.add(pm5);

        productoModelList = lisTem;
        adapter.updateRVAdapterItemProducto(lisTem);

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListProductosModel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pedidos, menu);

        int carrito = 0;
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String dataCarrito = sharedPref.getString("dataCarrito", "");

        if (!dataCarrito.equals("")) {
            Type type = new TypeToken<List<PedidoModel>>() {
            }.getType();
            List<PedidoModel> pedidoModels = gson.fromJson(dataCarrito, type);
            carrito = pedidoModels.size();
        }


        // Create a condition (hide it if the count == 0)
        //  if (carrito > 0) {
        BadgeCounter.update(this,
                menu.findItem(R.id.menu_carrito_compras),
                R.drawable.ic_action_shopping_cart,
                BadgeCounter.BadgeColor.RED,
                carrito);
        //} else {
        // BadgeCounter.hide(menu.findItem(R.id.menu_carrito_compras));
        //}

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

}
