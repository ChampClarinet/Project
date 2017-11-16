package com.example.champ.project.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.champ.project.Adapters.ServiceRecyclerViewAdapter;
import com.example.champ.project.Interfaces.Filterable;
import com.example.champ.project.Menu.HospitalMenu;
import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Models.Store;
import com.example.champ.project.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetHospitalListFragment extends Fragment implements Filterable{

    private static final String TAG = PetHospitalListFragment.class.getSimpleName();

    @BindView(R.id.rv_hospital)
    RecyclerView mRecyclerViewHospital;

    private ServiceRecyclerViewAdapter adapter;

    public PetHospitalListFragment() {
    }

    public static PetHospitalListFragment newInstance() {
        PetHospitalListFragment fragment = new PetHospitalListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pet_hospital, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        HospitalMenu menu = HospitalMenu.getInstance(getContext());
        mRecyclerViewHospital.setHasFixedSize(true);
        mRecyclerViewHospital.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ServiceRecyclerViewAdapter(menu.getHospitalList(), getContext(), getString(R.string.model_name_hospital));
        mRecyclerViewHospital.setAdapter(adapter);
    }

    @Override
    public void filter(String query) {
        ArrayList<Store> newList = HospitalMenu.getInstance(getContext()).getFilteredHospitalList(query);
        adapter.updateList(newList);
    }

}
