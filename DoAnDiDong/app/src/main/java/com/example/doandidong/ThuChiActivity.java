package com.example.doandidong;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.doandidong.adapte.CustomArrayThuChi;
import com.example.doandidong.data.NhanVien;
import com.example.doandidong.data.ThuChi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ThuChiActivity extends AppCompatActivity {
    private ArrayList<ThuChi> listThuChi;
    private ListView listViewThuChi;
    private CustomArrayThuChi customArrayThuChi;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_chi);

        listViewThuChi = findViewById(R.id.lvThuChi);

        firebaseFirestore = FirebaseFirestore.getInstance();

        CollectionReference db = firebaseFirestore.collection("thuchi");

        listThuChi = new ArrayList<>();


        load(listThuChi, db);

        Log.d("TEST", listThuChi.size()+"");

        Button button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TEST", listThuChi.size()+"");
            }
        });
//            try {

//                Thread.sleep(500);
//
//                    Log.d("TEST", listThuChi.size()+"");
//            } catch (InterruptedException e) {
//                Log.d("TEST", e+"");
//            }
    }

    public void load(ArrayList<ThuChi> listThuChi, CollectionReference db){
        db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        ThuChi thuChi = new ThuChi();
                        thuChi.setTongThu(Double.parseDouble(document.getData().get("tongthu").toString()));
                        thuChi.setTongVon(Double.parseDouble(document.getData().get("tongvon").toString()));
                        thuChi.setId(document.getId());

                        listThuChi.add(thuChi);
                        //Log.d("TESTABC", document.getId() +"" + document.getData());
                    }

                    ArrayList<ThuChi> mThuChi = new ArrayList<>();
                    mThuChi.add(listThuChi.get(0));
                    mThuChi.add(listThuChi.get(4));
                    mThuChi.add(listThuChi.get(5));
                    mThuChi.add(listThuChi.get(6));
                    mThuChi.add(listThuChi.get(7));
                    mThuChi.add(listThuChi.get(8));
                    mThuChi.add(listThuChi.get(9));
                    mThuChi.add(listThuChi.get(10));
                    mThuChi.add(listThuChi.get(11));
                    mThuChi.add(listThuChi.get(1));
                    mThuChi.add(listThuChi.get(2));
                    mThuChi.add(listThuChi.get(3));


                    customArrayThuChi = new CustomArrayThuChi(ThuChiActivity.this, R.layout.item_thuchi, mThuChi);
                    listViewThuChi.setAdapter(customArrayThuChi);

                }else{
                    Log.d("THUCHI", "Khong lay duoc du lieu thu chi");
                }
            }
        });
        try {

                Thread.sleep(1000);

                    Log.d("TEST", listThuChi.size()+"");
            } catch (InterruptedException e) {
                Log.d("TEST", e+"");
            }
    }
}