package com.example.doandidong;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.doandidong.adapte.CustumArrayBepBar;
import com.example.doandidong.data.Bepbar;
import com.example.doandidong.data.SanPhamOder;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BepBarActivity extends AppCompatActivity {
    private ListView listView;
    private ListView listView1;
    private CustumArrayBepBar custumArrayBepBar;
    private ArrayList<Bepbar> bepbarArrayList;
    private SanPhamOder bepbar;
    private final static String TEN_BAN = "tenban";
    private final static String KHU_VUC = "khuvuc";
    public final static String KEY_TEN_BAN = "KEY_TEN_BAN";
    public final static String KEY_KHU_VUC = "KEY_KHU_VUC";
    private final static String DANH_SACH_ODER = "danhsachoder";
    private final static String THOI_GIAN = "thoigian";
    private ArrayList<String> data;
    private ArrayList<Bepbar> data1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bepbar);
        listView = findViewById(R.id.listViewKhuVuc);
        listView = findViewById(R.id.listViewKhuVuc1);
        bepbarArrayList = new ArrayList<>();
        Query allBan = FirebaseDatabase.getInstance().getReference("bepbar");
        allBan.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                bepbarArrayList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String key = snapshot.getKey();
                    Bepbar bepbar = snapshot.getValue(Bepbar.class);
                    String a = snapshot.child(DANH_SACH_ODER).getValue().toString();
                    String b = snapshot.child(THOI_GIAN).getValue().toString();
                    data.add(a+b);
                    Log.d("aaa",a);
                    Log.d("bbb",b);

                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(BepBarActivity.this, android.R.layout.simple_list_item_1, data);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                        custumArrayBepBar = new CustumArrayBepBar(BepBarActivity.this,R.layout.item_bepbar,data1);
//                        listView1.setAdapter(custumArrayBepBar);
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
//    private void getListsanphamoder(){
//        Query allOder = FirebaseDatabase.getInstance().getReference("bepbar");
//        allOder.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
//                for (DataSnapshot data : snapshot.getChildren()){
//
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull @NotNull DatabaseError error) {
//
//            }
//        });
//    }
}
