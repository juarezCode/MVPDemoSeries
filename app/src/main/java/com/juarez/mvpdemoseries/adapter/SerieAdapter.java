package com.juarez.mvpdemoseries.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.juarez.mvpdemoseries.R;
import com.juarez.mvpdemoseries.model.entity.Serie;
import com.juarez.mvpdemoseries.view.activity.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SerieAdapter extends RecyclerView.Adapter<SerieAdapter.Holder> {
    private Context mContext;
    private List<Serie> mDataset;
    private String endpointBanner = "https://www.thetvdb.com/banners/";

    // constructor
    public SerieAdapter(Context context, List<Serie> myDataset) {
        mDataset = myDataset;
        mContext = context;
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageSeriesBack)
        ImageView photo;
        @BindView(R.id.txtSeriesName)
        TextView serieName;
        Holder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }
    }

    @NonNull
    @Override
    public SerieAdapter.Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_series, viewGroup, false);

        return new SerieAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SerieAdapter.Holder holder, final int position) {
        Picasso.get()
                .load(endpointBanner + mDataset.get(position).getBanner())
                .placeholder(R.drawable.banner_mediomelon)
                .into(holder.photo);
        holder.serieName.setText(mDataset.get(position).getSeriesName());
        //lanzar a pantalla detalles de serie
        holder.itemView.setOnClickListener(v -> {
            Log.e("Adapter", "id: " + mDataset.get(position).getId());


            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("seriesName", mDataset.get(position).getSeriesName());
            intent.putExtra("serie", mDataset.get(position));
            mContext.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();

    }
}
