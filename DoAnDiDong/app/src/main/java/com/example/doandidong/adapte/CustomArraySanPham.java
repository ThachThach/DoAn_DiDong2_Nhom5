package com.example.doandidong.adapte;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.doandidong.R;
import com.example.doandidong.data.Sanpham;

import java.util.ArrayList;

public class CustomArraySanPham extends ArrayAdapter<Sanpham>  {
    @NonNull
    @Override
    public Context getContext() {
        return context;
    }

    public ArrayList<Sanpham> getArrayList() {
        return arrayList;
    }

    public int getLayoutResource() {
        return layoutResource;
    }

    Context context;
    ArrayList<Sanpham> arrayList;
    int layoutResource;

    public CustomArraySanPham(Context context, int resource, ArrayList<Sanpham> objects) {
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
        CheckBox checkBox = convertView.findViewById(R.id.checkSanpham);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox)v).isChecked()){
                    arrayList.get(position).setCheckSP(true);
                }
                else {
                    arrayList.get(position).setCheckSP(false);
                }
            }
        });
        TextView tensanpham = convertView.findViewById(R.id.tensanpham);
        TextView gia = convertView.findViewById(R.id.giasanpham);
        TextView tennhomsanpham = convertView.findViewById(R.id.tennhomsanpham);
        ImageView img = convertView.findViewById(R.id.icon_delete);

        tennhomsanpham.setText(arrayList.get(position).nhomSanPham);
        tensanpham.setText(arrayList.get(position).tenSanpham);
        gia.setText(arrayList.get(position).giaSanpham+"");
        img.setImageResource(arrayList.get(position).getImage());

        return  convertView;
    }
}
