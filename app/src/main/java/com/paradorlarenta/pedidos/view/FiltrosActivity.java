package com.paradorlarenta.pedidos.view;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemFiltro;
import com.paradorlarenta.pedidos.models.FiltroModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FiltrosActivity extends AppCompatActivity {


    private static final String LOG_ACTIVITY = "FiltrosActivity";
    private RVAdapterItemFiltro adapter;
    private List<FiltroModel> filtroModelList;
    private Activity activity;


    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.rcv_filtros_activity)
    RecyclerView rvFiltros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        ButterKnife.bind(this);
        filtroModelList = new ArrayList<>();
        activity = this;
        setupToolbar("Bienvenidos");
        setupRVFiltros();

    }

    @Override
    protected void onResume() {
        super.onResume();

        updateListFiltroModel();

    }

    private void setupRVFiltros() {


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        rvFiltros.setLayoutManager(gridLayoutManager);

        CallBackItemFiltro callBackItemFiltro = new CallBackItemFiltro() {
            @Override
            public void OnCallbackTouchContainer(FiltroModel filtroModel) {

                startActivity(new Intent(activity,ProductosActivity.class));

            }
        };

        adapter = new RVAdapterItemFiltro(filtroModelList,activity,callBackItemFiltro);
        rvFiltros.setAdapter(adapter);
        updateListFiltroModel();
    }

    private void updateListFiltroModel() {

        filtroModelList.clear();
        filtroModelList = new ArrayList<>();

        List<FiltroModel> listTem = new ArrayList<>();

        FiltroModel filtroModel = new FiltroModel(1,"Filtro 1");
        FiltroModel filtroModel2 = new FiltroModel(2,"Filtro 2");
        FiltroModel filtroModel3 = new FiltroModel(3,"Filtro 3");
        FiltroModel filtroModel4 = new FiltroModel(4,"Filtro 4");

        listTem.add(filtroModel);
        listTem.add(filtroModel2);
        listTem.add(filtroModel3);
        listTem.add(filtroModel4);
        filtroModelList=listTem;

        adapter.updateRVAdapterItemFiltro(filtroModelList);

    }

    private void setupToolbar(String s) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(s);
    }


}
