package com.thrymr.farmersmarket.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.thrymr.farmersmarket.Constants;
import com.thrymr.farmersmarket.Fragments.DrawerItemsFragment;
import com.thrymr.farmersmarket.Fragments.FarmersHomeFragment;
import com.thrymr.farmersmarket.Model.Photos;
import com.thrymr.farmersmarket.R;
import com.thrymr.farmersmarket.SessionManager;
import com.thrymr.farmersmarket.adapters.FarmerAdapter;
import com.thrymr.farmersmarket.interfaces.LanguageListener;
import com.thrymr.farmersmarket.utils.MyBounceInterpolator;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private View myFragment;
    private FarmersHomeFragment mFragment;
    private RecyclerView mRcvConsumer, mRcvFormer;
    private FarmerAdapter mConsumerAdapter;
    private ArrayList<Photos> mPhotosArrayList = new ArrayList<>();
    private TextView mTxtCreateAccount, mTxtForget;
    private Button mBtnSignIn;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setupToolBar();
        setUplang();
        initializeViews();
        dataSetUP();
        mBtnSignIn.setOnTouchListener((view, motionEvent) -> {
            Animation animation= AnimationUtils.loadAnimation(this,R.anim.bounce);
            MyBounceInterpolator interpolator=new MyBounceInterpolator(0.2,20);
            animation.setInterpolator(interpolator);
            mBtnSignIn.startAnimation(animation);
            return false;
        });

    }

    private void dataSetUP() {
        mPhotosArrayList.add(new Photos(R.drawable.one, "raj"));
        mPhotosArrayList.add(new Photos(R.drawable.two, "ram"));
        mPhotosArrayList.add(new Photos(R.drawable.three, "prakash"));
        mPhotosArrayList.add(new Photos(R.drawable.four, "jyan"));
        mPhotosArrayList.add(new Photos(R.drawable.five, "joker"));

        mRcvConsumer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mConsumerAdapter = new FarmerAdapter(this, mPhotosArrayList);
        mRcvConsumer.setAdapter(mConsumerAdapter);

        mRcvFormer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mConsumerAdapter = new FarmerAdapter(this, mPhotosArrayList);
        mRcvFormer.setAdapter(mConsumerAdapter);
    }

    private void addFragments(FarmersHomeFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fl_main, fragment).commit();
        }
    }

    private void setUplang() {
        if (SessionManager.getInstance(MainActivity.this).getSelectedLan() != null) {
            changeLang(SessionManager.getInstance(MainActivity.this).getSelectedLan());
        }
    }

    private void setupToolBar() {
        mToolbar = findViewById(R.id.inc_toolbar);
        setSupportActionBar(mToolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initializeViews() {
        mDrawerLayout = findViewById(R.id.drawer_menu);
        myFragment = findViewById(R.id.drawer_fragment);
        mRcvConsumer = findViewById(R.id.rcv_consumer);
        mRcvFormer = findViewById(R.id.rcv_former);
        mTxtCreateAccount = findViewById(R.id.txt_create_account);
        mTxtCreateAccount.setOnClickListener(this);
        mTxtForget = findViewById(R.id.txt_forget);
        mTxtForget.setOnClickListener(this);
        mBtnSignIn = findViewById(R.id.btn_sign_in);
        mBtnSignIn.setOnClickListener(this);

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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(toggle);

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

    @Override
    public void onBackPressed() {
        Fragment fragmentByTag = getSupportFragmentManager().findFragmentByTag(Constants.CREATE_ACCOUNT);
        if (fragmentByTag != null && fragmentByTag.isVisible())
            getSupportFragmentManager().beginTransaction().remove(fragmentByTag).commit();
    }

    public void setActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.txt_create_account:
                mTxtCreateAccount.setPaintFlags(mTxtCreateAccount.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                new Handler().postDelayed(() -> mTxtCreateAccount.setPaintFlags(0), 100);
                intent = new Intent(this, SecondaryActivity.class);
                intent.putExtra(Constants.FRAGMENTS, Constants.CREATE_ACCOUNT);
                startActivity(intent);
                break;
            case R.id.txt_forget:
                Toast.makeText(this, "Hi forget password", Toast.LENGTH_SHORT).show();

                break;
            case R.id.btn_sign_in:
                Toast.makeText(this, "Hi sign page", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, SecondaryActivity.class);
                intent.putExtra(Constants.FRAGMENTS, Constants.SIGNED_IN);
                startActivity(intent);
                break;
            default:
                Toast.makeText(this, "Hi ....", Toast.LENGTH_SHORT).show();
        }
    }
}
