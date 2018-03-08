package com.paradorlarenta.pedidos.models;

/**
 * Created by INFO24 on 7/03/2018.
 */

public class FiltroModel {

    private Integer idFiltro;
    private String nombreFiltro;

    public FiltroModel() {
    }

    public FiltroModel(Integer idFiltro, String nombreFiltro) {
        this.idFiltro = idFiltro;
        this.nombreFiltro = nombreFiltro;
    }


    //region Get and set
    public Integer getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(Integer idFiltro) {
        this.idFiltro = idFiltro;
    }

    public String getNombreFiltro() {
        return nombreFiltro;
    }

    public void setNombreFiltro(String nombreFiltro) {
        this.nombreFiltro = nombreFiltro;
    }
    //endregion
}
