package com.paradorlarenta.pedidos.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemFiltro;
import com.paradorlarenta.pedidos.conexion.ApiUtils;
import com.paradorlarenta.pedidos.conexion.SOService;
import com.paradorlarenta.pedidos.models.FiltroModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltrosActivity extends AppCompatActivity {


    private static final String LOG_ACTIVITY = "FiltrosActivity";
    private RVAdapterItemFiltro adapter;
    private List<FiltroModel> filtroModelList;
    private Activity activity;
    private SOService apiService;

    @BindView(R.id.tool_bar)
    Toolbar toolbar;

    @BindView(R.id.rcv_filtros_activity)
    RecyclerView rvFiltros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtros);
        ButterKnife.bind(this);
        SharedPreferences sharedPref = getSharedPreferences(
                "SharedPreferencesPedidos", Context.MODE_PRIVATE);
        String apiIP = sharedPref.getString("apiIP", "");
        apiService = ApiUtils.getSOService(apiIP);
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

                SharedPreferences sharedPref = getSharedPreferences(
                        "SharedPreferencesPedidos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("filtro", filtroModel.getNombreFiltro());
                editor.commit();


                startActivity(new Intent(activity,ProductosActivity.class));

            }
        };

        adapter = new RVAdapterItemFiltro(filtroModelList,activity,callBackItemFiltro);
        rvFiltros.setAdapter(adapter);
        updateListFiltroModel();
    }

    private void updateListFiltroModel() {

        Log.d(LOG_ACTIVITY,"EN updateListFiltroModel");
        filtroModelList.clear();
        filtroModelList = new ArrayList<>();

        apiService.ApiGetFiltros().enqueue(new Callback<List<FiltroModel>>() {
            @Override
            public void onResponse(Call<List<FiltroModel>> call, Response<List<FiltroModel>> response) {

                if(response.isSuccessful()){
                    Log.d(LOG_ACTIVITY,"isSuccessful");
                    filtroModelList =response.body();
                    Log.d(LOG_ACTIVITY,"filtroModelList.size():"+filtroModelList.size());
                    adapter.updateRVAdapterItemFiltro(filtroModelList);
                }
            }

            @Override
            public void onFailure(Call<List<FiltroModel>> call, Throwable t) {
                Log.d(LOG_ACTIVITY,"onFailure:"+t.getMessage());
            }
        });

     /*
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
        */



    }

    private void setupToolbar(String s) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(s);
    }


}
