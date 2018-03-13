package com.paradorlarenta.pedidos.conexion;


import android.util.Log;

import com.mancj.materialsearchbar.adapter.DefaultSuggestionsAdapter;

/**
 * Created by INFO24 on 8/03/2018.
 */

public class ApiUtils {

    //CODE SESSION EXPIRE

    public static  final  Integer CODE_SESION_EXPIRE=429;


    public static final String GET_FILTROS = "getFiltros.php";
    public static final String GET_PRODUCTOS = "getProductos.php";
    public static final String POST_REGISTRAR = "registrarPedido.php";

    public static SOService getSOService(String ip) {

        Log.d("SOService","http://"+ip+"/pedidos/");

        return RetrofitClient.getClient("http://"+ip+"/pedidos/").create(SOService.class);
    }


}
