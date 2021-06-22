package com.example.doandidong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ThemPhongBanActivity extends AppCompatActivity {

    private EditText tenBan;
    private RadioButton checkKhuVuc1, checkKhuVuc2, checkKhuVuc3;
    private Button themBan;
    private FirebaseFirestore firebaseFirestore;
    private int KHU_VUC_1 = 1;
    private int KHU_VUC_2 = 2;
    private int KHU_VUC_3 = 3;
    private Boolean TRANG_THAI = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themphongban);

        tenBan = findViewById(R.id.edtBan);
        checkKhuVuc1 = findViewById(R.id.checkKhuVuc1);
        checkKhuVuc2 = findViewById(R.id.checkKhuVuc2);
        checkKhuVuc3 = findViewById(R.id.checkKhuVuc3);
        themBan = findViewById(R.id.btnTaoBan);
        firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference reference = firebaseFirestore.collection("phongban");
     themBan.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Map<String, Object> item = new HashMap<>();
             final int[] khuvuc = {KHU_VUC_1,KHU_VUC_2,KHU_VUC_3};
             item.put("tenban",tenBan.getText().toString());
             if(checkKhuVuc1.isChecked()){
                 khuvuc[0] = KHU_VUC_1;
             }
             else if(checkKhuVuc2.isChecked()){
                 khuvuc[0] = KHU_VUC_2;
             }
             else {
                 khuvuc[0] = KHU_VUC_3;
             }
             item.put("khuvuc",khuvuc[0]);
             item.put("trangthai",TRANG_THAI);
             reference.add(item);
         }
     });

    }
}
