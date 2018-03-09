package com.paradorlarenta.pedidos.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.paradorlarenta.pedidos.R;
import com.paradorlarenta.pedidos.callbacks.CallBackItemFiltro;
import com.paradorlarenta.pedidos.models.FiltroModel;
import com.squareup.picasso.Picasso;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by INFO24 on 7/03/2018.
 */

public class RVAdapterItemFiltro extends RecyclerView.Adapter<RVAdapterItemFiltro.FiltrosViewHolder>  {


    public List<FiltroModel> filtroModelList;
    private Context mContext;
    private CallBackItemFiltro callBackItemFiltro;


    public RVAdapterItemFiltro(List<FiltroModel> filtroModelList, Context mContext, CallBackItemFiltro callBackItemFiltro) {
        this.filtroModelList = filtroModelList;
        this.mContext = mContext;
        this.callBackItemFiltro = callBackItemFiltro;
    }



    public class FiltrosViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_filtro_activity_filtro)
        TextView txtNombreFiltro;

        @BindView(R.id.card_container_item_filtro)
        CardView container;

        @BindView(R.id.img_filtro_activity_filtro)
        ImageView imgFiltro;


        public FiltrosViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public void updateRVAdapterItemFiltro(List<FiltroModel> filtroModelList) {
        this.filtroModelList = filtroModelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FiltrosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_filtros, parent, false);

        return new FiltrosViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FiltrosViewHolder holder, int position) {


        final FiltroModel filtroModel = filtroModelList.get(position);

        Picasso.with(mContext)
                .load(filtroModel.getUrlFiltro())
                .placeholder(R.drawable.base)
                .into(holder.imgFiltro);

        holder.txtNombreFiltro.setText(filtroModel.getNombreFiltro());

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                callBackItemFiltro.OnCallbackTouchContainer(filtroModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != filtroModelList ? filtroModelList.size() : 0);
    }


}
