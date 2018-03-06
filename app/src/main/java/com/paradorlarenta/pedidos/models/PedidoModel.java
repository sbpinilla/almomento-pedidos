package com.paradorlarenta.pedidos.models;

/**
 * Created by INFO24 on 5/03/2018.
 */

public class PedidoModel {

    private ProductoModel productoModel;
    private Integer cantidad;

    public PedidoModel(ProductoModel productoModel, Integer cantidad) {
        this.productoModel = productoModel;
        this.cantidad = cantidad;
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
