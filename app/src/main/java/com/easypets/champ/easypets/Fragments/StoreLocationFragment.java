package com.easypets.champ.easypets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.easypets.champ.easypets.Models.PetService;
import com.easypets.champ.easypets.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreLocationFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = StoreLocationFragment.class.getSimpleName();

    private static final String ARG_STORE = "TAB_LOCATION_STORE";

    @BindView(R.id.store_mapView)
    MapView mapView;
    @BindView(R.id.store_address)
    TextView addressTextView;

    GoogleMap mGoogleMap;

    private PetService petService;

    public StoreLocationFragment() {
    }

    public static StoreLocationFragment newInstance(PetService petService) {
        StoreLocationFragment fragment = new StoreLocationFragment();
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
        return inflater.inflate(R.layout.fragment_store_location, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setMap();
    }

    private void setMap() {
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        MapsInitializer.initialize(getContext());

        mGoogleMap = googleMap;

        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Log.d("Coordinates", petService.getLatitude() + ", " + petService.getLongitude());
        // Add a marker and move the camera
        LatLng location = new LatLng(petService.getLatitude(), petService.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(location).title(petService.getName()));
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16));
    }
}
