package com.thrymr.formersmarket.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.thrymr.formersmarket.R;
import com.thrymr.formersmarket.SessionManager;
import com.thrymr.formersmarket.interfaces.LanguageListener;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DrawerItemsFragment extends Fragment implements PopupMenu.OnMenuItemClickListener {

    private static DrawerLayout drawerId;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    Fragment fragment;
    private  LanguageListener languageListener;

    public void getNavDrawer(DrawerLayout drawer, LanguageListener languageListener) {
        drawerId = drawer;
        this.languageListener = languageListener;
    }

   public void getMyCheck(DrawerLayout layout,LanguageListener languageListener){

   }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drawer_items, container, false);
        ImageView menuItem = view.findViewById(R.id.img_options);
        changeLang(SessionManager.getInstance(getActivity()).getSelectedLan());
        menuItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu menu = new PopupMenu(getActivity(), view);
                menu.setOnMenuItemClickListener(DrawerItemsFragment.this);
                menu.inflate(R.menu.menu);
                menu.show();
            }
        });
        drawerItemsSelctListners(view);
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        changeLang(SessionManager.getInstance(getActivity()).getSelectedLan());
    }

    /*  @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_lan:
                selectLan();
            case R.id.change_role:

        }
        return super.onOptionsItemSelected(item);
    }
*/

    public void setUp(int paramInt, DrawerLayout paramDrawerLayout) {
        View mFragmentContainerView = getActivity().findViewById(paramInt);
        this.mDrawerLayout = paramDrawerLayout;
        ActionBar localActionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        localActionBar.setDisplayHomeAsUpEnabled(true);
        localActionBar.setHomeButtonEnabled(true);
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), mDrawerLayout,
// nav menu toggle icon
                R.string.app_name, // nav drawer open - description for
// accessibility
                R.string.app_name // nav drawer close - description for
// accessibility
        ) {
            @Override
            public void onDrawerClosed(View view) {
                getActivity().invalidateOptionsMenu();
                changeLang(SessionManager.getInstance(getActivity()).getSelectedLan());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
//HIding Soft keyboard When Clicked on Navigation Drawer ....EMp 32
                /*FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (Build.VERSION.SDK_INT >= 26) {
                    ft.setReorderingAllowed(false);
                }
                ft.detach(DrawerItemsFragment.this).attach(DrawerItemsFragment.this).commit();*/

                changeLang(SessionManager.getInstance(getActivity()).getSelectedLan());
                InputMethodManager inputManager = (InputMethodManager) drawerView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                IBinder binder = drawerView.getWindowToken();
                inputManager.hideSoftInputFromWindow(binder, InputMethodManager.HIDE_NOT_ALWAYS);
                getActivity().invalidateOptionsMenu();
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    private void selectLan() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final String[] langArray = getActivity().getResources().getStringArray(R.array.lang_array);
        int pos = Integer.parseInt(SessionManager.getInstance(getActivity()).getSelectedPos());
        builder.setSingleChoiceItems(langArray, pos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                changeLang(langArray[position]);
                SessionManager.getInstance(getActivity()).setSelectedPos(String.valueOf(position));
            }
        }).setPositiveButton(getResources().getString(R.string.select), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.create().dismiss();
                changeLang(SessionManager.getInstance(getActivity()).getSelectedLan());
                languageListener.changeLang();
            }
        });
        builder.show();
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
            SessionManager.getInstance(getActivity()).setSelectedLan("hi");
        } else if (lang.equalsIgnoreCase("English") || lang.equalsIgnoreCase("en")) {
            SessionManager.getInstance(getActivity()).setSelectedLan("en");
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        isDrawerOpened();
        switch (menuItem.getItemId()) {
            case R.id.change_lan:
                selectLan();
                return true;
            default:
                return super.onContextItemSelected(menuItem);
        }
    }

    private void isDrawerOpened() {
        if (drawerId.isDrawerOpen(GravityCompat.START)) {
            drawerId.closeDrawer(GravityCompat.START);
        }
    }

    private void drawerItemsSelctListners(View view) {
        view.findViewById(R.id.btn_gov).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragment = new GovtFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_admin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new AdminFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_former_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new FormerLoginFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_consumer_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ConsumerLoginFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_field_officer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new FieldOfficerLoginFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });
        view.findViewById(R.id.btn_consumer_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ConsumerLoginFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_consumer_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ConsumerRegFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_former_reg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new FormerRegFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_consumers_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new ConsumersListFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

        view.findViewById(R.id.btn_formers_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment = new FormersListFragment();
                replaceFragment(fragment);
                isDrawerOpened();
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_main, fragment).commit();
    }

}
