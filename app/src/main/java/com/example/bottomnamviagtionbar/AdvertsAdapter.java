package com.example.bottomnamviagtionbar;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdvertsAdapter extends RecyclerView.Adapter<AdvertsAdapter.AdvertsViewHolder> {


    public int currentItem;
    AccessibilityService activity;
    private String[] description = {"MyResque, a helping hand",
            "MyResque, a helping hand",
            "MyResque, a helping hand",
            "MyResque, a helping hand",
    };
    private Context mContext;
    private String[] titles;
    private int[] images;

    public AdvertsAdapter(Context mContext, String[] titles, int[] image) {
        this.mContext = mContext;
        this.titles = titles;
        this.images = image;
    }

    @NonNull
    @Override
    public AdvertsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advert, parent, false);
        return new AdvertsAdapter.AdvertsViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdvertsViewHolder holder, int position) {

        holder.advertTitle.setText(titles[position % titles.length]);
        holder.image.setImageResource(images[position % titles.length]);

    }

    @Override
    public int getItemCount() {
//        return titles.length;
        return titles == null ? 0 : titles.length * 2;
    }

    public static class AdvertsViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView advertTitle;
        TextView description;

        public AdvertsViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            advertTitle = itemView.findViewById(R.id.advertTitle);
            itemView.setClickable(false);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    int position = getAdapterPosition();
//
//                    Snackbar.make(v, "Click detected on item " + position,
//                            Snackbar.LENGTH_LONG)
//                            .setAction("Action", null).show();
//
//                }
//            });


        }
    }
}
