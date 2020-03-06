package com.thrymr.farmersmarket.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.thrymr.farmersmarket.Model.ConsumerRegistration;
import com.thrymr.farmersmarket.R;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class ConsumerLoginFragment extends Fragment {

    TextInputEditText consumerId,password;
    Button signIn;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    private DatabaseReference reference;
    ValueEventListener readListner;
    ConsumerRegistration login =new ConsumerRegistration();
    public ConsumerLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_consumer_login, container, false);
        initViews(view);
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog(getContext());
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                reference= FirebaseDatabase.getInstance().getReference().child("Consumer");

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            HashMap<String,Object> dataMap=(HashMap<String, Object>)dataSnapshot.getValue();

                            for (String key:dataMap.keySet()){
                                Object data=dataMap.get(key);

                                try {
                                    HashMap<String,Object> userData=(HashMap<String, Object>)data;
                                    login.setConsumerId((Long)userData.get("consumerId"));
                                    login.setConsumerPassword((String) userData.get("password"));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if(consumerId.getText().toString().equalsIgnoreCase(String.valueOf(login.getConsumerId())) && password.getText().toString().equalsIgnoreCase(login.getConsumerPassword())){
                                progressDialog.cancel();
                                Toast.makeText(getContext(),"Sign up sucessfull",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        return view;
    }

    private void initViews(View view) {
        consumerId=view.findViewById(R.id.e_txt_consumer_id);
        password=view.findViewById(R.id.e_txt_consumer_password_login);
        signIn=view.findViewById(R.id.btn_sign_in);
    }

}
