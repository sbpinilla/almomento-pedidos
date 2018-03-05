package com.almomento.pedidos.models;

/**
 * Created by Nelsy Acuña on 05/03/2018.
 */

public class ProductoModel {


    private String nombreProducto;
    private Double valorProducto;
    private String urlImage;


    public ProductoModel(String nombreProducto, Double valorProducto, String urlImage) {
        this.nombreProducto = nombreProducto;
        this.valorProducto = valorProducto;
        this.urlImage = urlImage;
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

