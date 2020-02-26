package com.thrymr.formersmarket.activities;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.thrymr.formersmarket.Fragments.DefaultFragment;
import com.thrymr.formersmarket.Fragments.DrawerItemsFragment;
import com.thrymr.formersmarket.R;
import com.thrymr.formersmarket.SessionManager;
import com.thrymr.formersmarket.interfaces.LanguageListener;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    View myFragment;
    DefaultFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupToolBar();
        setUplang();
        initializeViews();
        fragment=new DefaultFragment();
        replaceFragments(fragment);
    }

    private void replaceFragments(DefaultFragment fragment) {
        if(fragment!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fl_main,fragment).commit();
        }
    }

    private void setUplang() {
        if (SessionManager.getInstance(MainActivity.this).getSelectedLan() != null) {
            changeLang(SessionManager.getInstance(MainActivity.this).getSelectedLan());
        }
    }

    private void setupToolBar() {
        toolbar = findViewById(R.id.inc_toolbar);
        setSupportActionBar(toolbar);
    }

    private void initializeViews() {
        drawerLayout = findViewById(R.id.drawer_menu);
        myFragment = findViewById(R.id.drawer_fragment);

    }


    @Override
    protected void onResume() {
        super.onResume();
        DrawerLayout drawer = findViewById(R.id.drawer_menu);
        DrawerItemsFragment mNavDrawerFragment = ((DrawerItemsFragment) getSupportFragmentManager().findFragmentById(R.id.drawer_fragment));
        assert mNavDrawerFragment != null;
        mNavDrawerFragment.getNavDrawer(drawer, new LanguageListener() {
            @Override
            public void changeLang() {
                MainActivity.this.recreate();
            }
        });
        mNavDrawerFragment.setUp(R.id.container, drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        if (SessionManager.getInstance(MainActivity.this).getSelectedLan() != null)
            changeLang(SessionManager.getInstance(MainActivity.this).getSelectedLan());
    }

    private void changeLang(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        onConfigurationChanged(configuration);
        if (lang.equalsIgnoreCase("Hindi") || lang.equalsIgnoreCase("hi") || lang.equalsIgnoreCase("हिंदी")) {
            SessionManager.getInstance(this).setSelectedLan("hi");
        } else if (lang.equalsIgnoreCase("English") || lang.equalsIgnoreCase("en")) {
            SessionManager.getInstance(this).setSelectedLan("en");
        }

    }

}
