package tdc.edu.vn.doan.GiaoDien;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import tdc.edu.vn.doan.R;

public class HomeActivity extends AppCompatActivity {
    TextView txtVN, txtUS;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setControl();
        setEvent();
    }

    public void setControl(){
        txtVN = (TextView) findViewById(R.id.txtVN);
        txtUS = (TextView) findViewById(R.id.txtUS);
        btnStart = (Button) findViewById(R.id.btnStart);
    }

    public void setEvent(){
        txtVN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("en");
            }
        });

        txtUS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ganNgonNgu("us");
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivityDangNhap.class);
                startActivity(intent);
            }
        });
    }
    public void ganNgonNgu(String language) {
        Locale locale = new Locale(language);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(
                config,
                getBaseContext().getResources().getDisplayMetrics()
        );
        Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}