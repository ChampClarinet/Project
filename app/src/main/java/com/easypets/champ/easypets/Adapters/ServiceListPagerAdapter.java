package com.easypets.champ.easypets.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.easypets.champ.easypets.Fragments.PetServiceFragment;

public class ServiceListPagerAdapter extends FragmentPagerAdapter{

    private static final String TAG = ServiceListPagerAdapter.class.getSimpleName();

    public ServiceListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PetServiceFragment.newInstance(false);
            case 1:
                return PetServiceFragment.newInstance(true);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

}
