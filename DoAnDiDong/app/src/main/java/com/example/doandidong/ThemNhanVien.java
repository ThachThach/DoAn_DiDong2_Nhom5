package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.data.NhanVien;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ThemNhanVien extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mData;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseUser user;

    private EditText edtTenNhanVien, edtEmail, edtPassword;
    private RadioGroup radioGroup;
    private RadioButton checkQuanLy, checkPhucVu, checkBepBar, checkThuNgan;
    private CheckBox checkBoxCaSang, checkBoxCaChieu, checkBoxCaToi;
    private Button btnTaoNhanVien;
    private static final String QUANLY = "quanly";
    private static final String PHUCVU = "phucvu";
    private static final String BEPBAR = "bepber";
    private static final String THUNGAN = "thungan";
    private  static final String CASANG = "sang";
    private static final String CACHIEU = "chieu";
    private  static  final  String CATOI = "toi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);

        edtTenNhanVien = findViewById(R.id.edtTenNhanVien);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        checkQuanLy = findViewById(R.id.checkQuanLy);
        checkBepBar = findViewById(R.id.checkBepBar);
        checkThuNgan = findViewById(R.id.checkThuNgan);
        checkPhucVu = findViewById(R.id.checkPhucVu);

        checkBoxCaSang = findViewById(R.id.checkCaSang);
        checkBoxCaChieu = findViewById(R.id.checkCaChieu);
        checkBoxCaToi = findViewById(R.id.checkCaToi);

        btnTaoNhanVien = findViewById(R.id.btnTaoUser);


        user = FirebaseAuth.getInstance().getCurrentUser();
        mData = FirebaseDatabase.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("nhanvien");


        String mail = "";
        if (user != null) {
            mail = user.getEmail();
        }

        String email_user = mail;
        final String[] email_admin = {""};
        String tenquan = "";
        ArrayList<NhanVien> listNhanVien = new ArrayList<NhanVien>();

        firebaseFirestore.collection("nhanvien").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String email_admin1 = "" + document.getData().get("email_admin");
                        String email1 = "" + document.getData().get("email");
                        String chucvu1 = "" + document.getData().get("chucvu");
                        String name1 = "" + document.getData().get("name");
                        NhanVien nv = new NhanVien(email1, chucvu1, email_admin1, name1);

                        listNhanVien.add(nv);

                    }
                    Toast.makeText(ThemNhanVien.this, listNhanVien.size() + "aaaa", Toast.LENGTH_SHORT).show();
                    for(NhanVien nv: listNhanVien){
                        if(nv.getEmail().equals(email_user)){
                            email_admin[0] = nv.getEmail_admin();
                            Log.d("test" , email_admin[0]);
                        }
                        //Toast.makeText(ThemNhanVien.this, nv.getEmail(), Toast.LENGTH_SHORT).show();
                    }

                    final String[] chucVu = {QUANLY,THUNGAN,BEPBAR,PHUCVU};
                    final String[] caLamViec = {CASANG};

                    btnTaoNhanVien.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(checkBepBar.isChecked()){
                                chucVu[0] = BEPBAR;
                            }else if(checkThuNgan.isChecked()){
                                chucVu[0] = THUNGAN;
                            }else if(checkQuanLy.isChecked()){
                                chucVu[0] = QUANLY;
                            }else if(checkPhucVu.isChecked()){
                                chucVu[0] = PHUCVU;
                            }

                            ArrayList<String> listCaLamViec = new ArrayList<String>();

                            if(checkBoxCaSang.isChecked()){
                                listCaLamViec.add(CASANG);
                            }
                            if(checkBoxCaChieu.isChecked()){
                                listCaLamViec.add(CACHIEU);
                            }
                            if(checkBoxCaToi.isChecked()){
                                listCaLamViec.add(CATOI);
                            }

                            CollectionReference referenceNhanVien= firebaseFirestore.collection("nhanvien");
                            Map<String, Object> nhanvien = new HashMap<>();
                            NhanVien nv = new NhanVien(edtEmail.getText().toString(), chucVu[0], email_admin[0], edtTenNhanVien.getText().toString(), caLamViec[0]);

                            nhanvien.put("email", nv.getEmail());
                            nhanvien.put("chucvu", nv.getChucvu());
                            nhanvien.put("email_admin", nv.getEmail_admin());
                            nhanvien.put("name", nv.getName());
                            if(chucVu[0].equals(QUANLY)){
                                nhanvien.put("calam", "CLadmin");
                            }else{
                                //nhanvien.put("calam", nv.getCalam());
                            }

                            reference.add(nhanvien).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(ThemNhanVien.this, "success!", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ThemNhanVien.this, "fail!", Toast.LENGTH_SHORT).show();
                                }
                            });
                            Intent intent = new Intent();
                            intent = new Intent(ThemNhanVien.this,QuanLyNhanVien.class);
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.w("test", "Error getting documents.", task.getException());
                }

            }
        });
    }
}