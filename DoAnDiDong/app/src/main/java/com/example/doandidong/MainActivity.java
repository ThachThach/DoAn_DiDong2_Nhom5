package com.example.doandidong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doandidong.setting.CustomArrayAdapter;
import com.example.doandidong.setting.Setting;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listViewSetting;
    ArrayList<Setting> arraySetting;
    CustomArrayAdapter customArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        listViewSetting = (ListView)findViewById(R.id.lvSetting);

        arraySetting = new ArrayList<>();
        arraySetting.add(new Setting("Nhóm sản phẩm", R.drawable.nhomsanpham));
        arraySetting.add(new Setting("Sản phẩm", R.drawable.sanpham));
        arraySetting.add(new Setting("Sơ đồ phòng, bàn", R.drawable.phongban));
        arraySetting.add(new Setting("Quản lý nhân viên", R.drawable.nhanvien));
        arraySetting.add(new Setting("Đăng xuất", R.drawable.logout));

        customArrayAdapter = new CustomArrayAdapter(this, R.layout.item_setting, arraySetting);
        listViewSetting.setAdapter(customArrayAdapter);

        listViewSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(this,"a", Toast.LENGTH_SHORT)).show();
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                //setContentView(R.layout.layout_sanphammoi);
                return true;

            default:break;
        }

        return super.onOptionsItemSelected(item);
    }
}