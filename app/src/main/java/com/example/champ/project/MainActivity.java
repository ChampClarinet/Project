package com.example.champ.project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.champ.project.Adapters.ServiceListPagerAdapter;
import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Models.PetService;
import com.example.champ.project.Utils.GPSTracker;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.main_content_root)
    ConstraintLayout contentRoot;
    @BindView(R.id.service_tabs)
    TabLayout serviceTabLayout;
    @BindView(R.id.service_list_pager)
    ViewPager serviceViewPager;

    private ServiceMenu mServiceMenu;

    private String defaultLanguage;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        GPSTracker.getInstance(this); //for let GPSTracker has context

        //Utils.setLanguage();

        setNavBarAndActionBar();
        setTabAndPager();
        setMenu();
        Log.d("DBtest", mServiceMenu.toString());
    }

    private void setTabAndPager() {
        serviceViewPager.setAdapter(new ServiceListPagerAdapter(getSupportFragmentManager()));
        serviceViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(serviceTabLayout));
        serviceTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(serviceViewPager));
    }

    private void setMenu() {
        mServiceMenu = ServiceMenu.getInstance(this);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Perform the final search
                Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                ArrayList<PetService> result = mServiceMenu.search(query);
                if (result != null && result.size() > 0) {
                    intent.putExtra("searchResults", result);
                    startActivity(intent);
                } else {
                    searchView.setQuery("", false);
                    Toast.makeText(MainActivity.this, getString(R.string.msg_zero_results), Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //Text has changed, apply filtering?
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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

}
