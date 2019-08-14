package com.juarez.mvpdemoseries.pagination;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juarez.mvpdemoseries.R;

import java.util.List;

public class AdapterTemporada extends RecyclerView.Adapter<AdapterTemporada.Holder> {

    private List<Temporada> mSeasons;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener){
        this.recyclerViewItemClickListener=recyclerViewItemClickListener;
    }

    // constructor
    public AdapterTemporada(List<Temporada> myDataset) {
        mSeasons = myDataset;

    }

    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public AdapterTemporada.Holder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_temporada, parent, false);

        return new AdapterTemporada.Holder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final AdapterTemporada.Holder holder, final int position) {
        holder.position=position;
        holder.numberTemporada.setText(String.valueOf(mSeasons.get(position).getNumber()));

    }

    @Override
    public int getItemCount() {
        return mSeasons.size();
    }


    // implememta de interface
    public class Holder extends RecyclerView.ViewHolder {
        private int position=0;
        TextView numberTemporada;

        Holder(View v) {//parametro
            super(v);
            //When item view is clicked, trigger the itemclicklistener
            //Because that itemclicklistener is indicated in MainActivity
            numberTemporada = v.findViewById(R.id.txtNumerSeason);
            v.setOnClickListener(v1 -> recyclerViewItemClickListener.onItemClick(v1,position));
        }

    }



}
