package com.easypets.champ.easypets;

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
import android.view.Menu;
import android.view.MenuItem;

import com.easypets.champ.easypets.Adapters.ServiceListPagerAdapter;
import com.easypets.champ.easypets.Utils.GPSTracker;

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
        //setMenu();
        //Log.d("DBtest", mServiceMenu.toString());
    }

    private void setTabAndPager() {
        serviceViewPager.setAdapter(new ServiceListPagerAdapter(getSupportFragmentManager()));
        serviceViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(serviceTabLayout));
        serviceTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(serviceViewPager));
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
                if (query.length() > 0) {
                    Intent intent = new Intent(MainActivity.this, SearchResultsActivity.class);
                    intent.putExtra("searchResults", query);
                    startActivity(intent);
                    searchView.setQuery("", false);
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
