package com.example.doandidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    //Id activity
    ImageView btnCaiDat, btnBanHang, btnThanhToanTaiQuay, btnBepBar, btnThuChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBanHang = findViewById(R.id.btnBanHang);
        btnCaiDat = findViewById(R.id.btnCaiDat);
        btnThanhToanTaiQuay = findViewById(R.id.btnThanhToanTaiQuay);
        btnThuChi = findViewById(R.id.btnThuChi);
        btnBepBar = findViewById(R.id.btnBepBar);

        btnCaiDat.setOnClickListener(this);
        btnBanHang.setOnClickListener(this);
        btnThanhToanTaiQuay.setOnClickListener(this);
        btnThuChi.setOnClickListener(this);
        btnBepBar.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnCaiDat) {
            intent = new Intent(MainActivity.this, SettingActivity.class);
        }else if(v.getId() == R.id.btnBanHang){
            intent = new Intent(MainActivity.this, SellActivity.class);
        }else if(v.getId() == R.id.btnThanhToanTaiQuay){
            intent = new Intent(MainActivity.this, ThanhToanTaiQuayActivity.class);
        }else if(v.getId() == R.id.btnThuChi){
            intent = new Intent(MainActivity.this, SellActivity.class);
        }else if(v.getId() == R.id.btnThuChi){
            intent = new Intent(MainActivity.this, SellActivity.class);
        }
        startActivity(intent);
    }

    int i = 0;

    @Override
    public void onBackPressed() {
        i++;
        if(i==2){
            finishAffinity();
            System.exit(0);
        }
    }
}