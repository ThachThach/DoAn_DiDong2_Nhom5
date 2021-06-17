package com.example.doandidong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NhomSanPhamMoi extends AppCompatActivity {
    private EditText editText;
    private Button button;
    private FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themnhomsanpham);
        editText = findViewById(R.id.edtnhomsanpham);
        button = findViewById(R.id.buttonLuuNhomSanPham);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("nhomsanpham");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> item = new HashMap<>();
                item.put("tennhomsanpham",editText.getText().toString());
                reference.add(item);
                Intent intent = new Intent();
                intent = new Intent(NhomSanPhamMoi.this,NhomSanPhamActivity.class);
                startActivity(intent);
            }
        });

    }
}
