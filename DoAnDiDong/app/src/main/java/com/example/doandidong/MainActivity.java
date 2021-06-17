package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    ImageView btnCaiDat, btnBanHang, btnThanhToanTaiQuay, btnBepBar, btnThuChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBanHang = findViewById(R.id.btnBanHang);
        btnCaiDat = findViewById(R.id.btnCaiDat);
        btnThanhToanTaiQuay = findViewById(R.id.btnThanhToan);
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
            startActivity(intent);
        }else if(v.getId() == R.id.btnBanHang){
            intent = new Intent(MainActivity.this, BanHangActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btnThanhToan){
            intent = new Intent(MainActivity.this, BepBarActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.btnThuChi){
            intent = new Intent(MainActivity.this, ThuChiActivity.class);
            startActivity(intent);
        }
//        else if(v.getId() == R.id.btnBepBar){
//            intent = new Intent(MainActivity.this, SanPhamActivity.class);
//            startActivity(intent);
//        }
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