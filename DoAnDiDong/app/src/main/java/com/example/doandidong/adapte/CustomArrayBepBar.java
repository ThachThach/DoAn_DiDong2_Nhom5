package com.example.doandidong.adapte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.doandidong.R;
import com.example.doandidong.data.DanhSachSanPhamOder;
import com.example.doandidong.data.SanPhamOder;

import java.util.ArrayList;
import java.util.TreeSet;

public class CustomArrayBepBar  extends BaseAdapter {

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;

    private  ArrayList<DanhSachSanPhamOder> ds = new ArrayList<DanhSachSanPhamOder>();
    private ArrayList<SanPhamOder> mData = new ArrayList<SanPhamOder>();
    private TreeSet<Integer> sectionHeader = new TreeSet<Integer>();

    private LayoutInflater mInflater;

    public CustomArrayBepBar(Context context) {
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final SanPhamOder item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSectionHeaderItem(final SanPhamOder item) {
        mData.add(item);
        sectionHeader.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return sectionHeader.contains(position) ? TYPE_HEADER : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public SanPhamOder getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        int rowType = getItemViewType(position);

        if (convertView == null) {
            holder = new ViewHolder();
            switch (rowType) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.row_item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.txtName);
                    holder.txtValue = (TextView) convertView.findViewById(R.id.txtValue);
                    break;
                case TYPE_HEADER:
                    convertView = mInflater.inflate(R.layout.header_item, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.textSeparator);
                    break;
            }
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if(rowType == TYPE_ITEM){
            holder.textView.setText("" + mData.get(position).getTenSP());
            holder.txtValue.setText("" + mData.get(position).getSoLuong());
        }else if(rowType == TYPE_HEADER){
            holder.textView.setText(mData.get(position).getBanKhuVuc());
        }
        return convertView;
    }

    public static class ViewHolder {
        public TextView textView;
        public TextView txtValue;
    }
}