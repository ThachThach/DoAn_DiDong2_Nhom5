package com.example.doandidong;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.doandidong.adapte.CustomArraySanPham;
import com.example.doandidong.data.SanPham;
import com.example.doandidong.fragment.SanPhamFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SanPhamActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ListView listView;
    ArrayList<SanPham> arraySanPham;
    CustomArraySanPham customArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        tabLayout = findViewById(R.id.tablaySanPham);
        viewPager = findViewById(R.id.viewsanpham);
        ArrayList<String> list = new ArrayList<>();
        listView = findViewById(R.id.list_item_sanphan);
//        arraySanPham = new ArrayList<>();
//        arraySanPham.add(new Sanpham("123",123.0,R.drawable.icon_delete));
//        arraySanPham.add(new Sanpham("123",123.0,R.drawable.icon_delete));
//        customArrayAdapter = new CustomArraySanPham(this, R.layout.item_sanpham,arraySanPham);
//        listView.setAdapter(customArrayAdapter);

        list.add("caphe");
        list.add("nuoc ngot ca loai");
        list.add("banh");
        list.add("kha");

        prepateViewPage(viewPager,list);
        tabLayout.setupWithViewPager(viewPager);


    }

    private void prepateViewPage(ViewPager mViewPager, ArrayList<String> list) {
        MainAdapter adapte = new MainAdapter(getSupportFragmentManager());

        SanPhamFragment sanPhamFragment = new SanPhamFragment();

        for(int i= 0; i<list.size(); i++){
            Bundle bundle = new Bundle();
            bundle.putString("title", list.get(i));
            sanPhamFragment.setArguments(bundle);
            adapte.addFragment(sanPhamFragment, list.get(i));
            sanPhamFragment = new SanPhamFragment();
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