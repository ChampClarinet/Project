package com.example.champ.project.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.champ.project.Adapters.ServiceRecyclerViewAdapter;
import com.example.champ.project.Menu.ServiceMenu;
import com.example.champ.project.Models.Store;
import com.example.champ.project.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetServiceListFragment extends Fragment implements SearchView.OnQueryTextListener {

    private static final String TAG = PetServiceListFragment.class.getSimpleName();

    @BindView(R.id.rv_service)
    RecyclerView mRecyclerViewService;

    private ServiceRecyclerViewAdapter adapter;
    private SearchView searchView;
    private ServiceMenu menu;

    public PetServiceListFragment() {
        menu = ServiceMenu.getInstance(getContext());
    }

    public static PetServiceListFragment newInstance() {
        PetServiceListFragment fragment = new PetServiceListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_main, container, false);
        searchView = v.findViewById(R.id.searchView);
        Log.d(TAG, searchView+"");
        return inflater.inflate(R.layout.fragment_pet_services, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        searchView.setOnQueryTextListener(this);
        mRecyclerViewService.setHasFixedSize(true);
        mRecyclerViewService.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ServiceRecyclerViewAdapter(menu.getServiceList(ServiceMenu.SORT_BY_LOCATION), getContext(), getString(R.string.model_name_services), (android.support.v7.widget.SearchView) getActivity().findViewById(R.id.searchView));
        mRecyclerViewService.setAdapter(adapter);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ArrayList<Store> newList = ServiceMenu.getInstance(getContext()).getFilteredServiceList(newText);
        adapter.updateList(newList);
        return false;
    }

}
