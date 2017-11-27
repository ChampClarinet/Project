package com.example.champ.project.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.champ.project.Fragments.StoreDescriptionFragment;
import com.example.champ.project.Fragments.StoreLocationFragment;
import com.example.champ.project.Fragments.StoreReviewFragment;
import com.example.champ.project.Models.PetService;

public class ServiceDetailsPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = ServiceDetailsPagerAdapter.class.getSimpleName();

    private PetService petService;

    public ServiceDetailsPagerAdapter(PetService petService, FragmentManager fm) {
        super(fm);
        this.petService = petService;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StoreDescriptionFragment.newInstance(petService);
            case 1:
                return StoreReviewFragment.newInstance(petService);
            case 2:
                return StoreLocationFragment.newInstance(petService);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
