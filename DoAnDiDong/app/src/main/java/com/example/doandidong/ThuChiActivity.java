package com.example.doandidong;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.doandidong.fragment.ThuChiFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ThuChiActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_chi);
        mTabLayout = findViewById(R.id.tablay);
        mViewPager = findViewById(R.id.view);

        ArrayList<String> list = new ArrayList<>();

        list.add("t1");
        list.add("t2");
        list.add("t4");
        list.add("t5");
        list.add("t6");
        list.add("t7");
        list.add("t8");
        list.add("t9");

        prepateViewPage(mViewPager, list);

        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void prepateViewPage(ViewPager mViewPager, ArrayList<String> list) {
        MainAdapter adapte = new MainAdapter(getSupportFragmentManager());

        ThuChiFragment mainFragment = new ThuChiFragment();

        for(int i= 0; i<list.size(); i++){
            Bundle bundle = new Bundle();
            bundle.putString("title", list.get(i));
            mainFragment.setArguments(bundle);
            adapte.addFragment(mainFragment, list.get(i));
            mainFragment = new ThuChiFragment();
        }
        mViewPager.setAdapter(adapte);
    }

    private class MainAdapter extends FragmentPagerAdapter {
        ArrayList<String> arrayList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title){
            arrayList.add(title);
            fragmentList.add(fragment);
        }

        public MainAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        public MainAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return arrayList.get(position);
        }
    }
}