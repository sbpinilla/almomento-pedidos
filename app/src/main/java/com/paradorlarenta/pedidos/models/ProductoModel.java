package com.paradorlarenta.pedidos.models;

/**
 * Created by Nelsy Acuña on 05/03/2018.
 */

public class ProductoModel {

    private Integer idProducto;
    private String nombreProducto;
    private Double valorProducto;
    private String urlImage;


    public ProductoModel(Integer idProducto, String nombreProducto, Double valorProducto, String urlImage) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.valorProducto = valorProducto;
        this.urlImage = urlImage;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getValorProducto() {
        return valorProducto;
    }

    public void setValorProducto(Double valorProducto) {
        this.valorProducto = valorProducto;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }
}

