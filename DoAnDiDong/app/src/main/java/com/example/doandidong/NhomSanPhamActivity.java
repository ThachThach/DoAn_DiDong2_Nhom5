package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.adapte.CusTomArrayNhomSanPham;
import com.example.doandidong.adapte.NhomSanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NhomSanPhamActivity extends AppCompatActivity {
    private ListView listViewNhomSanPham;
    private ArrayList<NhomSanPham> arrayList;
    private CusTomArrayNhomSanPham cusTomArrayNhomSanPham;
    private FirebaseFirestore firebaseFirestore;
    private  NhomSanPham nhomSanPham;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomsanpham);
        listViewNhomSanPham  = findViewById(R.id.list_item_nhomsanpham);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("nhomsanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot snapshots = task.getResult();
                    arrayList = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : snapshots){
                        nhomSanPham = new NhomSanPham();
                        nhomSanPham.setTenNhom(doc.get("tennhomsanpham").toString());
                        nhomSanPham.setImg(R.drawable.icon_delete);
                        arrayList.add(nhomSanPham);
                    }
                    cusTomArrayNhomSanPham = new CusTomArrayNhomSanPham(NhomSanPhamActivity.this,R.layout.item_nhomsanpham,arrayList);
                    listViewNhomSanPham.setAdapter(cusTomArrayNhomSanPham);
                }
            }
        });


        FloatingActionButton floatingActionButton = findViewById(R.id.themnhomsanpham);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Thêm san pham  mới", Snackbar.LENGTH_LONG)
                        .setAction("Thêm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent = new Intent(NhomSanPhamActivity.this, NhomSanPhamMoi.class);
                                startActivity(intent);
                            }
                        }).show();
            }

        });
    }
}
