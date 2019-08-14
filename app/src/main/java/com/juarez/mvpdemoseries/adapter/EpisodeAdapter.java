package com.juarez.mvpdemoseries.adapter;


import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.model.entity.Episode;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.Holder> {
    private Context mContext;
    private List<Episode> mDataset;
    private String endpointBanner = "https://www.thetvdb.com/banners/";

    // viewholder accede a todas las vistas
    static class Holder extends RecyclerView.ViewHolder {
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

        Holder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    // constructor
    public EpisodeAdapter(Context context, List<Episode> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }


    // Crea nuevas vistas (invocadas pot el layout manager)
    @Override
    public EpisodeAdapter.Holder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // crear una nueva vista
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_episode, parent, false);

        return new EpisodeAdapter.Holder(v);
    }

    // Reemplazar el contenido del layout manager
    @Override
    public void onBindViewHolder(final EpisodeAdapter.Holder holder, final int position) {
        // - obtiene los elementos del dataset en una posicion definida
        Picasso.get()
                .load(endpointBanner + mDataset.get(position).getFilename())
                .placeholder(R.drawable.toolbar_mediomelon)
                .into(holder.chapterPhoto);
        holder.chapterName.setText("Capitulo " + mDataset.get(position).getAiredEpisodeNumber() + ": " + mDataset.get(position).getEpisodeName());
        holder.chapterOverview.setText(mDataset.get(position).getOverview());
        holder.chapterFisrtAired.setText("Emitido: " + mDataset.get(position).getFirstAired());
        holder.chapterRating.setText("Calificacion: " + mDataset.get(position).getSiteRating());

        holder.chapterOverview.setOnClickListener(v -> {
            AlertDialog.Builder dialogo1 = new AlertDialog.Builder(mContext);
            dialogo1.setTitle("Sinopsis: " + mDataset.get(position).getAiredEpisodeNumber() + " " + mDataset.get(position).getEpisodeName());
            dialogo1.setMessage(mDataset.get(position).getOverview());
            dialogo1.setCancelable(true);
            dialogo1.show();
        });

    }

    // Devuelve el tamaño de tu dataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
