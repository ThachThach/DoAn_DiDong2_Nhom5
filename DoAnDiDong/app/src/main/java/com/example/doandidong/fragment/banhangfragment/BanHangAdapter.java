package com.example.doandidong.fragment.banhangfragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class BanHangAdapter extends FragmentStatePagerAdapter {
    private String listTab[] = {"Khu vực I","Khu vực II","Khu vực III"};
    private KhuVuc1Fragment khu1;
    private KhuVuc2Fragment khu2;
    private KhuVuc3Fragment khu3;
    public BanHangAdapter(FragmentManager fm, int behavior) {
        super(fm, behavior);
        khu1 = new KhuVuc1Fragment();
        khu2 = new KhuVuc2Fragment();
        khu3 = new KhuVuc3Fragment();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return khu1;
        }else if (position == 1){
            return khu2;
        }else {
            return khu3;
        }
    }

    @Override
    public int getCount() { return listTab.length; }
    public CharSequence getPageTitle(int potision)
    {
        return listTab[potision];
    }
}
