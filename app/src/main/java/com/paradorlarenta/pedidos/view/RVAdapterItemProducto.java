package com.paradorlarenta.pedidos.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemProducto;
import com.paradorlarenta.pedidos.models.ProductoModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nelsy Acuña on 05/03/2018.
 */


public class RVAdapterItemProducto extends RecyclerView.Adapter<RVAdapterItemProducto.ProductosViewHolder> {

    public List<ProductoModel> productoModelList;
    private Context mContext;
    private CallBackItemProducto callBackItemProducto;


    public RVAdapterItemProducto(List<ProductoModel> productoModelList, Context mContext, CallBackItemProducto callBackItemProducto) {
        this.productoModelList = productoModelList;
        this.mContext = mContext;
        this.callBackItemProducto = callBackItemProducto;



    }

    public void setFilter(List<ProductoModel> newList){
        productoModelList = new ArrayList<>();
        productoModelList.addAll(newList);
        notifyDataSetChanged();
    }


    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cv_container_item_producto)
        CardView container;

        @BindView(R.id.img_producto_item_producto)
        ImageView imgProducto;

        @BindView(R.id.txt_nombre_item_producto)
        TextView txtNombreProducto;

        @BindView(R.id.txt_valor_item_producto)
        TextView txtValorProducto;

        public ProductosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


    public void updateRVAdapterItemProducto(List<ProductoModel> productoModelList) {
        this.productoModelList = productoModelList;
        notifyDataSetChanged();
    }


    @Override
    public ProductosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_producto, viewGroup, false);

        return new ProductosViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final ProductosViewHolder customViewHolder, int i) {

        final ProductoModel productoModel = productoModelList.get(i);

        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance();

        customViewHolder.txtNombreProducto.setText(productoModel.getNombreProducto());
        customViewHolder.txtValorProducto.setText(("$ "+String.format( new Locale("es_CO"),"%,.2f", productoModel.getValorProducto())));

        customViewHolder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBackItemProducto.OnCallbackTouchContainer(productoModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != productoModelList ? productoModelList.size() : 0);
    }



}
