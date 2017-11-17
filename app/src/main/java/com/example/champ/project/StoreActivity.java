package com.example.champ.project;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.champ.project.Adapters.StorePagerAdapter;
import com.example.champ.project.Models.PetService;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreActivity extends AppCompatActivity {

    @BindView(R.id.store_pager)
    ViewPager pager;
    @BindView(R.id.store_tabs)
    TabLayout tabLayout;

    private PetService petService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        petService = (PetService) getIntent().getSerializableExtra(getString(R.string.model_name_services));

        Toolbar toolbar = findViewById(R.id.store_toolbar);
        toolbar.setTitle(petService.getName());
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        //setup tabs
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

        setUpBackIcon();

    }

    @Override
    protected void onResume() {
        super.onResume();

        setPager();

    }

    private void setUpBackIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setPager(){
        StorePagerAdapter adapter = new StorePagerAdapter(petService, getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
