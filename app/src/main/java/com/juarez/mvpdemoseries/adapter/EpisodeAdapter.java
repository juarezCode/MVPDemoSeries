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
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Episode> mDataset;
    private String endpointBanner = "https://www.thetvdb.com/banners/";

    // viewholder accede a todas las vistas
    static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        @BindView(R.id.imgChapterImage)
        ImageView chapterPhoto;
        @BindView(R.id.txtChapterNumber)
        TextView chapterName;
        @BindView(R.id.txtChapterOverview)
        TextView chapterOverview;
        @BindView(R.id.txtChapterFirstAired)
        TextView chapterFisrtAired;
        @BindView(R.id.txtChapterSiteRating)
        TextView chapterRating;

        ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);

        }
    }

    // constructor
    public EpisodeAdapter(Context context, ArrayList<Episode> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }


    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public EpisodeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_episode, parent, false);

        return new EpisodeAdapter.ViewHolder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final EpisodeAdapter.ViewHolder holder, final int position) {
        // - obtiene los elementos del dataset en una posicion definida
        Picasso.get()
                .load(endpointBanner + mDataset.get(position).getFilename())
                .placeholder(R.drawable.toolbar_mediomelon)
                .into(holder.chapterPhoto);
        holder.chapterName.setText("Capitulo "+mDataset.get(position).getAiredEpisodeNumber()+": "+mDataset.get(position).getEpisodeName());
        holder.chapterOverview.setText(mDataset.get(position).getOverview());
        holder.chapterFisrtAired.setText("Emitido: "+mDataset.get(position).getFirstAired());
        holder.chapterRating.setText("Calificacion: "+mDataset.get(position).getSiteRating());

    }

    // Devuelve el tamaño de tu dataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
