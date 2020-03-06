package com.thrymr.farmersmarket.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.thrymr.farmersmarket.Constants;
import com.thrymr.farmersmarket.Fragments.FarmersHomeFragment;
import com.thrymr.farmersmarket.Fragments.RegFragment;
import com.thrymr.farmersmarket.R;

public class SecondaryActivity extends AppCompatActivity {
    public static Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);
        toolbar = findViewById(R.id.inc_toolbar);
        toolbar.setTitle("Registration");
        setSupportActionBar(toolbar);
        String type = getIntent().getStringExtra(Constants.FRAGMENTS);
        fragmentType((type != null) ? type : "");

    }

    private void fragmentType(String type) {
        Fragment fragment;
        switch (type) {
            case Constants.CREATE_ACCOUNT:
                fragment = new RegFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_secondary, fragment).commit();
                break;
            case Constants.SIGNED_IN:
                fragment=new FarmersHomeFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.fl_secondary,fragment).commit();
                break;
            default:
                Toast.makeText(this, "sorry null value", Toast.LENGTH_SHORT).show();
        }
    }

}
