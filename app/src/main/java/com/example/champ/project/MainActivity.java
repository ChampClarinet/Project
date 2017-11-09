package com.example.champ.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.champ.project.Adapters.ServiceRecyclerViewAdapter;
import com.example.champ.project.Menu.HospitalMenu;
import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Models.Store;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.rv_service)
    RecyclerView rvService;
    @BindView(R.id.rv_hospital)
    RecyclerView rvHospital;
    @BindView(R.id.searchView)
    SearchView searchView;

    private ServiceRecyclerViewAdapter serviceAdapter;
    private ServiceRecyclerViewAdapter hospitalAdapter;

    private int sortBy;
    private String defaultLanguage;
    private String currentFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //Utils.setLanguage();

        currentFocus = getString(R.string.model_name_store);

        setNavBarAndActionBar();
    }

    private void setNavBarAndActionBar() {

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        HospitalMenu hMenu = HospitalMenu.getInstance(this);
        ServiceMenu sMenu = ServiceMenu.getInstance(this);

        ArrayList<Store> service = sMenu.getServiceList();
        ArrayList<Store> hospital = hMenu.getHospitalList();

        rvService.setHasFixedSize(true);
        rvHospital.setHasFixedSize(true);

        rvService.setLayoutManager(new LinearLayoutManager(this));
        rvHospital.setLayoutManager(new LinearLayoutManager(this));

        serviceAdapter = new ServiceRecyclerViewAdapter(service, this, getString(R.string.model_name_store));
        hospitalAdapter = new ServiceRecyclerViewAdapter(hospital, this, getString(R.string.model_name_hospital));

        rvService.setAdapter(serviceAdapter);
        rvHospital.setAdapter(hospitalAdapter);

        searchView.setOnQueryTextListener(this);

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

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(currentFocus.equals(getString(R.string.model_name_store))){
            serviceAdapter.filter(newText);
        }else if(currentFocus.equals(getString(R.string.model_name_hospital))){
            hospitalAdapter.filter(newText);
        }else Log.d(TAG, getString(R.string.error_invalid_service_type));
        return false;
    }
}
