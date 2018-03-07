package com.paradorlarenta.pedidos.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemPedido;
import com.paradorlarenta.pedidos.models.PedidoModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by INFO24 on 6/03/2018.
 */

public class RVAdapterItemPedido extends RecyclerView.Adapter<RVAdapterItemPedido.PedidosViewHolder>  {

    public List<PedidoModel> pedidoModelList;
    private Context mContext;
    private CallBackItemPedido callBackItemPedido;

    public RVAdapterItemPedido(List<PedidoModel> pedidoModelList, Context mContext, CallBackItemPedido callBackItemPedido) {
        this.pedidoModelList = pedidoModelList;
        this.mContext = mContext;
        this.callBackItemPedido = callBackItemPedido;
    }



    public class PedidosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_nombre_item_pedido)
        TextView txtNombreProducto;

        @BindView(R.id.txt_cantidad_item_pedido)
        TextView txtCantidad;

        @BindView(R.id.txt_precio_item_pedido)
        TextView txtValorProducto;

        @BindView(R.id.txt_descripcion_item_pedido)
        TextView txtDescripcion;

        @BindView(R.id.txt_total_item_pedido)
        TextView txtTotal;


        public PedidosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateRVAdapterItemPedido(List<PedidoModel> pedidoModelList) {
        this.pedidoModelList = pedidoModelList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PedidosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pedido, parent, false);

        return new PedidosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidosViewHolder holder, int position) {

        final PedidoModel pedidoModel= pedidoModelList.get(position);

        holder.txtNombreProducto.setText(pedidoModel.getProductoModel().getNombreProducto());
        holder.txtValorProducto.setText(("$ "+String.format( "%,.2f", pedidoModel.getProductoModel().getValorProducto())));
        holder.txtCantidad.setText(pedidoModel.getCantidad().toString());
        holder.txtDescripcion.setText(pedidoModel.getDescripcion());
        holder.txtTotal.setText(("$ "+String.format( "%,.2f", (pedidoModel.getCantidad()*pedidoModel.getProductoModel().getValorProducto()))));
    }

    @Override
    public int getItemCount() {
        return (null != pedidoModelList ? pedidoModelList.size() : 0);
    }





}
