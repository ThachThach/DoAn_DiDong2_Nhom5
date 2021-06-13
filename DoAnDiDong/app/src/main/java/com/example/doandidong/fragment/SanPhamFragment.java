package com.example.doandidong.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.doandidong.R;
import com.example.doandidong.SanPhamMoi;
import com.example.doandidong.adapte.CustomArraySanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SanPhamFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SanPhamFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String SAN_PHAM1 = "CaPhe";
    private String SAN_PHAM2 = "Banh";
    private  double GIA_SANPHAM = 20000;
    ArrayList<Sanpham> arraySanPham;
    ListView listView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SanPhamFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SanPhamFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SanPhamFragment newInstance(String param1, String param2) {
        SanPhamFragment fragment = new SanPhamFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        int sec = getArguments().getInt(ARG_PARAM1);


        View view = inflater.inflate(R.layout.fragment_san_pham, container, false);
//
//        ListView listView = view.findViewById(R.id.list_item_sanphan);
//
//        arraySanPham = new ArrayList<>();
//        arraySanPham.add(new Sanpham("123",123.0,R.drawable.icon_delete));
//        arraySanPham.add(new Sanpham("123",123.0,R.drawable.icon_delete));
//        CustomArraySanPham customArrayAdapter = new CustomArraySanPham(this, R.layout.item_sanpham,arraySanPham);
//        listView.setAdapter(customArrayAdapter);

//        if(sec == 1){
//            view = inflater.inflate(R.layout.fragment_san_pham, container, false);
//        }else{
//            view = inflater.inflate(R.layout.fragment_san_pham, container, false);
//        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.list_item_sanphan);
                arraySanPham = new ArrayList<>();
        arraySanPham.add(new Sanpham("123",123.0,R.drawable.icon_delete));
        arraySanPham.add(new Sanpham("123",123.0,R.drawable.icon_delete));
        FloatingActionButton floatingActionButton = view.findViewById(R.id.themsanpham);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view, "Thêm san pham  mới", Snackbar.LENGTH_LONG)
                        .setAction("Thêm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent = new Intent(view.getContext(), SanPhamMoi.class);
                                startActivity(intent);
                            }
                        }).show();
            }
        });
        CustomArraySanPham customArrayAdapter = new CustomArraySanPham(view.getContext(), R.layout.item_sanpham,arraySanPham);
        listView.setAdapter(customArrayAdapter);
    }
}