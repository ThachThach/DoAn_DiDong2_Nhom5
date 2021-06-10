package com.example.doandidong.adapte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doandidong.R;
import com.example.doandidong.data.ThuChi;

import java.util.ArrayList;

public class CustomArrayThuChi extends ArrayAdapter<ThuChi> {
    Context context;
    ArrayList<ThuChi> arrayList;
    int layoutResource;

    public CustomArrayThuChi(Context context, int resource, ArrayList<ThuChi> ojects){
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

        TextView lblTongThu = (TextView)convertView.findViewById(R.id.lblTongThu);
        TextView lblTongVon = (TextView)convertView.findViewById(R.id.lblTongVon);
        TextView lblLoiBan = (TextView)convertView.findViewById(R.id.lblLoiBan);
        TextView lblThang = (TextView)convertView.findViewById(R.id.lblThang);

        lblTongThu.setText("- Tổng thu :  " + arrayList.get(position).getTongThu()+"");
        lblTongVon.setText("- Tổng vốn :  " + arrayList.get(position).getTongVon()+"");
        lblThang.setText("Tháng "+(position+1)+": ");
        lblLoiBan.setText(" - Lời bán  : " + (arrayList.get(position).getTongThu()-arrayList.get(position).getTongVon()));

        return convertView;
    }
}
