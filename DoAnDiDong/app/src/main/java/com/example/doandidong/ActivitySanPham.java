package com.example.doandidong;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.adapte.CustomArraySanPham;
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

import java.util.ArrayList;

public class ActivitySanPham extends AppCompatActivity {

    private ListView listViewSanpham;
    private FirebaseFirestore firebaseFirestore;
    private SanPham sanpham;
    ArrayList<SanPham> arraySanPham;
    private  CollectionReference reference;
    private  CollectionReference reference1;
    private ArrayList<String> List;
    private  NhomSanPham nhomSanPham;
    private ArrayList<NhomSanPham> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sanpham);
        listViewSanpham = findViewById(R.id.sanphamid);

        //Hien thị danh sách
        getData();

        FloatingActionButton floatingActionButton = findViewById(R.id.themsanpham);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Thêm san pham  mới", Snackbar.LENGTH_LONG)
                        .setAction("Thêm", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent();
                                intent = new Intent(ActivitySanPham.this, SanPhamMoi.class);
                                startActivity(intent);
                                finish();

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
                ///Toast.makeText(this, arraySanPham.size() + "", Toast.LENGTH_SHORT).show();
                delete();
                break;
            case R.id.updateMN:
                update();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void delete() {
//
        getData();

        for (int i = 0; i < arraySanPham.size(); i++) {
            if (arraySanPham.get(i).getCheckSP()) {
                reference.document(arraySanPham.get(i).getIdSanPham()).delete();
            }
        }
    }

    public void update() {
        reference1 = firebaseFirestore.collection("nhomsanpham");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.dailong_updatesanpham, null);
        EditText tensanpham = customLayout.findViewById(R.id.ediTenSanPhamupdate);
        EditText gia = customLayout.findViewById(R.id.editGiaSanPhamupdate);
        EditText von = customLayout.findViewById(R.id.editVonupdate);
        EditText ma = customLayout.findViewById(R.id.editMaSanPhamupdate);
        Spinner nhom = customLayout.findViewById(R.id.spinnerSanPhamupdate);
        builder.setView(customLayout);
        for (int i = 0; i < arraySanPham.size(); i++) {
            if (arraySanPham.get(i).getCheckSP()) {
                tensanpham.setText(arraySanPham.get(i).getTenSanpham());
                gia.setText(arraySanPham.get(i).getGiaSanpham()+"");
                von.setText(arraySanPham.get(i).getVonSanPham()+"");
                ma.setText(arraySanPham.get(i).getMaSanPham());

                reference1.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            QuerySnapshot snapshots = task.getResult();
                            List = new ArrayList<>();
                            for(QueryDocumentSnapshot doc : snapshots){
                                nhomSanPham = new NhomSanPham();
                                String name = nhomSanPham.setTenNhom(doc.get("tennhomsanpham").toString());
                                List.add(name);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ActivitySanPham.this,R.layout.support_simple_spinner_dropdown_item,List);
                            adapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);
                            nhom.setAdapter(adapter);
                        }

                    }
                });

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
                        for(int i1 = 0; i1 < arraySanPham.size(); i1++){
                            if(arraySanPham.get(i1).getCheckSP()){
                                DocumentReference contrac = reference.document(arraySanPham.get(i1).getIdSanPham());
                                contrac.update("tensanpham",tensanpham.getText().toString());
                                contrac.update("giasanpham", gia.getText().toString());
                                contrac.update("masanpham",ma.getText().toString());
                                contrac.update("nhomsanpham", nhom.getSelectedItem().toString());
                                contrac.update("von",von.getText().toString());
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
        reference = firebaseFirestore.collection("sanpham");
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete( Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    QuerySnapshot snapshots = task.getResult();
                    arraySanPham = new ArrayList<>();
                    ArrayList<String> List1 = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : snapshots) {
                        sanpham = new SanPham();
                        sanpham.setTenSanpham(doc.get("tensanpham").toString());
                        sanpham.setNhomSanPham(doc.get("nhomsanpham").toString());
                        sanpham.setIdSanPham(doc.getId());
                        sanpham.setVonSanPham(Double.parseDouble(doc.get("von").toString()));
                        sanpham.setGiaSanpham(Double.parseDouble(doc.get("giasanpham").toString()));
                        sanpham.setMaSanPham(doc.get("masanpham").toString());
                        sanpham.setImage(R.drawable.hinhanh);
                        arraySanPham.add(sanpham);
                    }
                    CustomArraySanPham customArrayAdapter = new CustomArraySanPham(ActivitySanPham.this, R.layout.layout_item_sanpham, arraySanPham);
                    listViewSanpham.setAdapter(customArrayAdapter);
                }
            }
        });

    }
}