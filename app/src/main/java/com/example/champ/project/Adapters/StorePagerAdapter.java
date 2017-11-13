package com.example.champ.project.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.champ.project.Fragments.StoreDescriptionFragment;
import com.example.champ.project.Models.Store;

public class StorePagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = StorePagerAdapter.class.getSimpleName();
    private Context context;
    private Store store;

    public StorePagerAdapter(Store store, Context context, FragmentManager fm) {
        super(fm);
        this.store = store;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
       /* switch (position) {
            case 0:
                return StoreDescriptionFragment.newInstance(store, context);
            case 1:
                return StoreReviewFragment.newInstance();
            case 2:
                return StoreMapsFragment.newInstance();
            default:
                return null;
        }
        */
       return StoreDescriptionFragment.newInstance(store, context);
    }

    @Override
    public int getCount() {
        return 3;
    }

}
