package com.example.bottomnamviagtionbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesViewHolder> {

    private Context context;
    private List<String> imageUrl;
    private List<String> serviceName;

    public ServicesAdapter(Context context, List<String> imageUrl, List<String> serviceName) {
        this.context = context;
        this.imageUrl = imageUrl;
        this.serviceName = serviceName;
    }

    @NonNull
    @Override
    public ServicesAdapter.ServicesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.item_service, parent, false);
        return new ServicesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ServicesViewHolder holder, int position) {
        holder.service.setText(serviceName.get(position));
        Glide.with(context)
                .load(imageUrl.get(position))
                .into(holder.image);


    }

    @Override
    public int getItemCount() {
        return serviceName.size();
    }

    public static class ServicesViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView service;
        public ServicesViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            service = itemView.findViewById(R.id.serviceName);
        }
    }
}
