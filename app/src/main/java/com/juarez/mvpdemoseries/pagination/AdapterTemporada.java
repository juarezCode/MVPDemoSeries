package com.juarez.mvpdemoseries.pagination;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juarez.mvpdemoseries.R;

import java.util.ArrayList;

public class AdapterTemporada extends RecyclerView.Adapter<AdapterTemporada.ViewHolder> {
    private Context mContext;
    public ArrayList<Temporada> mSeasons;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public void setOnItemClickListener(RecyclerViewItemClickListener recyclerViewItemClickListener){
        this.recyclerViewItemClickListener=recyclerViewItemClickListener;
    }

    // constructor
    public AdapterTemporada(Context context, ArrayList<Temporada> myDataset) {
        mSeasons = myDataset;
        mContext = context;
    }

    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public AdapterTemporada.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_temporada, parent, false);

        return new AdapterTemporada.ViewHolder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final AdapterTemporada.ViewHolder holder, final int position) {
        holder.position=position;
        holder.numberTemporada.setText(String.valueOf(mSeasons.get(position).getNumber()));

    }

    @Override
    public int getItemCount() {
        return mSeasons.size();
    }


    // implememta de interface
    public class ViewHolder extends RecyclerView.ViewHolder {
        public int position=0;
        TextView numberTemporada;

        ViewHolder(View v) {//parametro
            super(v);
            numberTemporada = v.findViewById(R.id.txtNumerSeason);
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //When item view is clicked, trigger the itemclicklistener
                    //Because that itemclicklistener is indicated in MainActivity
                    recyclerViewItemClickListener.onItemClick(v,position);
                }
            });
        }

    }



}
