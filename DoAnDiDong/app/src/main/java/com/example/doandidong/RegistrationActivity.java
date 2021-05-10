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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private TextView textView;
    private EditText edtEmail, edtPassword;
    private Button btnRegistration;

    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_activity);

        textView =  findViewById(R.id.loginOfRegistration);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnRegistration = findViewById(R.id.btnRegstration);

        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPassword.getText().toString();

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
                                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
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
}
