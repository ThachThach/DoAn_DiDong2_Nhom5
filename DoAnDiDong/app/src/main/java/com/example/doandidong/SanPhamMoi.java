package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SanPhamMoi extends AppCompatActivity {
    private ArrayList<NhomSanPham> arrayList;
    private Spinner spin;
    private FirebaseFirestore firebaseFirestore;
    private  NhomSanPham nhomSanPham;
    private ArrayList<String> List;
    private Sanpham sanpham;
    private EditText editTenSanPham;
    private EditText editGiaSanPham;
    private EditText editDonViTinh;
    private EditText editVon;
    private EditText editMaSanPham;
    private Button buttonLuu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_moi);

        spin = findViewById(R.id.spinnerSanPham);
        editTenSanPham = findViewById(R.id.ediTenSanPham);
        editDonViTinh = findViewById(R.id.editDonViTinh);
        editGiaSanPham = findViewById(R.id.editGiaSanPham);
        editVon = findViewById(R.id.editVon);
        editMaSanPham = findViewById(R.id.editMaSanPham);
        buttonLuu = findViewById(R.id.buttonLuuSanPham);


        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("nhomsanpham");
        CollectionReference reference1 = firebaseFirestore.collection("sanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot snapshots = task.getResult();
                    arrayList = new ArrayList<>();
                  List = new ArrayList<>();

                    for(QueryDocumentSnapshot doc : snapshots){
                        nhomSanPham = new NhomSanPham();
                        String name = nhomSanPham.setTenNhom(doc.get("tennhomsanpham").toString());
                        List.add(name);

                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(SanPhamMoi.this,R.layout.support_simple_spinner_dropdown_item,List);
                    adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                    spin.setAdapter(adapter);
                }
                buttonLuu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String, Object> item = new HashMap<>();
                        item.put("tensanpham",editTenSanPham.getText().toString());
                        item.put("giasanpham",editGiaSanPham.getText().toString());
                        item.put("donvitinh",editDonViTinh.getText().toString());
                        item.put("nhomsanpham",spin.getSelectedItem().toString());
                        item.put("von",editVon.getText().toString());
                        item.put("masanpham",editMaSanPham.getText().toString());
                        reference1.add(item);
                        Intent intent = new Intent();
                        intent = new Intent(SanPhamMoi.this,ActivitySanPham.class);
                        startActivity(intent);

                    }
                });
            }
        });
    }
}