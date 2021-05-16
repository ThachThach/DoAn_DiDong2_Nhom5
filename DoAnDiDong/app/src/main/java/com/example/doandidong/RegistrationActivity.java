package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.data.Admin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private TextView textView;
    private EditText edtEmail, edtPassword;
    private Button btnRegistration;
    private Intent intent;
    private static final String admin = "1";

    FirebaseAuth mFirebaseAuth;
    DatabaseReference mData;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        textView =  findViewById(R.id.loginOfRegistration);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegistration = findViewById(R.id.btnRegstration);

        mData = FirebaseDatabase.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("user");

        btnRegistration.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(RegistrationActivity.this,"Fialds Are Empty!", Toast.LENGTH_LONG).show();
                }else if(!(email.isEmpty() && pass.isEmpty())){

                    mFirebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this, "SignUp UnSuccessful, plese Try Again ", Toast.LENGTH_SHORT).show();
                            }else{
                                Map<String, Object> userF = new HashMap<>();
                                Admin user = new Admin(email, admin);

                                userF.put("email", user.getEmail());
                                userF.put("admin", user.getAdmin());
                                reference.add(userF).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(RegistrationActivity.this, "success!", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegistrationActivity.this, "fail!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                intent = new Intent(RegistrationActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        }
                    });}else {
                    Toast.makeText(RegistrationActivity.this,"Error Occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    int i=0;

    @Override
    public void onBackPressed() {
        i++;

        if(i==2){
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
            finish();
        }
    }
}
