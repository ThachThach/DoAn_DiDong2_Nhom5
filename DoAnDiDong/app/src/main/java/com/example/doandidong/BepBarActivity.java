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
import com.example.doandidong.adapte.NhomSanPham;
import com.example.doandidong.fragment.SanPhamFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BepBarActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ListView listView;
    private ArrayList<NhomSanPham> arrayList;
    CustomArraySanPham customArrayAdapter;
    private FirebaseFirestore firebaseFirestore;
    private NhomSanPham nhomSanPham;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        tabLayout = findViewById(R.id.tablaySanPham);
        viewPager = findViewById(R.id.viewsanpham);

        listView = findViewById(R.id.list_item_sanphan);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("nhomsanpham");

        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull  Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    arrayList = new ArrayList<>();
                    ArrayList<String> List = new ArrayList<>();
                    QuerySnapshot snapshots = task.getResult();
                    for(QueryDocumentSnapshot doc : snapshots){
                        nhomSanPham = new NhomSanPham();
                       String name = nhomSanPham.setTenNhom(doc.get("tennhomsanpham").toString());
                        List.add(name);
                    }
                    prepateViewPage(viewPager,List);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }
        });







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