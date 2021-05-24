package com.example.doandidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import com.example.doandidong.data.Admin;

public class LoginActivity extends AppCompatActivity {

    private TextView textView;
    private Button btnLogin;
    private EditText edtEmail, edtPassword;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    DatabaseReference mData = FirebaseDatabase.getInstance().getReference();
    FirebaseAuth mFirebaseAuth;
    FirebaseFirestore firebaseFirestore;
    Intent intent;


    private static final String ADMIN = "admin";
    private static final String USER = "user";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        mFirebaseAuth = FirebaseAuth.getInstance();

        textView = findViewById(R.id.registrationOfLogin);
        btnLogin = findViewById(R.id.btnLogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        ArrayList<Admin> admins = new ArrayList<Admin>();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (mFirebaseUser != null) {
                    Boolean kiemTraAdmin = kiemTraUserAdmin(admins);

                    if(kiemTraAdmin == true){
                        Toast.makeText(LoginActivity.this, "You are logged in", Toast.LENGTH_SHORT).show();
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Yes", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();

                if (email.isEmpty()) {
                    edtEmail.setError("Plese enter email id");
                    edtEmail.requestFocus();
                } else if (pass.isEmpty()) {
                    edtPassword.setError("Plese enter your password");
                    edtPassword.requestFocus();
                } else if (email.isEmpty() && pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fialds Are Empty!", Toast.LENGTH_LONG).show();
                } else if (!(email.isEmpty() && pass.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginActivity.this, "Login Error, Plese login Again!", Toast.LENGTH_LONG).show();
                            } else {
                                Boolean kiemTraAdmin = kiemTraUserAdmin(admins);
                                if(kiemTraAdmin == true){
                                    intent = new Intent(LoginActivity.this, MainActivity.class);
                                    Toast.makeText(LoginActivity.this, "Admin", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(LoginActivity.this, "Yes yes", Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "Error Occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public Boolean kiemTraUserAdmin(ArrayList<Admin> admins){
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("user");
        Boolean kiemTra = false;
        reference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    QuerySnapshot snapshots = task.getResult();
                    ArrayList<Admin> item = new ArrayList<>();
                    for(QueryDocumentSnapshot doc : snapshots){
                        Admin admin = new Admin();
                        admin.setEmail(doc.get("email").toString());
                        admin.setAdmin(doc.get("admin").toString());
                        admins.add(admin);
                    }
                }
            }
        });

        returnFor: for(Admin ad : admins){
            if(ad.getEmail().equals(edtEmail.getText().toString())){
                if (ad.getAdmin().equals("1")){
                    kiemTra = true;
                    break returnFor;
                }
            }
        }

        return kiemTra;
    }

    int i = 0;

    @Override
    public void onBackPressed() {
        i++;
        if (i == 2) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();
        }
    }
}