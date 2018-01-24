package com.easypets.champ.easypets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_text)
    EditText searchEditText;
    @BindView(R.id.search_type)
    Spinner searchTypeSpinner;
    @BindView(R.id.search_filter_button)
    Button searchFilterButton;
    @BindView(R.id.search_submit)
    Button submitButton;
    String searchText = "";
    String searchType = null;
    private boolean isSelectedOpeningOnly = false;
    private boolean[] priceFilter = {false, false, false};
    private boolean[] ratingFilter = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.search_toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        searchFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSearchFilter();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSubmit();
            }
        });

    }

    private void onSubmit() {
    }

    private void setSearchFilter() {
        startActivityForResult(new Intent(this, SearchFilterActivity.class), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1){
            try {
                isSelectedOpeningOnly = data.getBooleanExtra("isSelectedOpeningOnly", false);
                priceFilter = data.getBooleanArrayExtra("priceFilter");
                ratingFilter = data.getBooleanArrayExtra("ratingFilter");
                String s = data.getStringExtra("data");
                Log.d("data", s);
            }catch (NullPointerException e){
                Log.d("data", "unchanged");
            }
        }
    }
}
