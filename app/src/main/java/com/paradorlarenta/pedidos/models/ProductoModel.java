package com.paradorlarenta.pedidos.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nelsy Acuña on 05/03/2018.
 */

public class ProductoModel {

    @SerializedName("REFERENCIA")
    @Expose
    private String idProducto;

    @SerializedName("DESCRIPCION")
    @Expose
    private String nombreProducto;

    @SerializedName("PRECIODEVENTA")
    @Expose
    private Double valorProducto;

    //@SerializedName("REFERENCIA")
    //@Expose
    private String urlImage;


    public ProductoModel() {
    }

    public ProductoModel(String idProducto, String nombreProducto, Double valorProducto, String urlImage) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.valorProducto = valorProducto;
        this.urlImage = urlImage;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
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

