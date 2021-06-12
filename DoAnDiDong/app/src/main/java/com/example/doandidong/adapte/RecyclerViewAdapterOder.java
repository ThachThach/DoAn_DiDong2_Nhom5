package com.example.doandidong.adapte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
        holder.tenBan.setText(listData.get(position).getTenSP());
        holder.giaBan.setText(listData.get(position).getGiaBan()+"");

        holder.btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listData.get(position).setSoLuong(listData.get(position).getSoLuong()+1);
                holder.lblSoLuong.setText((listData.get(position).getSoLuong())+"");

            }
        });

        holder.btnTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listData.get(position).getSoLuong() != 0){
                    listData.get(position).setSoLuong(listData.get(position).getSoLuong()-1);
                    holder.lblSoLuong.setText((listData.get(position).getSoLuong())+"");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tenBan, giaBan, lblSoLuong;
        Button btnCong, btnTru;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tenBan = itemView.findViewById(R.id.lblTenSanPham);
            giaBan = itemView.findViewById(R.id.lblGia);
            btnCong = itemView.findViewById(R.id.btnCong);
            btnTru = itemView.findViewById(R.id.btnTru);
            lblSoLuong = itemView.findViewById(R.id.lblSoLuong);
        }
    }
}
