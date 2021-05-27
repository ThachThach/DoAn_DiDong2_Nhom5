package com.example.doandidong;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import com.example.doandidong.adapte.CustomArraySettingAdapter;
import com.example.doandidong.adapte.Setting;
import com.google.firebase.auth.FirebaseUser;

public class SettingActivity extends AppCompatActivity {

    private ListView listViewSetting;
    private ArrayList<Setting> arraySetting;
    private CustomArraySettingAdapter customArrayAdapter;

    private String NHOM_SAN_PHAM = "Nhóm sản phẩm";
    private String SAN_PHAM = "Sản phẩm";
    private String SO_DO = "Sơ đồ phòng, bàn";
    private String QL_NHAN_VIEN = "Quản lý nhân viên";
    private String DANG_XUAT = "Đăng xuất";
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = mAuth.getCurrentUser();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        listViewSetting = (ListView)findViewById(R.id.lvSetting);
        arraySetting = new ArrayList<>();
        arraySetting.add(new Setting(NHOM_SAN_PHAM, R.drawable.nhomsanpham));
        arraySetting.add(new Setting(SAN_PHAM, R.drawable.sanpham));
        arraySetting.add(new Setting(SO_DO, R.drawable.phongban));
        arraySetting.add(new Setting(QL_NHAN_VIEN, R.drawable.nhanvien));
        arraySetting.add(new Setting(DANG_XUAT, R.drawable.logout));

        customArrayAdapter = new CustomArraySettingAdapter(this, R.layout.item_setting, arraySetting);
        listViewSetting.setAdapter(customArrayAdapter);

        listViewSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==4){
                    mAuth.signOut();

                    Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }else if(position == 3){
                    Intent intent = new Intent(SettingActivity.this, QuanLyNhanVien.class);
                    startActivity(intent);
                }
            }
        });

    }
}