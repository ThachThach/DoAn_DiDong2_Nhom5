package com.example.doandidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.doandidong.fragment.banhangfragment.BanHangAdapter;
import com.google.android.material.tabs.TabLayout;

public class BanHangActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ban_hang);
        viewPager = findViewById(R.id.lvBan);
        //viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        tabLayout = findViewById(R.id.tltang);

        BanHangAdapter adapter = new BanHangAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}