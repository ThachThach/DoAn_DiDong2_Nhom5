package com.example.doandidong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.doandidong.adapte.RecyclerViewAdapterOder;
import com.example.doandidong.data.SanPhamOder;

import java.util.ArrayList;

public class OderActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    public final static String KEY_TEN_BAN = "KEY_TEN_BAN";
    public final static String KEY_KHU_VUC = "KEY_KHU_VUC";

    ArrayList<String> tenNhom = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String value1 = bundle.getString(KEY_TEN_BAN);
            String value2 = bundle.getString(KEY_KHU_VUC);
            Log.d("TEST ten ban", value1);
            Log.d("TEST khu vuc", value2);
        }

        ListView listView = findViewById(R.id.listViewNhomSanPham);

        tenNhom.add("Nhom 1");
        tenNhom.add("Nhom 2");
        tenNhom.add("nhom 3");

        ArrayList<SanPhamOder> listData = new ArrayList<>();
        listData.add(new SanPhamOder("San pham 1",10000,20000));
        listData.add(new SanPhamOder("San pham 2",10000,20000));
        listData.add(new SanPhamOder("San pham 3",10000,20000));
        listData.add(new SanPhamOder("San pham 4",10000,20000));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tenNhom);
        listView.setAdapter(arrayAdapter);

        recyclerView = findViewById(R.id.sanpham_list);

        RecyclerViewAdapterOder myAdapterA = new RecyclerViewAdapterOder(this,listData);
        recyclerView.setAdapter(myAdapterA);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}