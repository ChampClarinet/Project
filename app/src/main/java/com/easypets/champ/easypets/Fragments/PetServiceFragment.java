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
import com.easypets.champ.easypets.Models.PetService;
import com.easypets.champ.easypets.R;
import com.easypets.champ.easypets.Utils.SortAgent;
import com.easypets.champ.easypets.Utils.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import toan.android.floatingactionmenu.FloatingActionButton;
import toan.android.floatingactionmenu.FloatingActionsMenu;

public class PetServiceFragment extends Fragment {

    private static final String TAG = PetServiceFragment.class.getSimpleName();
    private static final String ARGS_IS_HOSPITAL = "isHospital";

    private boolean isHospital;
    private ArrayList<PetService> serviceList;
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
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
        if (args != null) {
            isHospital = args.getBoolean(ARGS_IS_HOSPITAL);
            serviceList = new ArrayList<>();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference(getString(R.string.firebase_reference));
        }
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

        adapter = new ServiceRecyclerViewAdapter(serviceList, getContext());

        petServiceRecyclerView.setHasFixedSize(true);
        petServiceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        petServiceRecyclerView.setAdapter(adapter);

        bindData();

        setFab();

    }

    private void bindData(){
        Query query;
        if(isHospital){
            Log.d(TAG, "creating hospital list");
            query = databaseReference.orderByChild("type").equalTo("1");
        }else{
            query = databaseReference.orderByChild("type").equalTo("0");
            Log.d(TAG, "creating service list");
        }
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                PetService service = Utils.toService(dataSnapshot);
                serviceList.add(service);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setFab() {
        nameFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortAgent.sort(serviceList, SortAgent.sortBy.NAME);
                adapter.notifyDataSetChanged();
            }
        });
        distanceFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortAgent.sort(serviceList, SortAgent.sortBy.DISTANCE);
                adapter.notifyDataSetChanged();
            }
        });
        priceRateFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortAgent.sort(serviceList, SortAgent.sortBy.PRICE_RATE);
                adapter.notifyDataSetChanged();
            }
        });
        likesFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortAgent.sort(serviceList, SortAgent.sortBy.LIKES);
                adapter.notifyDataSetChanged();
            }
        });
        fabMenu.collapse();
    }

}
