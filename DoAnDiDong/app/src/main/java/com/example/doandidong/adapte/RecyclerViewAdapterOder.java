package com.example.doandidong.adapte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.R;
import com.example.doandidong.data.SanPhamOder;

import java.util.ArrayList;

public class RecyclerViewAdapterOder extends RecyclerView.Adapter<RecyclerViewAdapterOder.MyViewHolder> {
    private ArrayList<SanPhamOder> listData;
    private Context context;

    public RecyclerViewAdapterOder(Context ct, ArrayList<SanPhamOder> list){
        context = ct;
        listData = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_recycler_view_oder,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myText1.setText(listData.get(position).getTenSP());
        holder.myText2.setText(listData.get(position).getGiaBan()+"");
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myText1, myText2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.myText1);
            myText2 = itemView.findViewById(R.id.myText2);
        }
    }
}
