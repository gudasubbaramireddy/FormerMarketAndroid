package com.thrymr.formersmarket.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thrymr.formersmarket.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FieldOfficerLoginFragment extends Fragment {


    public FieldOfficerLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_field_officer_login, container, false);
    }

}
