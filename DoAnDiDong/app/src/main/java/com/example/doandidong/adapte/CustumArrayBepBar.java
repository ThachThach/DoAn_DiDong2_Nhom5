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

import java.util.ArrayList;

public class CustumArrayBepBar extends ArrayAdapter<Bepbar> {
    Context context;
    ArrayList<Bepbar> arrayList;
    int layout;
    public CustumArrayBepBar( Context context, int resource, ArrayList<Bepbar> objects) {
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
        TextView tensanpham = convertView.findViewById(R.id.tensanphamoder);
        TextView tennguoioder= convertView.findViewById(R.id.tennguoioder);
        TextView thoigian = convertView.findViewById(R.id.thoigian);
        TextView soluong = convertView.findViewById(R.id.soluonghang);
        TextView tenban = convertView.findViewById(R.id.tenbanbepbar);
        TextView khuvuc = convertView.findViewById(R.id.khuvuc);

        tensanpham.setText(arrayList.get(position).tensanpham);
        tennguoioder.setText(arrayList.get(position).tennguoioder);
        thoigian.setText(arrayList.get(position).thoigian);
        soluong.setText(arrayList.get(position).soluong+"");
        tenban.setText(arrayList.get(position).tenban);
        khuvuc.setText(arrayList.get(position).khuvuc);
        return convertView;
    }
}
