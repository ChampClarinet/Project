package com.easypets.champ.easypets.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easypets.champ.easypets.Adapters.ServiceRecyclerViewAdapter;
import com.easypets.champ.easypets.Menu.ServiceMenu;
import com.easypets.champ.easypets.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class PetServiceFragment extends Fragment {

    private static final String TAG = PetServiceFragment.class.getSimpleName();
    private static final String ARGS_IS_HOSPITAL = "isHospital";

    private boolean isHospital;
    private ServiceMenu menu;
    private ServiceRecyclerViewAdapter adapter;

    @BindView(R.id.sortMenuFab)
    FloatingActionsMenu fabMenu;
    @BindView(R.id.fab_sort_name)
    FloatingActionButton nameFab;
    @BindView(R.id.fab_sort_likes)
    FloatingActionButton likesFab;
    @BindView(R.id.fab_sort_distance)
    FloatingActionButton distanceFab;
    @BindView(R.id.fab_sort_price_rate)
    FloatingActionButton priceRateFab;
    @BindView(R.id.fragment_rv_service)
    RecyclerView petServiceRecyclerView;

    public PetServiceFragment() {
    }

    @NonNull
    public static PetServiceFragment newInstance(boolean isHospital) {
        PetServiceFragment fragment = new PetServiceFragment();
        Bundle args = new Bundle();
        args.putBoolean(ARGS_IS_HOSPITAL, isHospital);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) isHospital = args.getBoolean(ARGS_IS_HOSPITAL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_service_overlay, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        menu = ServiceMenu.getInstance(getContext());

        if (isHospital) {
            Log.d(TAG, "creating hospital");
            adapter = new ServiceRecyclerViewAdapter(menu.getHospitalList(ServiceMenu.SORT_BY_DISTANCE), getContext());
        } else {
            Log.d(TAG, "crating service");
            adapter = new ServiceRecyclerViewAdapter(menu.getServiceList(ServiceMenu.SORT_BY_DISTANCE), getContext());
        }
        petServiceRecyclerView.setHasFixedSize(true);
        petServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        petServiceRecyclerView.setAdapter(adapter);
//        DividerItemDecoration myDivider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
//        myDivider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.divider));
//        petServiceRecyclerView.addItemDecoration(myDivider );

        setFab();

    }

    private void setFab() {
        nameFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHospital) {
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_NAME));
                } else
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_NAME));
            }
        });
        distanceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHospital) {
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_DISTANCE));
                } else
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_DISTANCE));
            }
        });
        priceRateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHospital) {
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_PRICE_RATE));
                } else
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_PRICE_RATE));
            }
        });
        likesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isHospital) {
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_LIKES));
                } else
                    adapter.updateList(menu.sort(adapter.getFilteredData(), ServiceMenu.SORT_BY_LIKES));
            }
        });
        fabMenu.collapse();
    }

}
