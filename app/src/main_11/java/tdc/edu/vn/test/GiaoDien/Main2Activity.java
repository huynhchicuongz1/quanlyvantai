package tdc.edu.vn.test.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import tdc.edu.vn.test.R;

public class Main2Activity extends AppCompatActivity {
    private Button btn_QLTaiXe, btn_QLXe, btn_QLTinh, btn_QLTuyen,btn_PhanCong,bn_TimKiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        setControl();
        setEvent();
    }
    private void setEvent(){
        btn_PhanCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                Main2Activity.this.startActivity(intent);
            }
        });
        btn_QLTaiXe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity_TaiXe.class);
                Main2Activity.this.startActivity(intent);
            }
        });

    }
    private void setControl(){
        btn_QLTaiXe = findViewById(R.id.btn_QLTAIXE);
        btn_QLXe = findViewById(R.id.btn_QLXe);
        btn_QLTinh = findViewById(R.id.btn_QLTinh);
        btn_QLTuyen = findViewById(R.id.btn_QLTuyen);
        btn_PhanCong = findViewById(R.id.btnPhanCong);
        bn_TimKiem = findViewById(R.id.btn_TimKiem);
    }
}
