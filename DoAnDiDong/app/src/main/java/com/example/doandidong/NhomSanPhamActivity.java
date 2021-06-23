package com.example.doandidong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.adapte.CusTomArrayNhomSanPham;
import com.example.doandidong.data.NhomSanPham;
import com.example.doandidong.data.SanPham;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NhomSanPhamActivity extends AppCompatActivity {
    private ListView listViewNhomSanPham;
    private ArrayList<NhomSanPham> arrayList;
    private CusTomArrayNhomSanPham cusTomArrayNhomSanPham;
    private FirebaseFirestore firebaseFirestore;
    private  NhomSanPham nhomSanPham;
    private CheckBox checkBoxNhomSanPham;
    private  CollectionReference reference;
    private  CollectionReference reference1;
    private ArrayList<NhomSanPham> listID;
    private ArrayList<SanPham> arrayListSanPham;
    private SanPham sanpham;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhomsanpham);
        listViewNhomSanPham  = findViewById(R.id.list_item_nhomsanpham);
        checkBoxNhomSanPham = findViewById(R.id.checkNhomsanpham);
        getData();
        FloatingActionButton floatingActionButton = findViewById(R.id.themnhomsanpham);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Thêm nhom san pham  mới", Snackbar.LENGTH_LONG)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mennuxoasua, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.removeMN:
                remove();
                break;
            case R.id.updateMN:
                update();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void remove(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        reference1 = firebaseFirestore.collection("sanpham");
        reference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshots = task.getResult();
                    arrayListSanPham = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : snapshots) {
                        sanpham = new SanPham();
                        sanpham.setTenSanpham(doc.get("tensanpham").toString());
                        sanpham.setNhomSanPham(doc.get("nhomsanpham").toString());
                        sanpham.setIdSanPham(doc.getId());
                        sanpham.setVonSanPham(Double.parseDouble(doc.get("von").toString()));
                        sanpham.setGiaSanpham(Double.parseDouble(doc.get("giasanpham").toString()));
                        sanpham.setMaSanPham(doc.get("masanpham").toString());
                        sanpham.setImage(R.drawable.hinhanh);
                        arrayListSanPham.add(sanpham);
                    }
                    for(int i = 0; i < arrayList.size(); i++ ){
                        if (arrayList.get(i).getCheck()) {
                            for(int y = 0; y < arrayListSanPham.size();y++){
                                if (arrayList.get(i).getTenNhom().equals(arrayListSanPham.get(y).getNhomSanPham())){
                                    reference.document(arrayList.get(i).getIdNhomSanPham()).delete();
                                    reference1.document(arrayListSanPham.get(y).getIdSanPham()).delete();
                                }
                            }
                        }
                    }
                    getData();
                }
            }
        });


    }

    private void update(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.dailong_updatenhomsanpham, null);
        builder.setView(customLayout);
        EditText textupdatenhomsanpham = customLayout.findViewById(R.id.edtupdatenhomsanpham);

        for(int i = 0; i<arrayList.size();i++){
            if (arrayList.get(i).getCheck()){
                textupdatenhomsanpham.setText(arrayList.get(i).getTenNhom());

                builder.setNegativeButton("Thoat", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getData();
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        for (int i = 0;i<arrayList.size();i++){
                            if (arrayList.get(i).getCheck()){
                                DocumentReference contrac = reference.document(arrayList.get(i).getIdNhomSanPham());
                                contrac.update("tennhomsanpham",textupdatenhomsanpham.getText().toString());
                            }
                        }
                        getData();
                    }
                });
                builder.create().show();
            }
        }
    }

    public void getData(){
        firebaseFirestore = FirebaseFirestore.getInstance();
        reference = firebaseFirestore.collection("nhomsanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot snapshots = task.getResult();
                    arrayList = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : snapshots){
                        nhomSanPham = new NhomSanPham();
                        nhomSanPham.setTenNhom(doc.get("tennhomsanpham").toString());
                        nhomSanPham.setImg(R.drawable.ic_launcher_background);
                        nhomSanPham.setIdNhomSanPham(doc.getId());
                        arrayList.add(nhomSanPham);
                    }
                    cusTomArrayNhomSanPham = new CusTomArrayNhomSanPham(NhomSanPhamActivity.this,R.layout.item_nhomsanpham,arrayList);
                    listViewNhomSanPham.setAdapter(cusTomArrayNhomSanPham);
                }
            }
        });

    }
}