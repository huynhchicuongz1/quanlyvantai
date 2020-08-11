package tdc.edu.vn.test.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.test.DataBase.DatabaseTaiXe;
import tdc.edu.vn.test.Model.TaiXe;
import tdc.edu.vn.test.R;

public class MainActivityDetailsTaiXe extends AppCompatActivity {
    private Button btnThem,btnSua, btnXoa, btnClear,btn_back;
    private ListView lvDanhSach;
    private EditText txtMaTX, txtTenTX, txtNgaySinh, txtDiaChi;
    DatabaseTaiXe databaseTaiXe;
    protected ArrayList<TaiXe> arrTaiXe = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_details_tai_xe);
        setControl();
        setEvent();
    }

    private void setEvent(){
        String maTX = getIntent().getExtras().getString("maTX");

        hienThi(maTX.toString());
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TaiXe taiXe = new TaiXe(txtMaTX.getText().toString(),txtTenTX.getText().toString(),txtNgaySinh.getText().toString(),txtDiaChi.getText().toString());
                    databaseTaiXe.SuaTaiXe(taiXe);
                    Toast.makeText(MainActivityDetailsTaiXe.this, "Sửa Thành Công!", Toast.LENGTH_SHORT).show();
                    hienThi(taiXe.getMaTX().toString());
                } catch (Exception ex) {
                    Toast.makeText(MainActivityDetailsTaiXe.this, "Không thể sửa!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityDetailsTaiXe.this, MainActivity_TaiXe.class);
                MainActivityDetailsTaiXe.this.startActivity(intent);
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    TaiXe taiXe = new TaiXe(txtMaTX.getText().toString(),txtTenTX.getText().toString(),txtNgaySinh.getText().toString(),txtDiaChi.getText().toString());
                    databaseTaiXe.XoaTaiXe(taiXe);
                    Toast.makeText(MainActivityDetailsTaiXe.this, "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivityDetailsTaiXe.this, MainActivity_TaiXe.class);
                    MainActivityDetailsTaiXe.this.startActivity(intent);
                } catch (Exception ex) {
                    Toast.makeText(MainActivityDetailsTaiXe.this, "Không thể Xóa!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void hienThi(String maTX){
        databaseTaiXe  =new DatabaseTaiXe(this);
        arrTaiXe = databaseTaiXe.layDL(maTX);
        txtMaTX.setText(arrTaiXe.get(0).getMaTX());
        txtTenTX.setText(arrTaiXe.get(0).getTenTX());
        txtNgaySinh.setText(arrTaiXe.get(0).getNgaySinh());
        txtDiaChi.setText(arrTaiXe.get(0).getDiaChi());
    }

    private  void setControl(){
        txtMaTX = findViewById(R.id.txtMaTX);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTenTX = findViewById(R.id.txtTenTX);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        btnSua = findViewById(R.id.btn_sua);
        btn_back = findViewById(R.id.btn_back);
        btnXoa = findViewById(R.id.btn_xoa);
    }
}
