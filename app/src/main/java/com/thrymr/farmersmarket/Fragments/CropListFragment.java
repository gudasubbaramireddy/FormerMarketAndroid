package com.thrymr.farmersmarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.thrymr.farmersmarket.R;
import com.thrymr.farmersmarket.adapters.CropListAdapter;

import static com.thrymr.farmersmarket.activities.SecondaryActivity.toolbar;

/**
 * A simple {@link Fragment} subclass.
 */

public class CropListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        toolbar.setTitle("Crops List");
        View view=inflater.inflate(R.layout.fragment_croff_list, container, false);
        initViews(view);


        return view;
    }

    private void initViews(View view) {
        RecyclerView rcvCropList=view.findViewById(R.id.rcv_crops_list);
        rcvCropList.setLayoutManager(new LinearLayoutManager(getActivity()));
        CropListAdapter adapter=new CropListAdapter(getActivity());
        rcvCropList.setAdapter(adapter);
    }

}
