package com.example.bottomnamviagtionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private Context context;
    private RecyclerViewClickListener mListener;
    public List<String> imageUrl;
    private List<String> serviceName;

    public ServicesAdapter(Context context, List<String> imageUrl, List<String> serviceName, RecyclerViewClickListener listener) {
        this.context = context;
        this.imageUrl = imageUrl;
        this.serviceName = serviceName;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ServicesAdapter.ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_service, parent, false);
        return new ServicesViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ServicesViewHolder holder, int position) {
        holder.service.setText(serviceName.get(position));
        Glide.with(context)
                .load(imageUrl.get(position))
                .into(((ServicesViewHolder) holder).image);


    }

    @Override
    public int getItemCount() {
        return serviceName.size();
    }

    public static class ServicesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView service;
        private RecyclerViewClickListener mListener;


        public ServicesViewHolder(@NonNull View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            service = itemView.findViewById(R.id.serviceName);
            mListener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }




}
