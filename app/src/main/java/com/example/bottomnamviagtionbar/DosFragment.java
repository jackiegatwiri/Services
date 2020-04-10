package com.example.bottomnamviagtionbar;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosFragment extends Fragment {

    private ArrayList<String> serviceName = new ArrayList<>();
    private TextView title;
    private ImageView imageView;
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ServicesAdapter servicesAdapter;


    public DosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imageBitmaps();
        initViews(view);





    }

    private void imageBitmaps(){
        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/flat_tire_48+by+48.png");
        serviceName.add("Valuation");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/battery_jumpstart_48+by+48.png");
        serviceName.add("Road rescue");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/non_accident_recovery_and_towing48+by+48.png");
        serviceName.add("Non-Accident Recovery and towing");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/minor_mechanical%2B48+by+48.png");
        serviceName.add("Minor mechanical");


    }

    private void initViews(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewServices);
        title = view.findViewById(R.id.title);
        imageView = view.findViewById(R.id.image);
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                if(position == 1) {
                    title.setText("Road Rescue");
                    imageView.setBackgroundResource(R.drawable.download);
                    serviceName.clear();
                    mImageUrls.clear();
                    ArrayList<String> newImageUrls = new ArrayList<>();
                    newImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/fuel_top_up_48+by+48.png");
                    newImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/battery_jumpstart_48+by+48.png");
                    newImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/flat_tire_48+by+48.png");
                    newImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/minor_mechanical%2B48+by+48.png");

                    ArrayList<String> newList = new ArrayList<>();
                    newList.add("fuel top up");
                    newList.add("battery jump-start");
                    newList.add("flat tyre");
                    newList.add("key unlock");

                    serviceName.addAll(newList);
                    mImageUrls.addAll(newImageUrls);
                    servicesAdapter.notifyDataSetChanged();
                }
            }
        };
        servicesAdapter = new ServicesAdapter(getActivity(), mImageUrls, serviceName, listener);
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setAdapter(servicesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));


    }


}
