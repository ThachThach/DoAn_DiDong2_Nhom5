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
import com.example.doandidong.data.SanPham;

import java.util.ArrayList;

public class CustomArraySanPham extends ArrayAdapter<SanPham>  {

    Context context;
    ArrayList<SanPham> arrayList;
    int layoutResource;

    public CustomArraySanPham(Context context, int resource, ArrayList<SanPham> objects) {
        super(context, resource,objects);
        this.context = context;
        this.arrayList = objects;
        this.layoutResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(layoutResource,null);

        TextView tensanpham = convertView.findViewById(R.id.tensanpham);
        TextView gia = convertView.findViewById(R.id.giasanpham);
        ImageView img = convertView.findViewById(R.id.icon_delete);
        tensanpham.setText(arrayList.get(position).getTenSanpham());
        gia.setText(arrayList.get(position).getGiaSanpham()+"");
        img.setImageResource(arrayList.get(position).getImage());

        return  convertView;
    }
}
