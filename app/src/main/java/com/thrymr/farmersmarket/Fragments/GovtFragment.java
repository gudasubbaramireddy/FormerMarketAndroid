package com.thrymr.farmersmarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrymr.farmersmarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GovtFragment extends Fragment {


    public GovtFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_govt, container, false);
    }

}