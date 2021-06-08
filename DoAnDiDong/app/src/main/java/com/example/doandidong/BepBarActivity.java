package com.example.doandidong;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.adapte.Bepbar;
import com.example.doandidong.adapte.CustumArrayBepBar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BepBarActivity extends AppCompatActivity {
    private ListView listView;
    private CustumArrayBepBar custumArrayBepBar;
    private ArrayList<Bepbar> bepbarArrayList;
    private DatabaseReference mDatabase;
    private Bepbar bepbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bep_bar);
        listView = findViewById(R.id.list_item_bepbar);
        bepbarArrayList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference("bepbar");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot snapshot) {
                bepbar = snapshot.getValue(Bepbar.class);
                String tensanpham = bepbar.getTensanpham();
                String tenban = bepbar.getTenban();
                String khuvuc = bepbar.getKhuvuc();
                String nguoioder = bepbar.getTennguoioder();
                int soluong = bepbar.getSoluong();
                String thoigian = bepbar.getThoigian();
                bepbarArrayList.add(new Bepbar(nguoioder,tenban,thoigian,soluong,tensanpham,khuvuc));
                custumArrayBepBar = new CustumArrayBepBar(BepBarActivity.this,R.layout.item_bepbar,bepbarArrayList);
                listView.setAdapter(custumArrayBepBar);
            }
            @Override
            public void onCancelled( DatabaseError error) {

            }
        });


    }
}
