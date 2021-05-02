package com.example.doandidong.setting;

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

public class CustomArrayAdapter extends ArrayAdapter<Setting> {
    Context context;
    ArrayList<Setting> arrayList;
    int layoutResource;

    public CustomArrayAdapter(Context context, int resource, ArrayList<Setting> ojects){
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

        TextView txt = (TextView)convertView.findViewById(R.id.textSetting);
        txt.setText(arrayList.get(position).name);

        ImageView img = (ImageView)convertView.findViewById(R.id.imgSetting);
        img.setImageResource(arrayList.get(position).getImg());

        return convertView;
    }


}
