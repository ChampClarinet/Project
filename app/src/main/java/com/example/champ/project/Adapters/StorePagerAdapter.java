package com.example.champ.project.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.champ.project.Fragments.StoreDescriptionFragment;
import com.example.champ.project.Fragments.StoreLocationFragment;
import com.example.champ.project.Models.Store;

public class StorePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = StorePagerAdapter.class.getSimpleName();

    private Store store;

    public StorePagerAdapter(Store store, FragmentManager fm) {
        super(fm);
        this.store = store;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return StoreDescriptionFragment.newInstance(store);
            case 1:
                return StoreDescriptionFragment.newInstance(store);//StoreReviewFragment.newInstance();
            case 2:
                return StoreLocationFragment.newInstance(store);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

}
