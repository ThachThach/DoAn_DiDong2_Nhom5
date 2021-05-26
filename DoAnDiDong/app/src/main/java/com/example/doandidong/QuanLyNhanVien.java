package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class QuanLyNhanVien extends AppCompatActivity {

    private ArrayList<String> danhsachNhanVien = new ArrayList<>();
    private ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        danhsachNhanVien.add("1");
        danhsachNhanVien.add("2");
        danhsachNhanVien.add("3");
        danhsachNhanVien.add("4");

        list = findViewById(R.id.listViewNhanVien);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Thêm nhân viên mới", Snackbar.LENGTH_LONG)
                        .setAction("Thêm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent = new Intent(QuanLyNhanVien.this, ThemNhanVien.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, danhsachNhanVien);
        list.setAdapter(itemsAdapter);
    }
}