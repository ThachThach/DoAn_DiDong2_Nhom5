package com.example.doandidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doandidong.adapte.RecyclerViewAdapterOder;
import com.example.doandidong.data.DanhSachSanPhamOder;
import com.example.doandidong.data.NhanVien;
import com.example.doandidong.data.SanPhamOder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OderActivity extends AppCompatActivity {

    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    private FirebaseFirestore mFirebaseFirestore;

    private final static String TEN_BAN = "tenban";
    private final static String KHU_VUC = "khuvuc";
    private final static String TEN_SAN_PHAM = "tensanpham";
    private final static String SO_LUONG = "soluong";
    private final static String THOI_GIAN = "thoigian";
    private final static String DANH_SACH_ODER = "danhsachoder";

    public final static String KEY_TEN_BAN = "KEY_TEN_BAN";
    public final static String KEY_KHU_VUC = "KEY_KHU_VUC";
    private ArrayList<SanPhamOder> sanPhamOders;

    private RecyclerView recyclerView;
    private RecyclerViewAdapterOder myAdapterA;
    public Button btnXong;
    private ListView listView;

    ArrayList<String> tenNhom = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("bepbar");
        mFirebaseFirestore = mFirebaseFirestore.getInstance();

        btnXong = findViewById(R.id.btnXong);
        listView = findViewById(R.id.listViewNhomSanPham);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String tenBan = bundle.getString(KEY_TEN_BAN);
        String khuVuc = bundle.getString(KEY_KHU_VUC);

        tenNhom.add("Nhom 1");
        tenNhom.add("Nhom 2");
        tenNhom.add("nhom 3");

        ArrayList<SanPhamOder> listData = new ArrayList<>();
        listData.add(new SanPhamOder("Nhom 1","San pham 1",10000,20000));
        listData.add(new SanPhamOder("Nhom 1","San pham 2",10000,20000));
        listData.add(new SanPhamOder("Nhom 1","San pham 3",10000,20000));
        listData.add(new SanPhamOder("Nhom 1","San pham 4",10000,20000));

        listData.add(new SanPhamOder("Nhom 2","San pham 5",10000,20000));
        listData.add(new SanPhamOder("Nhom 2","San pham 6",10000,20000));
        listData.add(new SanPhamOder("Nhom 2","San pham 7",10000,20000));
        listData.add(new SanPhamOder("Nhom 2","San pham 8",10000,20000));

        listData.add(new SanPhamOder("nhom 3","San pham 7",10000,20000));
        listData.add(new SanPhamOder("nhom 3","San pham 8",10000,20000));
        listData.add(new SanPhamOder("nhom 3","San pham 9",10000,20000));
        listData.add(new SanPhamOder("nhom 3","San pham 10",10000,20000));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tenNhom);
        listView.setAdapter(arrayAdapter);
        recyclerView = findViewById(R.id.sanpham_list);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sanPhamOders = new ArrayList<>();
                for(int i = 0; i <listData.size(); i++){
                    if(tenNhom.get(position).equals(listData.get(i).getNhomSanPham())){
                        sanPhamOders.add(listData.get(i));
                    }
                }
                myAdapterA = new RecyclerViewAdapterOder(OderActivity.this ,sanPhamOders);
                recyclerView.setAdapter(myAdapterA);
                recyclerView.setLayoutManager(new LinearLayoutManager(OderActivity.this));
            }
        });

        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sanPhamOders.size() != 0) {
                    OderNew(tenBan, khuVuc);
                    Toast.makeText(OderActivity.this, "Đã lưu",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(OderActivity.this, "Không thể lưu",Toast.LENGTH_SHORT).show();
                }
            }
        });

        DatabaseReference all = FirebaseDatabase.getInstance().getReference();
    }

    private void OderNew(String ban, String khuVuc){
            Date date = new Date();
            DanhSachSanPhamOder danhSachSanPhamOder1 = new DanhSachSanPhamOder(ban,khuVuc, date, sanPhamOders);
            String id = ban+"_"+khuVuc;

            mFirebaseDatabase.child(id).child(TEN_BAN).setValue(danhSachSanPhamOder1.getTenban());
            mFirebaseDatabase.child(id).child(KHU_VUC).setValue(danhSachSanPhamOder1.getKhuVuc());
            mFirebaseDatabase.child(id).child(THOI_GIAN).setValue(Calendar.getInstance().getTime());
            for(int i =0; i < danhSachSanPhamOder1.getListSP().size(); i++) {
                if(danhSachSanPhamOder1.getListSP().get(i).getSoLuong() >= 0) {
                    String id2 = mFirebaseDatabase.push().getKey();
                    mFirebaseDatabase.child(id).child(DANH_SACH_ODER).child(danhSachSanPhamOder1.getListSP().get(i).getTenSP()).child(SO_LUONG).setValue(danhSachSanPhamOder1.getListSP().get(i).getSoLuong());
                }
            }
    }


    private ArrayList<SanPham> getSanPham(){


        return null;
    }

    private String getDataBepBerRealTime(String ban, String khuVuc, String tenSanPham){
        final String[] key = {""};
        mFirebaseDatabase.child(ban+"_"+khuVuc).child(DANH_SACH_ODER).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    key[0] = task.getResult().child(tenSanPham).child(SO_LUONG).getValue().toString();
                }
            }
        });
        return key[0];
    }

    private  void removeSanPhamBepBar(String ban, String khuVuc, String key, String tenSanPham){
        mFirebaseDatabase.child(ban+"_"+khuVuc).child(DANH_SACH_ODER).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    if (Integer.parseInt(task.getResult().child(tenSanPham).child(SO_LUONG).getValue()+"") == 0){
                        mFirebaseInstance.getInstance().getReference("bepbar").child(DANH_SACH_ODER).child(tenSanPham).removeValue();
                    }
                }
            }
        });
    }

    private ArrayList<SanPham> getDataSanPham(){
        mFirebaseFirestore.collection("").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String email_admin1 = "" + document.getData().get("email_admin");
                        String email1 = "" + document.getData().get("email");
                        String chucvu1 = "" + document.getData().get("chucvu");
                        String name1 = "" + document.getData().get("name");
                        NhanVien nv = new NhanVien(email1, chucvu1, email_admin1, name1);
                    }
                }
            }
        });
    }


}

/* int finalI = i;
                mFirebaseDatabase.child(ban+"_"+khuVuc).child(DANH_SACH_ODER).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()) {
                           String key = task.getResult().child(danhSachSanPhamOder1.getListSP().get(finalI).getTenSP()).child(SO_LUONG).getValue().toString();

                            if (Integer.parseInt(task.getResult().child(danhSachSanPhamOder1.getListSP().get(finalI).getTenSP()).child(SO_LUONG).getValue()+"") == 0){
                                mFirebaseInstance.getInstance().getReference("bepbar").child(DANH_SACH_ODER).child(key).removeValue();
                            }
                        }
                    }
                });*/

//removeSanPhamBepBar(ban, khuVuc, key,danhSachSanPhamOder1.getListSP().get(i).getTenSP());