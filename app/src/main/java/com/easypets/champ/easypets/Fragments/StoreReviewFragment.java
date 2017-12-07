package com.easypets.champ.easypets.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easypets.champ.easypets.Adapters.ServiceReviewRecyclerAdapter;
import com.easypets.champ.easypets.Menu.ReviewLoader;
import com.easypets.champ.easypets.Models.PetService;
import com.easypets.champ.easypets.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StoreReviewFragment extends Fragment {

    private static final String TAG = StoreReviewFragment.class.getSimpleName();

    @BindView(R.id.rv_review)
    RecyclerView reviewRecyclerView;

    private static final String ARG_STORE = "STORE";

    private PetService petService;

    public StoreReviewFragment() {
    }

    public static StoreReviewFragment newInstance(PetService petService) {
        StoreReviewFragment fragment = new StoreReviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_STORE, petService);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
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
        ButterKnife.bind(this, view);
        ReviewLoader loader = ReviewLoader.getInstance();
        ServiceReviewRecyclerAdapter adapter = new ServiceReviewRecyclerAdapter(loader.getReviews(), getContext());

        reviewRecyclerView.setHasFixedSize(true);
        reviewRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewRecyclerView.setAdapter(adapter);
    }

}
