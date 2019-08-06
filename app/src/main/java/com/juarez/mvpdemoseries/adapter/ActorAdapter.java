package com.juarez.mvpdemoseries.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.model.entity.Actor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder>{
    private Context mContext;
    private ArrayList<Actor> mDataset;
    private String endpointBanner = "https://www.thetvdb.com/banners/";

    // viewholder accede a todas las vistas
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        @BindView(R.id.imgActorImage)
        ImageView actorPhoto;
        @BindView(R.id.txtActorName)
        TextView actorName;
        @BindView(R.id.txtActorRol)
        TextView actorRol;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);

        }
    }

    // constructor
    public ActorAdapter(Context context, ArrayList<Actor> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }


    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public ActorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_actor, parent, false);

        return new ActorAdapter.ViewHolder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final ActorAdapter.ViewHolder holder, final int position) {
        // - obtiene los elementos del dataset en una posicion definida
        Glide.with(mContext)
                .load(endpointBanner + mDataset.get(position).getImage())
                .placeholder(R.drawable.toolbar_mediomelon)
                .into(holder.actorPhoto);
        holder.actorName.setText(mDataset.get(position).getName());
        holder.actorRol.setText(mDataset.get(position).getRole());

    }

    // Devuelve el tamaño de tu dataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
