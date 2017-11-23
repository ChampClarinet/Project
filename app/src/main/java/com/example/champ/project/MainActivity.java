package com.example.champ.project;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.champ.project.Adapters.ServiceRecyclerViewAdapter;
import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Utils.GPSTracker;
import com.example.champ.project.Utils.OnSwipeListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnFocusChangeListener, SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.main_content_root)
    ConstraintLayout contentRoot;
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.service_tabs)
    TabLayout serviceTabLayout;
    @BindView(R.id.rv_service)
    RecyclerView sRecyclerView;
    @BindView(R.id.rv_hospital)
    RecyclerView hRecyclerView;
    @BindView(R.id.fab_sort_name)
    FloatingActionButton nameFab;
    @BindView(R.id.fab_sort_price_rate)
    FloatingActionButton priceRateFab;
    @BindView(R.id.fab_sort_distance)
    FloatingActionButton distanceFab;
    @BindView(R.id.fab_sort_likes)
    FloatingActionButton likesFab;
    @BindView(R.id.sortMenuFab)
    FloatingActionsMenu menuFab;

    private ServiceMenu mServiceMenu;

    private ServiceRecyclerViewAdapter sAdapter;
    private ServiceRecyclerViewAdapter hAdapter;
    private boolean isFocusHospital;

    private String defaultLanguage;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        searchView.clearFocus();

        GPSTracker.getInstance(this);

        //Utils.setLanguage();

        setNavBarAndActionBar();
        isFocusHospital = false;
        setTab();
        setMenu();
        setRecyclerView();
        Log.d("DBtest", mServiceMenu.toString());
        setFab();
        contentRoot.setOnTouchListener(new OnSwipeListener(this) {

            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Log.d(TAG, "swipeLeft");
                changeToHospital();
                serviceTabLayout.getTabAt(1).select();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Log.d(TAG, "swipeRight");
                changeToService();
                serviceTabLayout.getTabAt(0).select();
            }
        });
    }

    private void setFab() {
        nameFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFocusHospital) {
                    sAdapter.updateList(mServiceMenu.sort(sAdapter.getFilteredData(), ServiceMenu.SORT_BY_NAME));
                } else
                    hAdapter.updateList(mServiceMenu.sort(hAdapter.getFilteredData(), ServiceMenu.SORT_BY_NAME));
            }
        });
        distanceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFocusHospital) {
                    sAdapter.updateList(mServiceMenu.sort(sAdapter.getFilteredData(), ServiceMenu.SORT_BY_DISTANCE));
                } else
                    hAdapter.updateList(mServiceMenu.sort(hAdapter.getFilteredData(), ServiceMenu.SORT_BY_DISTANCE));
            }
        });
        priceRateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFocusHospital) {
                    sAdapter.updateList(mServiceMenu.sort(sAdapter.getFilteredData(), ServiceMenu.SORT_BY_PRICE_RATE));
                } else
                    hAdapter.updateList(mServiceMenu.sort(hAdapter.getFilteredData(), ServiceMenu.SORT_BY_PRICE_RATE));
            }
        });
        likesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isFocusHospital) {
                    sAdapter.updateList(mServiceMenu.sort(sAdapter.getFilteredData(), ServiceMenu.SORT_BY_LIKES));
                } else
                    hAdapter.updateList(mServiceMenu.sort(hAdapter.getFilteredData(), ServiceMenu.SORT_BY_LIKES));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setSearchView();
    }

    private void setTab() {
        serviceTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                //switch here
                if (position == 0) {
                    changeToService();
                } else if (position == 1) {
                    changeToHospital();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void changeToHospital() {
        Log.d(TAG, "Hospital");
        isFocusHospital = true;
        sRecyclerView.setVisibility(View.GONE);
        hRecyclerView.setVisibility(View.VISIBLE);
        hAdapter.resetFilter();
        clearSearch();
    }

    private void changeToService() {
        Log.d(TAG, "PetService");
        isFocusHospital = false;
        sRecyclerView.setVisibility(View.VISIBLE);
        hRecyclerView.setVisibility(View.GONE);
        clearSearch();
        sAdapter.resetFilter();
    }

    private void clearSearch() {
        searchView.setQuery("", false);
    }

    private void setMenu() {
        mServiceMenu = ServiceMenu.getInstance(this);
    }

    private void setRecyclerView() {
        sAdapter = new ServiceRecyclerViewAdapter(mServiceMenu.getServiceList(ServiceMenu.SORT_BY_DISTANCE), this);
        sRecyclerView.setHasFixedSize(true);
        sRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        sRecyclerView.setAdapter(sAdapter);
        hAdapter = new ServiceRecyclerViewAdapter(mServiceMenu.getHospitalList(ServiceMenu.SORT_BY_DISTANCE), this);
        hRecyclerView.setHasFixedSize(true);
        hRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        hRecyclerView.setAdapter(hAdapter);
    }

    private void setSearchView() {
        searchView.setFocusable(false);
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnFocusChangeListener(this);
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                searchView.setIconified(false);
                searchView.clearFocus();
                return false;
            }
        });
        searchView.setOnQueryTextListener(this);
    }

    private void setNavBarAndActionBar() {

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
/*
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        */
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
/*
        if (id == R.id.drawer_pet_service) {
            rvService.setVisibility(View.VISIBLE);
            rvHospital.setVisibility(View.GONE);
            currentFocus = getString(R.string.model_name_store);
        } else if (id == R.id.drawer_hospital) {
            rvService.setVisibility(View.GONE);
            rvHospital.setVisibility(View.VISIBLE);
            currentFocus = getString(R.string.model_name_hospital);
        } else if (id == R.id.drawer_settings) {
            //go to setting activity
            startActivity(new Intent(this, SettingsActivity.class));
        }
*/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (v.getId() == R.id.searchView && !hasFocus) {
            Log.d("FocusFocus", "Change");
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (!isFocusHospital) {
            ServiceRecyclerViewAdapter adapter = (ServiceRecyclerViewAdapter) sRecyclerView.getAdapter();
            adapter.filterList(ServiceMenu.filter(adapter.getData(), newText));
        } else {
            ServiceRecyclerViewAdapter adapter = (ServiceRecyclerViewAdapter) hRecyclerView.getAdapter();
            adapter.filterList(ServiceMenu.filter(adapter.getData(), newText));
        }
        return true;
    }

}
