package com.example.doandidong;

import android.os.Bundle;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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
    private static final String CASANG = "sang";
    private static final String CACHIEU = "chieu";
    private static final String CATOI = "toi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_vien);
        mFirebaseAuth = FirebaseAuth.getInstance();

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
        mFirebaseAuth = FirebaseAuth.getInstance();

        user = FirebaseAuth.getInstance().getCurrentUser();
        mData = FirebaseDatabase.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("nhanvien");

        String mail = "";
        if (user != null) {
            mail = user.getEmail();
        }

        final String email_admin = "";

        final String[] chucVu = {QUANLY, THUNGAN, BEPBAR, PHUCVU};
        final String[] caLamViec = {CASANG};

        btnTaoNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();
                mFirebaseAuth = FirebaseAuth.getInstance();

                if(email.isEmpty()){
                    edtEmail.setError("Plese enter email id");
                    edtEmail.requestFocus();
                }else if(pass.isEmpty()){
                    edtPassword.setError("Plese enter your password");
                    edtPassword.requestFocus();
                }else if(email.isEmpty() && pass.isEmpty()){
                    Toast.makeText(ThemNhanVien.this,"Fialdssss Are Empty!", Toast.LENGTH_LONG).show();
                }else if(!(email.isEmpty() && pass.isEmpty())){

                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(ThemNhanVien.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(ThemNhanVien.this, "SignUp UnSuccessful, plese Try Again ", Toast.LENGTH_SHORT).show();
                            } else {

                            }
                            if (checkBepBar.isChecked()) {
                                chucVu[0] = BEPBAR;
                            } else if (checkThuNgan.isChecked()) {
                                chucVu[0] = THUNGAN;
                            } else if (checkQuanLy.isChecked()) {
                                chucVu[0] = QUANLY;
                            } else if (checkPhucVu.isChecked()) {
                                chucVu[0] = PHUCVU;
                            }

                            ArrayList<String> listCaLamViec = new ArrayList<String>();

                            if (checkBoxCaSang.isChecked()) {
                                listCaLamViec.add(CASANG);
                            }
                            if (checkBoxCaChieu.isChecked()) {
                                listCaLamViec.add(CACHIEU);
                            }
                            if (checkBoxCaToi.isChecked()) {
                                listCaLamViec.add(CATOI);
                            }

                            CollectionReference referenceNhanVien = firebaseFirestore.collection("nhanvien");
                            Map<String, Object> nhanvien = new HashMap<>();
                            NhanVien nv = new NhanVien(edtEmail.getText().toString(), chucVu[0], email_admin, edtTenNhanVien.getText().toString(), caLamViec[0]);

                            nhanvien.put("email", nv.getEmail());
                            nhanvien.put("chucvu", nv.getChucvu());
                            nhanvien.put("name", nv.getName());
                            if (chucVu[0].equals(QUANLY)) {
                                nhanvien.put("calam", "CLadmin");
                            } else {
                                nhanvien.put("calam", nv.getCalam());
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
                        }
                    });}else {
                    Toast.makeText(ThemNhanVien.this,"Error Occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

