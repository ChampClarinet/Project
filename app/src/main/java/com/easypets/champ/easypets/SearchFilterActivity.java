package com.easypets.champ.easypets;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFilterActivity extends AppCompatActivity {

    private boolean isSelectedOpeningOnly = false;
    private boolean[] priceFilter = {false, false, false};
    private boolean[] ratingFilter = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        Toolbar toolbar = findViewById(R.id.search_filter_toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        onCheckChanged();
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isSelectedOpeningOnly", isSelectedOpeningOnly);
                intent.putExtra("priceFilter", priceFilter);
                intent.putExtra("ratingFilter", ratingFilter);
                intent.putExtra("data", "filter updated");
                setResult(1, intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_filter, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_clear){
            clear();
        }
        return super.onOptionsItemSelected(item);
    }

    private void clear(){
        isOpeningSwitch.setChecked(false);
        priceLowButton.setChecked(false);
        priceMidButton.setChecked(false);
        priceHighButton.setChecked(false);
        oneStarButton.setChecked(false);
        twoStarButton.setChecked(false);
        threeStarButton.setChecked(false);
        fourStarButton.setChecked(false);
        fiveStarButton.setChecked(false);
    }

    private void onCheckChanged() {
        priceLowButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(priceLowButton);
                } else {
                    unCheck(priceLowButton);
                }
            }
        });
        priceMidButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(priceMidButton);
                } else {
                    unCheck(priceMidButton);
                }
            }
        });
        priceHighButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(priceHighButton);
                } else {
                    unCheck(priceHighButton);
                }
            }
        });
        oneStarButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(oneStarButton);
                } else {
                    unCheck(oneStarButton);
                }
            }
        });
        twoStarButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(twoStarButton);
                } else {
                    unCheck(twoStarButton);
                }
            }
        });
        threeStarButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(threeStarButton);
                } else {
                    unCheck(threeStarButton);
                }
            }
        });
        fourStarButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(fourStarButton);
                } else {
                    unCheck(fourStarButton);
                }
            }
        });
        fiveStarButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    check(fiveStarButton);
                } else {
                    unCheck(fiveStarButton);
                }
            }
        });

    }

    private void check(ToggleButton button) {
        Log.d("toggled", "toggled");
        button.setBackground(getDrawable(R.drawable.toggle_button_toggled));
        button.setTextColor(getResources().getColor(R.color.cardview_light_background));
    }

    private void unCheck(ToggleButton button) {
        Log.d("untoggled", "untoggled");
        button.setBackground(getDrawable(R.drawable.toggle_button));
        button.setTextColor(getResources().getColor(R.color.black));
    }

    @BindView(R.id.switch_is_opening)
    Switch isOpeningSwitch;
    @BindView(R.id.button_price_low)
    ToggleButton priceLowButton;
    @BindView(R.id.button_price_mid)
    ToggleButton priceMidButton;
    @BindView(R.id.button_price_high)
    ToggleButton priceHighButton;
    @BindView(R.id.button_one_star)
    ToggleButton oneStarButton;
    @BindView(R.id.button_two_star)
    ToggleButton twoStarButton;
    @BindView(R.id.button_three_star)
    ToggleButton threeStarButton;
    @BindView(R.id.button_four_star)
    ToggleButton fourStarButton;
    @BindView(R.id.button_five_star)
    ToggleButton fiveStarButton;
    @BindView(R.id.submit_filter)
    Button submitButton;

}
