package com.thrymr.farmersmarket.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thrymr.farmersmarket.Constants;
import com.thrymr.farmersmarket.Model.Photos;
import com.thrymr.farmersmarket.R;
import com.thrymr.farmersmarket.adapters.FarmerAdapter;
import com.thrymr.farmersmarket.adapters.SelectedFarmersAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumerHomeFragment extends Fragment {

    private RecyclerView rcvSelectedFarmers;
    private AppCompatButton btnFetch;
    private ArrayList<Photos> photosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consumer_home, container, false);
        initViews(view);
        setAdapter();
        btnFetch.setOnClickListener(view1 -> {
            Fragment fragment=new CropListFragment();
            Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.fl_secondary,fragment, Constants.FETCH_FARMERS).commit();
        });
        return view;
    }

    private void setAdapter() {
        photosList=new ArrayList<>();
        rcvSelectedFarmers.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        FarmerAdapter adapter=new FarmerAdapter(getActivity(),photosList);
        rcvSelectedFarmers.setAdapter(adapter);
    }

    private void initViews(View view) {
        rcvSelectedFarmers=view.findViewById(R.id.rcv_selected_farmers);
        btnFetch=view.findViewById(R.id.btn_fetch);

    }

}
