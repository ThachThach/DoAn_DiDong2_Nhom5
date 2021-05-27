package com.example.doandidong;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.adapte.CusTomArrayNhomSanPham;
import com.example.doandidong.adapte.NhomSanPham;

import java.util.ArrayList;

public class NhomSanPhamActivity extends AppCompatActivity {
private ListView listViewNhomSanPham;
private ArrayList<NhomSanPham> arrayList;
private CusTomArrayNhomSanPham cusTomArrayNhomSanPham;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomsanpham);
        listViewNhomSanPham  = findViewById(R.id.list_item_nhomsanpham);
        arrayList = new ArrayList<>();
        arrayList.add(new NhomSanPham("Nuoc ngot",R.drawable.icon_delete));
        arrayList.add(new NhomSanPham("Ca phe",R.drawable.icon_delete));
        cusTomArrayNhomSanPham = new CusTomArrayNhomSanPham(this,R.layout.item_nhomsanpham,arrayList);
        listViewNhomSanPham.setAdapter(cusTomArrayNhomSanPham);
    }
}
