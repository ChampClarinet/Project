package com.example.champ.project;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.champ.project.Adapters.StorePagerAdapter;
import com.example.champ.project.Models.Store;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreActivity extends AppCompatActivity {

    @BindView(R.id.store_pager)
    ViewPager pager;

    private Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        store = (Store) getIntent().getSerializableExtra(getString(R.string.model_name_store));

        //set dummy value
        boolean[] days = {false, true, true, true, false, true, false, true, false};
        store.setDayOpen(days);
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        store.setTimeOpen(calendar);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.clear();
        calendar2.set(Calendar.HOUR_OF_DAY, 20);
        calendar2.set(Calendar.MINUTE, 0);
        store.setTimeClose(calendar2);

        Toolbar toolbar = findViewById(R.id.store_toolbar);
        toolbar.setTitle(store.getName());
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        getBackIcon();

    }

    @Override
    protected void onResume() {
        super.onResume();

        setPager();

    }

    private void getBackIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setPager(){
        StorePagerAdapter adapter = new StorePagerAdapter(store, this, getSupportFragmentManager());
        pager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
