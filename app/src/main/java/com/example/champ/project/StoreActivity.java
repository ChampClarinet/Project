package com.example.champ.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.champ.project.Models.Store;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreActivity extends AppCompatActivity {

    @BindView(R.id.imageview_store_like)
    ImageView like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        Store store = (Store) getIntent().getSerializableExtra(getString(R.string.model_name_store));

        Toolbar toolbar = findViewById(R.id.store_toolbar);
        toolbar.setTitle(store.getName());
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        getBackIcon();

    }

    @Override
    protected void onResume() {
        super.onResume();

        setData();

    }

    private void getBackIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    private void setData(){
        //if(like)
        like.setColorFilter(getResources().getColor(R.color.liked));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
