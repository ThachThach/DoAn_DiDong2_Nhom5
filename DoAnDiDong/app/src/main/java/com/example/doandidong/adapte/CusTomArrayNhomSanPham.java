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

import java.util.ArrayList;

public class CusTomArrayNhomSanPham extends ArrayAdapter<NhomSanPham> {
    Context context;
    ArrayList<NhomSanPham> arrayList;
    int layout;
    public CusTomArrayNhomSanPham(Context context, int resource,ArrayList<NhomSanPham> objects) {
        super(context, resource, objects);
        this.context = context;
        this.layout = resource;
        this.arrayList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
         convertView = inflater.inflate(layout,null);
        TextView tennhomSanPham = convertView.findViewById(R.id.tennhomsanpham);
        ImageView iconDelete = convertView.findViewById(R.id.icon_delete1);

        tennhomSanPham.setText(arrayList.get(position).getTenNhom());
        iconDelete.setImageResource(arrayList.get(position).getImg());
         return convertView;
    }
}
