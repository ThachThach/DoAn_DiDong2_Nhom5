package com.example.doandidong;

        import android.os.Bundle;
        import android.widget.TextView;

        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import com.google.firebase.database.DatabaseReference;

public class ThuChiActivity extends AppCompatActivity {
    DatabaseReference db;
    private TextView a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.banghang);
    }
}
