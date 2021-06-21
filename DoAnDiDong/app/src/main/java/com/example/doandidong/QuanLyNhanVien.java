package com.example.doandidong;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.doandidong.data.NhanVien;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class QuanLyNhanVien extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private FirebaseFirestore firebaseFirestore;
    private ArrayList<String> danhsachNhanVien = new ArrayList<>();
    private ArrayList<NhanVien> danhsachNhanVien1 = new ArrayList<>();
    private ListView list;
    private CollectionReference reference;
    private  ArrayList<NhanVien> listNV ;
    private NhanVien nhanVien;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_nhan_vien);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        constraintLayout = findViewById(R.id.menuCoontext);

        firebaseFirestore = FirebaseFirestore.getInstance();

    /*    firebaseFirestore.collection("users").get().then((querySnapshot) => {
                querySnapshot.forEach((doc) = > {
                        console.log(`${doc.id} = > ${doc.data()}`);
                }
        });*/


        firebaseFirestore.collection("nhanvien").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    listNV = new ArrayList<>();

                    for (QueryDocumentSnapshot document : task.getResult()) {
                        nhanVien = new NhanVien();
                        nhanVien.setName(document.get("name").toString());
                        nhanVien.setId(document.getId());
                        listNV.add(nhanVien);
                    }
                }
                for (NhanVien nv : listNV){
                    danhsachNhanVien.add(nv.getName());
                }

                list = findViewById(R.id.listViewNhanVien);

                FloatingActionButton fab = findViewById(R.id.fab);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar.make(view, "Thêm nhân viên mới", Snackbar.LENGTH_LONG)
                                .setAction("Thêm", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent();

                                        intent = new Intent(QuanLyNhanVien.this, ThemNhanVien.class);
                                        startActivity(intent);
                                    }
                                }).show();
                    }
                });

                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(QuanLyNhanVien.this, android.R.layout.simple_list_item_1, danhsachNhanVien);
                list.setAdapter(itemsAdapter);
                //registerForContextMenu(list);

                list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                        //final int which_item = position;

                        new AlertDialog.Builder(QuanLyNhanVien.this)
                                .setIcon(android.R.drawable.btn_plus)
                                .setTitle("Are you sure ?")
                                .setMessage("Do you want to delete this item")
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        firebaseFirestore = FirebaseFirestore.getInstance();
                                        reference = firebaseFirestore.collection("nhanvien");


                                            reference.document(listNV.get(position).getId()).delete();
                                        itemsAdapter.notifyDataSetChanged();
                                    }
                                })
                                .setNegativeButton("No", null)
                                .show();
                        return  true;
                    }
                });
            }
        });
    }


}