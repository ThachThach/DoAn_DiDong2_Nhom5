package com.example.doandidong.adapte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doandidong.R;
import com.example.doandidong.data.PhongBan;

import java.util.ArrayList;

public class CustomBanHangAdapter extends ArrayAdapter<PhongBan> {
    Context context;
    ArrayList<PhongBan> arrayList;
    int layoutResource;

    public CustomBanHangAdapter(Context context, int resource, ArrayList<PhongBan> ojects){
        super(context, resource, ojects);
        this.context = context;
        this.arrayList = ojects;
        this.layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResource,null);

        TextView lblTenBan = convertView.findViewById(R.id.lblTenBan);
        TextView lblTrangThai = convertView.findViewById(R.id.lblTrangThai);

        lblTenBan.setText("Tên bàn: "+arrayList.get(position).getTenban());
        lblTrangThai.setText("Trạng thái: " + arrayList.get(position).isTrangthai());

        return convertView;
    }
}
