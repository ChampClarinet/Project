package com.example.champ.project.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.champ.project.Models.PetService;
import com.example.champ.project.R;
import com.example.champ.project.Utils.Utils;

import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreDescriptionFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = StoreDescriptionFragment.class.getSimpleName();

    private static final String ARG_STORE = "STORE";

    private PetService petService;
    private boolean isLiked;

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

    public static StoreDescriptionFragment newInstance(PetService petService) {
        StoreDescriptionFragment fragment = new StoreDescriptionFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORE, petService);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            petService = (PetService) getArguments().getSerializable(ARG_STORE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_description, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        String imgPath = petService.getPicturePath();
        storeImageView.setImageDrawable(Utils.getDrawableFromAssets(getContext(), imgPath));
        likesTextView.setText(Integer.toString(petService.getLikes()));
        isLiked = Utils.getLikeCondition(petService.getId(), getActivity());
        if (isLiked) {
            likeImageView.setColorFilter(getContext().getResources().getColor(R.color.liked));
        }
        setDays();
        openTimeTextView.setText(getOpenTimeString(petService.getDayOpen(), petService.getTimeOpen(), petService.getTimeClose()));
        telTextView.setText(petService.getTelNo());
        descTextView.setText(petService.getDescription());

        likeImageView.setOnClickListener(this);

    }

    private String getOpenTimeString(boolean[] dayOpen, Calendar openTime, Calendar closeTime) {
        String out = "";
        if (dayOpen[0]) {
            out += getContext().getString(R.string.every_day) + " ";
        }
        out += Utils.calendarToStringHourAndMinute(getContext(), openTime) + " " +
                getContext().getString(R.string.to) + " " +
                Utils.calendarToStringHourAndMinute(getContext(), closeTime);
        if (Locale.getDefault().getLanguage().equals("ja")) {
            out += getContext().getString(R.string.made);
        }
        return out;
    }

    private void setDays() {
        boolean[] b = petService.getDayOpen();
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

    @Override
    public void onClick(View v) {
        if (isLiked) {
            isLiked = false;
            likeImageView.setColorFilter(getContext().getResources().getColor(R.color.icon));
            Utils.unLike(petService.getId(), getActivity());
        } else {
            isLiked = true;
            likeImageView.setColorFilter(getContext().getResources().getColor(R.color.liked));
            Utils.like(petService.getId(), getActivity());
        }
    }

}
