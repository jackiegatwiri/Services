package com.example.bottomnamviagtionbar;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class UnoFragment extends Fragment {

    private ArrayList<String> serviceName = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


    public UnoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_uno, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        imageBitmaps();
        initViews(view);

    }

    private void imageBitmaps(){
        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/capture_scene_48+by+48.png");
        serviceName.add("Digital claim form");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/accident_recovery_and_tow_48+by+48.png");
        serviceName.add("Accident Recovery and towing");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/flat_tire_48+by+48.png");
        serviceName.add("Damage assessment");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/recovery_towing_48+by+48.png");
        serviceName.add("Courtesy car");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/hospital_48+by+48.png");
        serviceName.add("Medical evacuation");

        mImageUrls.add("https://myresque.s3.eu-central-1.amazonaws.com/Images/icons/police_48+by+48.png");
        serviceName.add("Police abstract");



    }

    private void initViews(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewServices);
        RecyclerViewClickListener listener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(UnoFragment.this.getContext(), "Position " + position, Toast.LENGTH_SHORT).show();
            }
        };
        ServicesAdapter servicesAdapter = new ServicesAdapter(getActivity(), mImageUrls, serviceName,listener);
        recyclerView.setAdapter(servicesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    }


}
