package com.example.champ.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.champ.project.Adapters.SettingListAdapter;
import com.example.champ.project.Models.SettingsItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    private final String TAG = SettingsActivity.class.getSimpleName();

    @BindView(R.id.listview_settings)
    ListView listView;

    private ArrayList<SettingsItem> settingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);

        settingList = new ArrayList<>();
        SettingsItem item = new SettingsItem(getString(R.string.language), R.drawable.ic_language_black_24dp);
        settingList.add(item);

        ButterKnife.bind(this);
        getBackIcon();
        setListView();

    }

    private void setListView() {
        listView.setAdapter(new SettingListAdapter(this, R.layout.listview_layout_settings, settingList));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) languageSettingsActivity();
            }
        });
    }

    private void languageSettingsActivity() {
        startActivity(new Intent(this, LanguageActivity.class));
    }

    private void getBackIcon() {
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
