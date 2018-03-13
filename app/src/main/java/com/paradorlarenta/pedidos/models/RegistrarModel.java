package com.paradorlarenta.pedidos.models;

import java.util.List;

/**
 * Created by INFO24 on 12/03/2018.
 */

public class RegistrarModel {

    private Integer mesa;
    private String descripcion;
    private String total;
    private List<PedidoModel> listPedidos;


    public RegistrarModel() {
    }

    public RegistrarModel(Integer mesa, String descripcion, List<PedidoModel> listPedidos) {
        this.mesa = mesa;
        this.descripcion = descripcion;
        this.listPedidos = listPedidos;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Integer getMesa() {
        return mesa;
    }

    public void setMesa(Integer mesa) {
        this.mesa = mesa;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<PedidoModel> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<PedidoModel> listPedidos) {
        this.listPedidos = listPedidos;
    }
}



