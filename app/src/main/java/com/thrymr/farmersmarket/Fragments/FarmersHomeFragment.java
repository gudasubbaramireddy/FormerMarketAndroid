package com.thrymr.farmersmarket.Fragments;


import android.app.AlertDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.thrymr.farmersmarket.Constants;
import com.thrymr.farmersmarket.R;

import java.util.Objects;

import static com.thrymr.farmersmarket.activities.SecondaryActivity.toolbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FarmersHomeFragment extends Fragment implements View.OnClickListener {
    private Button btnSubmit;
    private int mCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        toolbar.setTitle("Crop Registration");
        View view = inflater.inflate(R.layout.fragment_farmer, container, false);

        initViews(view);

        btnSubmit.setOnClickListener(view1 -> {
            alertDialog();
        });

        return view;
    }

    private void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        if (mCount == 1) {
            builder.setMessage("Do you want to add one more?");
        } else builder.setMessage("Do you want to confirm?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            /*ProgressDialog dialog = new ProgressDialog(getActivity());
            dialog.show();
            dialog.setCancelable(false);*/
            Toast.makeText(getActivity(), "saved successfully", Toast.LENGTH_SHORT).show();
            if (mCount == 0) {
                mCount++;
                new Handler().postDelayed(this::alertDialog,1000);
            }
            else
                dialogInterface.cancel();
        });
        builder.setNegativeButton("NO", (dialogInterface, i) -> {
            if(mCount==1){
                Fragment fragment=new CropListFragment();
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction().add(R.id.fl_secondary,fragment, Constants.FORMERS_FRAGMENT).commit();
            }else Objects.requireNonNull(getActivity()).finish();
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void initViews(View view) {
        btnSubmit = view.findViewById(R.id.btn_submit);

    }

    @Override
    public void onClick(View view) {

    }

}
