package com.example.posapp;

import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

public class NhanVienActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhanvien);
        //RecyclerView recyclerView =  findViewById(R.id.listview);
        //GridLayoutManager linearLayoutManager= new GridLayoutManager(this,2);
        //linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        //recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

}