package com.paradorlarenta.pedidos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by INFO24 on 7/03/2018.
 */

public class FiltroModel {

    @SerializedName("CODIGO")
    @Expose
    private String idFiltro;

    @SerializedName("ARTICULOS")
    @Expose
    private String nombreFiltro;

    @SerializedName("URL")
    @Expose
    private String urlFiltro;


    public FiltroModel() {
    }

    public FiltroModel(String idFiltro, String nombreFiltro) {
        this.idFiltro = idFiltro;
        this.nombreFiltro = nombreFiltro;
    }

    public FiltroModel(String idFiltro, String nombreFiltro, String urlFiltro) {
        this.idFiltro = idFiltro;
        this.nombreFiltro = nombreFiltro;
        this.urlFiltro = urlFiltro;
    }

    //region Get and set
    public String getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(String idFiltro) {
        this.idFiltro = idFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String nombreFiltro) {
        this.nombreFiltro = nombreFiltro;
    }

    public String getUrlFiltro() {
        return urlFiltro;
    }

    public void setUrlFiltro(String urlFiltro) {
        this.urlFiltro = urlFiltro;
    }

    //endregion
}
