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
import com.example.doandidong.data.Bepbar;

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
        TextView thoigia = convertView.findViewById(R.id.thoigianoder);
        TextView danhsach = convertView.findViewById(R.id.danhsachoder);
        thoigia.setText(arrayList.get(position).getThoigia());
        danhsach.setText(arrayList.get(position).getDanhdsachoer());
        return convertView;
    }
}
