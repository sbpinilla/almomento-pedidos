package com.almomento.pedidos.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.almomento.pedidos.R;
import com.almomento.pedidos.callbacks.CallBackItemProducto;
import com.almomento.pedidos.models.ProductoModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductosActivity extends AppCompatActivity {


    private RVAdapterItemProducto adapter;
    private List<ProductoModel> productoModelList;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.rcv_productos_activity)
    RecyclerView rvProductos;

    @BindString(R.string.str_productos)
    String strTitulos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        ButterKnife.bind(this);

        productoModelList = new ArrayList<>();
        setupToolbar(strTitulos);
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


        CallBackItemProducto callBackItemProducto =  new CallBackItemProducto() {
            @Override
            public void OnCallbackTouchContainer(ProductoModel productoModel) {

            }
        };

        adapter = new RVAdapterItemProducto( productoModelList,this,callBackItemProducto );
        rvProductos.setAdapter(adapter);

        updateListProductosModel();
    }


    private void updateListProductosModel() {


        List<ProductoModel> lisTem = new ArrayList<>();

        ProductoModel pm1 = new ProductoModel("Producto 1",20000.0,"");
        ProductoModel pm2 = new ProductoModel("Producto 2",10000.0,"");
        ProductoModel pm3 = new ProductoModel("Producto 3",30000.0,"");
        ProductoModel pm4 = new ProductoModel("Producto 4",40000.0,"");
        ProductoModel pm5 = new ProductoModel("Producto 5",90000.0,"");

        lisTem.add(pm1);
        lisTem.add(pm2);
        lisTem.add(pm3);
        lisTem.add(pm4);
        lisTem.add(pm5);

        adapter.updateRVAdapterItemProducto(lisTem);

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListProductosModel();
    }


}
