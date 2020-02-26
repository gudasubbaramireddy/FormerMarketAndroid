package com.thrymr.formersmarket.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.thrymr.formersmarket.Model.Photos;
import com.thrymr.formersmarket.R;
import com.thrymr.formersmarket.adapters.FormerAdapter;

import java.util.ArrayList;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class DefaultFragment extends Fragment {

    private static final int DELAY_MS = 500;
    private static final int PERIOD_MS = 2500;
    private ViewPager mFormerPager;
    private RecyclerView rcvConsumer,rcvFormer;
    private FormerAdapter consumerAdapter;
    private ImageView zero, one, two, three;
    private ArrayList<Photos> photosArrayList = new ArrayList<>();
    private int currentPage = 0, page = 0;
    private Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_default, container, false);


        photosArrayList.add(new Photos(R.drawable.one));
        photosArrayList.add(new Photos(R.drawable.two));
        photosArrayList.add(new Photos(R.drawable.three));
        photosArrayList.add(new Photos(R.drawable.four));
        photosArrayList.add(new Photos(R.drawable.five));


        rcvConsumer = view.findViewById(R.id.rcv_consumer);
        rcvConsumer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        consumerAdapter =new FormerAdapter(getContext(),photosArrayList);
        rcvConsumer.setAdapter(consumerAdapter);

        rcvFormer = view.findViewById(R.id.rcv_former);
        rcvFormer.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false));
        consumerAdapter =new FormerAdapter(getContext(),photosArrayList);
        rcvFormer.setAdapter(consumerAdapter);



        return view;
    }

}
