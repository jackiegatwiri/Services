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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosFragment extends Fragment {

    private ArrayList<String> serviceName = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();


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

    private void initViews(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewServices);
        ServicesAdapter servicesAdapter = new ServicesAdapter(getActivity(), mImageUrls, serviceName);
        recyclerView.setAdapter(servicesAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    }

}
