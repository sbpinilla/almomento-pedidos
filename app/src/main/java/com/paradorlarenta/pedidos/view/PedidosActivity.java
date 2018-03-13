package com.paradorlarenta.pedidos.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemPedido;
import com.paradorlarenta.pedidos.conexion.ApiUtils;
import com.paradorlarenta.pedidos.conexion.SOService;
import com.paradorlarenta.pedidos.models.FiltroModel;
import com.paradorlarenta.pedidos.models.PedidoModel;
import com.paradorlarenta.pedidos.models.RegistrarModel;
import com.shawnlin.numberpicker.NumberPicker;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidosActivity extends AppCompatActivity {


    private static final String LOG_ACTIVITY = "PedidosActivity";
    private RVAdapterItemPedido adapter;
    private List<PedidoModel> pedidoModelList;
    private Activity activity;
    private Double dTotal;
    private SOService apiService;
    private MaterialDialog  mProgress;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.rcv_pedidos_activity)
    RecyclerView rvPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        ButterKnife.bind(this);
        activity = this;

        SharedPreferences sharedPref = getSharedPreferences(
                "SharedPreferencesPedidos", Context.MODE_PRIVATE);
        String apiIP = sharedPref.getString("apiIP", "");
        apiService = ApiUtils.getSOService(apiIP);

        setupToolbar("",true);
        setupDataList();
        setupRVPedidos();

    }

    private void initDialog() {

        mProgress =  new MaterialDialog.Builder(activity)
                .title("Registrando")
                .content("")
                .progress(true, 0)
                .progressIndeterminateStyle(true).show();
    }

    private void setupRVPedidos() {
        //Obtener datos de los servicios disponibles
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvPedidos.getContext(),
                llm.getOrientation());
        rvPedidos.addItemDecoration(dividerItemDecoration);


        // rvServicios.setHasFixedSize(true);
        rvPedidos.setLayoutManager(llm);

        CallBackItemPedido callBackItemPedido = new CallBackItemPedido() {
            @Override
            public void OnCallbackTouchContainer(PedidoModel pedidoModel) {

            }
        };

        adapter = new RVAdapterItemPedido(pedidoModelList, this, callBackItemPedido);
        rvPedidos.setAdapter(adapter);

        updateListProductosModel();
    }

    private void updateListProductosModel() {
        adapter.updateRVAdapterItemPedido(pedidoModelList);
    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListProductosModel();
    }

    private void setupToolbar(String s, boolean b) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(s);
        getSupportActionBar().setDisplayHomeAsUpEnabled(b);
    }

    private void setupDataList() {

        pedidoModelList = new ArrayList<>();
        dTotal = 0.0;
        SharedPreferences sharedPref = getSharedPreferences(
                "SharedPreferencesPedidos", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String dataCarrito = sharedPref.getString("dataCarrito", "");
        if (!dataCarrito.equals("")) {
            Type type = new TypeToken<List<PedidoModel>>() {
            }.getType();
            pedidoModelList = gson.fromJson(dataCarrito, type);

            for (PedidoModel pm:pedidoModelList) {

                dTotal = dTotal +(pm.getCantidad() * pm.getProductoModel().getValorProducto());
            }

            String strTotal = String.format(("$ "+String.format( "%,.2f", dTotal)));

            setupToolbar("Total: "+strTotal,true);

        }

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pedidos, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_pedidos_cancelar) {


            new MaterialDialog.Builder(this)
                    .title("Cancelar pedido")
                    .content("Realmente desea cancelar el pedido")
                    .positiveText("Cancelar")
                    .onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                            SharedPreferences sharedPref = getSharedPreferences(
                                    "SharedPreferencesPedidos", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("dataCarrito", "");
                            editor.commit();

                            finish();
                        }
                    })
                    .show();


        } else if (id == R.id.menu_pedidos_confirmar){

            //TODO: eNVIAR PEDIDO AL SERVIDOR


            final MaterialDialog dialog = new MaterialDialog.Builder(activity)
                    .title("Confirmar")
                    .theme(Theme.LIGHT)
                    .customView(R.layout.dialog_pedir, true)
                    .show();

            View view = dialog.getCustomView();

            Button btnPedir = view.findViewById(R.id.btn_confirmar_dialog_pedir);
            final NumberPicker numberPicker =view.findViewById(R.id.number_picker_dialog_pedir);
            final EditText edtDescripcion = view.findViewById(R.id.edt_descripcion_dialog_pedir);


            btnPedir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Integer iMesa=numberPicker.getValue();
                    String descripcion = edtDescripcion.getText().toString();

                    final SharedPreferences sharedPref = getSharedPreferences(
                            "SharedPreferencesPedidos", Context.MODE_PRIVATE);
                    Gson gson = new Gson();
                    String dataCarrito = sharedPref.getString("dataCarrito", "");
                    Type type = new TypeToken<List<PedidoModel>>() {
                    }.getType();
                    List<PedidoModel> pedidoModels = gson.fromJson(dataCarrito, type);

                    if(pedidoModels.size() !=0){

                        RegistrarModel registrarModel = new RegistrarModel();
                        registrarModel.setMesa(iMesa);
                        registrarModel.setDescripcion(descripcion);
                        registrarModel.setListPedidos(pedidoModels);
                        registrarModel.setTotal(dTotal.toString());

                        dialog.dismiss();
                        initDialog();
                        apiService.ApiRegistrarPedido(registrarModel).enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                mProgress.dismiss();

                                Log.d(LOG_ACTIVITY,"onResponse");

                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("dataCarrito", "");
                                editor.commit();

                                Toast.makeText(getBaseContext(),"Pedido Registrado !",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(activity, FiltrosActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();


                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                mProgress.dismiss();
                                Log.d(LOG_ACTIVITY,"onFailure");
                                Toast.makeText(getBaseContext(),"So se pudo registrar el pedido !",Toast.LENGTH_LONG).show();

                            }
                        });

                    }else{
                        // no hay productos en el pedido
                        dialog.dismiss();
                        Toast.makeText(getBaseContext(),"No hay productos en el pedido",Toast.LENGTH_LONG).show();
                    }


                }
            });



        }

        return super.onOptionsItemSelected(item);
    }


}
