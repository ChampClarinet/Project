package com.example.champ.project.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.champ.project.Models.Store;
import com.example.champ.project.R;
import com.example.champ.project.Utils;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreDescriptionFragment extends Fragment {

    private static final String TAG = StoreDescriptionFragment.class.getSimpleName();

    private Context context;

    private static final String ARG_STORE = "STORE";

    private Store store;

    @BindView(R.id.imageview_store)
    ImageView storeImageView;
    @BindView(R.id.store_like_quantity)
    TextView likesTextView;
    @BindView(R.id.imageview_store_like)
    ImageView likeImageView;
    @BindView(R.id.store_time_open)
    TextView openTimeTextView;
    @BindView(R.id.store_tel)
    TextView telTextView;
    @BindView(R.id.store_desc)
    TextView descTextView;
    @BindView(R.id.store_open_sunday)
    TextView sun;
    @BindView(R.id.store_open_monday)
    TextView mon;
    @BindView(R.id.store_open_tuesday)
    TextView tue;
    @BindView(R.id.store_open_wednesday)
    TextView wed;
    @BindView(R.id.store_open_thursday)
    TextView thu;
    @BindView(R.id.store_open_friday)
    TextView fri;
    @BindView(R.id.store_open_saturday)
    TextView sat;

    public StoreDescriptionFragment() {
    }

    public static StoreDescriptionFragment newInstance(Store store, Context context) {
        StoreDescriptionFragment fragment = new StoreDescriptionFragment();
        fragment.context = context;
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORE, store);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            store = (Store) getArguments().getSerializable(ARG_STORE);
        }
    }

    private String getOpenTimeString(boolean[] dayOpen, Calendar openTime, Calendar closeTime) {
        String out = "";
        if (dayOpen[0]) {
            out += context.getString(R.string.every_day) + " ";
        }
        out += Utils.calendarToStringHourAndMinute(context, openTime) + " " +
                context.getString(R.string.to) + " " +
                Utils.calendarToStringHourAndMinute(context, closeTime);
        if (Locale.getDefault().getLanguage().equals("ja")) {
            out += context.getString(R.string.made);
        }
        return out;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_description, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        //set image
        likesTextView.setText(Integer.toString(store.getLikes()));
        //check if liked to use this command
        try {
            likeImageView.setColorFilter(context.getResources().getColor(R.color.liked));
        } catch (NullPointerException e) {
            likeImageView.setColorFilter(Color.argb(255, 249, 126, 119));
        }
        setDays();
        openTimeTextView.setText(getOpenTimeString(store.getDayOpen(), store.getTimeOpen(), store.getTimeClose()));
        //telTextView.setText(store.getTelNo());
        //descTextView.setText(store.getDescription());

    }

    private void setDays() {
        boolean[] b = store.getDayOpen();
        if (!b[0]) {
            if (!b[1]) {
                sun.setVisibility(View.GONE);
            }
            if (!b[2]) {
                mon.setVisibility(View.GONE);
            }
            if (!b[3]) {
                tue.setVisibility(View.GONE);
            }
            if (!b[4]) {
                wed.setVisibility(View.GONE);
            }
            if (!b[5]) {
                thu.setVisibility(View.GONE);
            }
            if (!b[6]) {
                fri.setVisibility(View.GONE);
            }
            if (!b[2]) {
                sat.setVisibility(View.GONE);
            }
        }
    }
}
