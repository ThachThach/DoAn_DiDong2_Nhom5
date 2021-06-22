package com.example.doandidong;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.doandidong.adapte.CustomArrayBepBar;
import com.example.doandidong.data.DanhSachSanPhamOder;
import com.example.doandidong.data.SanPhamOder;
import com.example.doandidong.data.item_tapped;

import java.util.ArrayList;
import java.util.Date;

public class BepBarActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<DanhSachSanPhamOder> danhSachSanPhamOders;
    private CustomArrayBepBar mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bep_bar);
        listView = (ListView) findViewById(R.id.listView);

        mAdapter = new CustomArrayBepBar(this);
        danhSachSanPhamOders = new ArrayList<>();
        ArrayList<SanPhamOder> sanPhamOders1 = new ArrayList<>();
        ArrayList<SanPhamOder> sanPhamOders2 = new ArrayList<>();
        ArrayList<SanPhamOder> sanPhamOders3 = new ArrayList<>();
        Date date = new Date();

        sanPhamOders1.add(new SanPhamOder("cafe11", 2, "ban1_khu2"));
        sanPhamOders1.add(new SanPhamOder("cafe12", 1, "ban1_khu2"));
        danhSachSanPhamOders.add(new DanhSachSanPhamOder("ban1", "khu vuc2", date, sanPhamOders1));

        sanPhamOders2.add(new SanPhamOder("cafe21", 1, "ban3_khu2"));
        sanPhamOders2.add(new SanPhamOder("cafe22", 3, "ban3_khu2"));
        sanPhamOders2.add(new SanPhamOder("cafe23", 3, "ban3_khu2"));
        sanPhamOders2.add(new SanPhamOder("cafe24", 2, "ban3_khu2"));
        danhSachSanPhamOders.add(new DanhSachSanPhamOder("ban2", "khu vuc2", date, sanPhamOders2));

        sanPhamOders3.add(new SanPhamOder("cafe31", 1, "ban3_1"));
        danhSachSanPhamOders.add(new DanhSachSanPhamOder("ban3", "khu vuc2", date, sanPhamOders3));

        for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
            SanPhamOder sp = new SanPhamOder(danhSachSanPhamOders.get(i).getTenban() + "_" + danhSachSanPhamOders.get(i).getKhuVuc());
            mAdapter.addSectionHeaderItem(sp);
            for (int j = 0; j < danhSachSanPhamOders.get(i).getListSP().size(); j++) {
                mAdapter.addItem(danhSachSanPhamOders.get(i).getListSP().get(j));
            }
        }

        listView.setAdapter(mAdapter);
        ArrayList<item_tapped> item_tappeds = new ArrayList<>();
        viTriTapped(item_tappeds);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
                    if (position == item_tappeds.get(i).getViTri()) {
                        Log.d("Test" , "aaaaaaa"+position);
                    }
                }
            }
        });
    }

    private void viTriTapped(ArrayList<item_tapped> item_tappeds) {
        int a = 0;
        for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
            item_tappeds.add(new item_tapped(danhSachSanPhamOders.get(i).getTenban() + "_" + danhSachSanPhamOders.get(i).getKhuVuc(), a));
            a += danhSachSanPhamOders.get(i).getListSP().size() + 1;
        }
    }
}