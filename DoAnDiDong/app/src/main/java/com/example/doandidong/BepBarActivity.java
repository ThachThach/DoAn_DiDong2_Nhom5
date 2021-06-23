package com.example.doandidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.doandidong.adapte.CustomArrayBepBar;
import com.example.doandidong.data.DanhSachSanPhamOder;
import com.example.doandidong.data.SanPhamOder;
import com.example.doandidong.data.item_tapped;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BepBarActivity extends AppCompatActivity {
    ListView listView;
    private ArrayList<DanhSachSanPhamOder> danhSachSanPhamOders;
    private CustomArrayBepBar mAdapter;

    private final static String TEN_BAN = "tenban";
    private final static String KHU_VUC = "khuvuc";
    private final static String DANH_SACH_ODER = "danhsachoder";
    private final static String THOI_GIAN = "thoigian";
    private final static String MONTH = "month";
    private final static String YEAR = "year";
    private final static String MINUTES = "minutes";
    private final static String SECONDS = "seconds";
    private final static String HOURS = "hours";
    private final static String DATE = "date";
    private final static  Query allBan = FirebaseDatabase.getInstance().getReference("bepbar");

    private DatabaseReference mDatabase;
    private ArrayList<item_tapped> item_tappeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bep_bar);
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new CustomArrayBepBar(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();



        allBan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String tenBan = "";
                String khuVuc = "";
                danhSachSanPhamOders = new ArrayList<>();
                //Log.d("Test", danhSachSanPhamOders.size()+"");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String gio = snapshot.child(THOI_GIAN).child(HOURS).getValue() + "";
                    String phut = snapshot.child(THOI_GIAN).child(MINUTES).getValue() + "";
                    String giay = snapshot.child(THOI_GIAN).child(SECONDS).getValue() + "";
                    String ngay = snapshot.child(THOI_GIAN).child(DATE).getValue() + "";
                    String thang = snapshot.child(THOI_GIAN).child(MONTH).getValue() + "";
                    String nam = (Integer.parseInt(snapshot.child(THOI_GIAN).child(YEAR).getValue() + "")+1900)+"";


                    //
                    tenBan = snapshot.child(TEN_BAN).getValue()+"";
                    khuVuc = snapshot.child(KHU_VUC).getValue()+"";



                    ArrayList<SanPhamOder> sanPham = new ArrayList<>();
                    for (DataSnapshot snap: snapshot.child(DANH_SACH_ODER).getChildren()){
                        Double soLuong = Double.parseDouble(snap.getValue()+"");
                        sanPham.add(new SanPhamOder(snap.getKey(), soLuong, tenBan +"_" + khuVuc));
                    }
                    String sDate = ngay+"-"+thang+"-"+nam+" "+gio+":"+phut+":"+giay;

                    SimpleDateFormat formatter4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = formatter4.parse(sDate);
                        //Log.d("Test", date+"");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    DanhSachSanPhamOder danhSachSanPhamOder = new DanhSachSanPhamOder(tenBan, khuVuc, date, sanPham);
                    danhSachSanPhamOders.add(danhSachSanPhamOder);
                }

                for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
                    SanPhamOder sp = new SanPhamOder(danhSachSanPhamOders.get(i).getTenban() + "_" + danhSachSanPhamOders.get(i).getKhuVuc());

                    mAdapter.addSectionHeaderItem(sp);
                    for (int j = 0; j < danhSachSanPhamOders.get(i).getListSP().size(); j++) {
                        mAdapter.addItem(danhSachSanPhamOders.get(i).getListSP().get(j));
                    }
                }

                listView.setAdapter(mAdapter);
                ArrayList<item_tapped> item_tappeds = new ArrayList<>();
                viTriTapped(item_tappeds);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mDatabase = FirebaseDatabase.getInstance().getReference("bepbar");
                        for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
                            if (position == item_tappeds.get(i).getViTri()) {
                                mDatabase.child(item_tappeds.get(i).getBanKhuVuc()).removeValue();
                                mAdapter.notifyDataSetChanged();
                                break;
                            }
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void viTriTapped(ArrayList<item_tapped> item_tappeds) {
        int a = 0;
        for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
            item_tappeds.add(new item_tapped(danhSachSanPhamOders.get(i).getTenban() + "_" + danhSachSanPhamOders.get(i).getKhuVuc(), a));
            a += danhSachSanPhamOders.get(i).getListSP().size() + 1;
        }
    }

    private void load(){
        allBan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String tenBan = "";
                String khuVuc = "";
                danhSachSanPhamOders = new ArrayList<>();
                //Log.d("Test", danhSachSanPhamOders.size()+"");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String gio = snapshot.child(THOI_GIAN).child(HOURS).getValue() + "";
                    String phut = snapshot.child(THOI_GIAN).child(MINUTES).getValue() + "";
                    String giay = snapshot.child(THOI_GIAN).child(SECONDS).getValue() + "";
                    String ngay = snapshot.child(THOI_GIAN).child(DATE).getValue() + "";
                    String thang = snapshot.child(THOI_GIAN).child(MONTH).getValue() + "";
                    String nam = (Integer.parseInt(snapshot.child(THOI_GIAN).child(YEAR).getValue() + "")+1900)+"";


                    //
                    tenBan = snapshot.child(TEN_BAN).getValue()+"";
                    khuVuc = snapshot.child(KHU_VUC).getValue()+"";



                    ArrayList<SanPhamOder> sanPham = new ArrayList<>();
                    for (DataSnapshot snap: snapshot.child(DANH_SACH_ODER).getChildren()){
                        Double soLuong = Double.parseDouble(snap.getValue()+"");
                        sanPham.add(new SanPhamOder(snap.getKey(), soLuong, tenBan +"_" + khuVuc));
                    }
                    String sDate = ngay+"-"+thang+"-"+nam+" "+gio+":"+phut+":"+giay;

                    SimpleDateFormat formatter4 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    Date date = new Date();
                    try {
                        date = formatter4.parse(sDate);
                        //Log.d("Test", date+"");
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    DanhSachSanPhamOder danhSachSanPhamOder = new DanhSachSanPhamOder(tenBan, khuVuc, date, sanPham);
                    danhSachSanPhamOders.add(danhSachSanPhamOder);
                }

                for (int i = 0; i < danhSachSanPhamOders.size(); i++) {
                    SanPhamOder sp = new SanPhamOder(danhSachSanPhamOders.get(i).getTenban() + "_" + danhSachSanPhamOders.get(i).getKhuVuc());

                    mAdapter.addSectionHeaderItem(sp);
                    for (int j = 0; j < danhSachSanPhamOders.get(i).getListSP().size(); j++) {
                        mAdapter.addItem(danhSachSanPhamOders.get(i).getListSP().get(j));
                    }
                }

                listView.setAdapter(mAdapter);
                ArrayList<item_tapped> item_tappeds = new ArrayList<>();
                viTriTapped(item_tappeds);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}