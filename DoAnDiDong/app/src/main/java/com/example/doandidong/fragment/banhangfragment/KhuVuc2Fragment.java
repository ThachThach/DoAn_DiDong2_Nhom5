package com.example.doandidong.fragment.banhangfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.doandidong.OderActivity;
import com.example.doandidong.R;
import com.example.doandidong.adapte.CustomBanHangAdapter;
import com.example.doandidong.data.PhongBan;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class KhuVuc2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public final static String KEY_TEN_BAN = "KEY_TEN_BAN";
    public final static String KEY_KHU_VUC = "KEY_KHU_VUC";

    private FirebaseFirestore firestore;
    private ArrayList<PhongBan> listPhongBan;
    private ListView listView;

    public KhuVuc2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tang1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static KhuVuc2Fragment newInstance(String param1, String param2) {
        KhuVuc2Fragment fragment = new KhuVuc2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_khu_vuc1, container, false);
        firestore =  FirebaseFirestore.getInstance();

        Intent intent = new Intent(getContext(), OderActivity.class);
        Bundle bundle = new Bundle();

        listPhongBan = new ArrayList<PhongBan>();
        listView = view.findViewById(R.id.listview_khu1);
        CollectionReference db = firestore.collection("phongban");
        db.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for(QueryDocumentSnapshot data : task.getResult()){
                        if(Integer.parseInt(data.getData().get("khuvuc")+"") == 2){
                            PhongBan phongBan = new PhongBan();
                            phongBan.setTenban(data.getData().get("tenban")+"");
                            phongBan.setKhuvuc(Integer.parseInt(data.getData().get("khuvuc")+""));
                            phongBan.setTrangthai(false);
                            listPhongBan.add(phongBan);
                        }
                    }

                    CustomBanHangAdapter customBanHangAdapter = new CustomBanHangAdapter(getContext(), R.layout.item_phongban, listPhongBan);
                    listView.setAdapter(customBanHangAdapter);

                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Toast.makeText(getContext(), listPhongBan.get(i).getTenban(), Toast.LENGTH_SHORT).show();
                            bundle.putString(KEY_TEN_BAN, listPhongBan.get(i).getTenban());
                            bundle.putInt(KEY_KHU_VUC, listPhongBan.get(i).getKhuvuc());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

        return view;
    }
}
