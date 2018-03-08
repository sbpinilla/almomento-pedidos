package com.paradorlarenta.pedidos.models;

/**
 * Created by INFO24 on 5/03/2018.
 */

public class PedidoModel {

    private String descripcion;
    private ProductoModel productoModel;
    private Integer cantidad;

    public PedidoModel() {
    }

    public PedidoModel(String descripcion, ProductoModel productoModel, Integer cantidad) {
        this.descripcion = descripcion;
        this.productoModel = productoModel;
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ProductoModel getProductoModel() {
        return productoModel;
    }

    public void setProductoModel(ProductoModel productoModel) {
        this.productoModel = productoModel;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
