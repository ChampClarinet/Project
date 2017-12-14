package com.easypets.champ.easypets.Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.easypets.champ.easypets.Models.PetService;
import com.easypets.champ.easypets.R;
import com.easypets.champ.easypets.Utils.Utils;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

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

        RelativeLayout openMaps = view.findViewById(R.id.store_open_map_button);

        openMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), getString(R.string.launch_google_maps), Toast.LENGTH_SHORT).show();
                Location location = petService.getLocation();
                openGoogleMaps(location);
            }
        });

        String bannerPath = getString(R.string.banner_storage_ref) + petService.getPicturePath();
        StorageReference reference = FirebaseStorage.getInstance().getReference().child(bannerPath);
        Glide.with(this).using(new FirebaseImageLoader()).load(reference).fitCenter().into(storeImageView);

        /*Picasso.Builder bannerBuilder = new Picasso.Builder(getContext());
        bannerBuilder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                storeImageView.setImageDrawable(Utils.getDrawableFromAssets(getContext(), "scenery.jpg"));
            }
        });
        bannerBuilder.build().load(petService.getPicturePath()).into(storeImageView);
        */
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

    private void openGoogleMaps(Location location) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        String uri = Utils.getGoogleMapsUri(location);
        Log.i(TAG, uri);
        i.setData(Uri.parse(uri));
        startActivity(i);
    }

    private String getOpenTimeString(boolean[] dayOpen, String openTime, String closeTime) {
        String out = "";
        if (dayOpen[0]) {
            out += getContext().getString(R.string.every_day) + " ";
        }
        if (openTime == null || closeTime == null) {
            return out + getString(R.string.all_day);
        }
        out += Utils.timeString(getContext(), openTime) + " " + getContext().getString(R.string.to) + " " + Utils.timeString(getContext(), closeTime);
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
        } else {
            sun.setVisibility(View.GONE);
            mon.setVisibility(View.GONE);
            tue.setVisibility(View.GONE);
            wed.setVisibility(View.GONE);
            thu.setVisibility(View.GONE);
            fri.setVisibility(View.GONE);
            sat.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageview_store_like) {
            if (isLiked) {
                isLiked = false;
                likeImageView.setColorFilter(getContext().getResources().getColor(R.color.icon));
                petService.unLike();
                Utils.unLike(petService.getId(), petService.getLikes(), getActivity());
            } else {
                isLiked = true;
                likeImageView.setColorFilter(getContext().getResources().getColor(R.color.liked));
                Utils.like(petService.getId(), petService.getLikes(), getActivity());
                petService.like();
            }
            int x = petService.getLikes();
            likesTextView.setText(Integer.toString(x));
        }
    }

}
