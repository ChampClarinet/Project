package com.example.champ.project.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.champ.project.Models.PetService;
import com.example.champ.project.R;

public class StoreReviewFragment extends Fragment{

    private static final String TAG = StoreReviewFragment.class.getSimpleName();

    private static final String ARG_STORE = "STORE";

    private PetService petService;

    public StoreReviewFragment() {
    }

    public static StoreReviewFragment newInstance(PetService petService){
        StoreReviewFragment fragment = new StoreReviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORE, petService);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            this.petService = (PetService) getArguments().getSerializable(ARG_STORE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store_review, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
