package com.example.champ.project.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.champ.project.Fragments.PetHospitalListFragment;
import com.example.champ.project.Fragments.PetServiceListFragment;

public class MainPagerAdapter extends FragmentPagerAdapter{

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return PetServiceListFragment.newInstance();
            case 1: return PetHospitalListFragment.newInstance();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
