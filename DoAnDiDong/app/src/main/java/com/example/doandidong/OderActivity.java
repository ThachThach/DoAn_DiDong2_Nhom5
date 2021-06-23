package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.doandidong.adapte.RecyclerViewAdapterOder;
import com.example.doandidong.data.DanhSachSanPhamOder;
import com.example.doandidong.data.NhanVien;
import com.example.doandidong.data.NhomSanPham;
import com.example.doandidong.data.SanPham;
import com.example.doandidong.data.SanPhamOder;
import com.example.doandidong.data.ThuChi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

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
    private NhomSanPham nhomSanPham;
    private SanPham sanPham;
    private FirebaseFirestore firebaseFirestore;
    private CollectionReference reference;
    private CollectionReference reference1;
    private CollectionReference reference2;
    private ThuChi thuChi;
    private ArrayList<ThuChi> thuChiArrayList;

    ArrayList<String> tenNhom = new ArrayList<>();
    private ArrayList<SanPhamOder> listData;
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
        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("nhomsanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot snapshots = task.getResult();

                    tenNhom = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : snapshots){
                        nhomSanPham = new NhomSanPham();
                        String name = nhomSanPham.setTenNhom(doc.get("tennhomsanpham").toString());
                        tenNhom.add(name);
                    }
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(OderActivity.this, android.R.layout.simple_list_item_1, tenNhom);
                    listView.setAdapter(arrayAdapter);
                }
            }
        });
        reference1 = firebaseFirestore.collection("sanpham");
        reference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot snapshots = task.getResult();

                    listData = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : snapshots){
                        sanPham = new SanPham();
                        String name = sanPham.setTenSanpham(doc.get("tensanpham").toString());
                        String nhom = sanPham.setNhomSanPham(doc.get("nhomsanpham").toString());
                        Double von = sanPham.setVonSanPham(Double.parseDouble(doc.get("von").toString()));
                        Double gia = sanPham.setGiaSanpham(Double.parseDouble(doc.get("giasanpham").toString()));
                        listData.add(new SanPhamOder(nhom,name,von,gia));
                    }
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

                }
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
        reference2 = firebaseFirestore.collection("thuchi");
        Date date = new Date();
        Double giavon = 0.0;
        Double Tongban = 0.0;
        Double Tongvon = 0.0;
        Double giaban = 0.0;
        SanPhamOder sanPhamOder = new SanPhamOder();
        DanhSachSanPhamOder danhSachSanPhamOder1 = new DanhSachSanPhamOder(ban,khuVuc, date, sanPhamOders);
        String id = ban+"_"+khuVuc;

        mFirebaseDatabase.child(id).child(TEN_BAN).setValue(danhSachSanPhamOder1.getTenban());
        mFirebaseDatabase.child(id).child(KHU_VUC).setValue(danhSachSanPhamOder1.getKhuVuc());
        mFirebaseDatabase.child(id).child(THOI_GIAN).setValue(Calendar.getInstance().getTime());
        for(int i = 0; i < danhSachSanPhamOder1.getListSP().size(); i++) {
            if(danhSachSanPhamOder1.getListSP().get(i).getSoLuong() >= 0) {
                mFirebaseDatabase.child(id).child(DANH_SACH_ODER).child(danhSachSanPhamOder1.getListSP().get(i).getTenSP()).setValue(danhSachSanPhamOder1.getListSP().get(i).getSoLuong());
                giaban = danhSachSanPhamOder1.getListSP().get(i).getGiaBan() * danhSachSanPhamOder1.getListSP().get(i).getSoLuong();
                Tongban+=giaban;
                giavon = danhSachSanPhamOder1.getListSP().get(i).getGiaVon() * danhSachSanPhamOder1.getListSP().get(i).getSoLuong();
                Tongvon+=giavon;
            }
        }
        Log.d("test1",Tongban+"");
        Log.d("test2",Tongvon+"");
        Double finalTongban = Tongban;
        Double finalTongvon  = Tongvon;
        reference2.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshots = task.getResult();
                    thuChiArrayList = new ArrayList<>();
                    ArrayList<String> List1 = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : snapshots) {
                        thuChi = new ThuChi();
                        Double tongthuchi = Double.parseDouble(doc.get("tongthu").toString());
                        Double tongvon = Double.parseDouble(doc.get("tongvon").toString());
                        String idThuChi = doc.getId();
                        thuChiArrayList.add(new ThuChi(tongthuchi,tongvon,idThuChi));
                    }

                    for (int y = 0; y < thuChiArrayList.size(); y++){
                        if (thuChiArrayList.get(y).getId().equals("thang"+(date.getMonth()+1))){
                            DocumentReference contrac = reference2.document(thuChiArrayList.get(y).getId());
                            contrac.update("tongthu",thuChiArrayList.get(y).getTongThu()+finalTongban);
                            contrac.update("tongvon",thuChiArrayList.get(y).getTongThu()+finalTongvon);
                        }
                    }
                }
            }
        });
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

    private void getDataSanPham() {
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

    private void getOder(String ban, String khuVuc){
        Date date = new Date();
        DanhSachSanPhamOder danhSachSanPhamOder1 = new DanhSachSanPhamOder(ban,khuVuc, date, sanPhamOders);

        for(int i = 0; i < danhSachSanPhamOder1.getListSP().size(); i++) {
            if(danhSachSanPhamOder1.getListSP().get(i).getSoLuong() >= 0) {

            }
        }
    }
}