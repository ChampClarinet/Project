package com.easypets.champ.easypets;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.easypets.champ.easypets.Adapters.ServiceRecyclerViewAdapter;
import com.easypets.champ.easypets.Menu.ServiceMenu;
import com.easypets.champ.easypets.Models.PetService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {

    @BindView(R.id.rv_search_results)
    RecyclerView searchResultsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = findViewById(R.id.search_results_toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        ArrayList<PetService> searchResults = (ArrayList<PetService>) getIntent().getSerializableExtra("searchResults");
        ServiceMenu menu = ServiceMenu.getInstance(this);
        searchResults = menu.sort(searchResults, ServiceMenu.SORT_BY_LIKES);
        searchResults = menu.sort(searchResults, ServiceMenu.SORT_BY_DISTANCE);

        ServiceRecyclerViewAdapter adapter = new ServiceRecyclerViewAdapter(searchResults, this);
        searchResultsRecyclerView.setHasFixedSize(true);
        searchResultsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResultsRecyclerView.setAdapter(adapter);
        getBackIcon();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    public void getBackIcon() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) finish();
        return super.onOptionsItemSelected(item);
    }
}
