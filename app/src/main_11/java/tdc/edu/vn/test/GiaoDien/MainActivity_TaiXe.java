package tdc.edu.vn.test.GiaoDien;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.test.Adapter.CustomAdapter;
import tdc.edu.vn.test.Adapter.CustomAdapterTaiXe;
import tdc.edu.vn.test.DataBase.DatabasePhanCong;
import tdc.edu.vn.test.DataBase.DatabaseTaiXe;
import tdc.edu.vn.test.Model.TaiXe;
import tdc.edu.vn.test.R;

public class MainActivity_TaiXe extends AppCompatActivity {

    private CustomAdapterTaiXe adapterTaiXe;
    protected ArrayList<TaiXe> arrTaiXe = new ArrayList<>();
    private Button btnThem,btnSua, btnXoa, btnClear,btn_back;
    private ListView lvDanhSach;
    private EditText txtMaTX, txtTenTX, txtNgaySinh, txtDiaChi;
    DatabaseTaiXe databaseTaiXe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__tai_xe);
        setControl();
        setEvent();
    }
    private boolean checkInput() {
        for (int i = 0; i < arrTaiXe.size(); i++) {
            if (arrTaiXe.get(i).getMaTX().toString().equals(txtMaTX.getText().toString())) {
                txtMaTX.setError("Mã Tài Xế không được trùng");
                return false;
            }
        }
        return true;
    }
    private void setEvent() {
        hienthiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkInput()) {
                        TaiXe taiXe = new TaiXe(txtMaTX.getText().toString(),
                                txtTenTX.getText().toString(),
                                txtNgaySinh.getText().toString(),
                                txtDiaChi.getText().toString());
                        databaseTaiXe.themTaiXe(taiXe);
                        hienthiDL();
                    }

                } catch (Exception ex) {
                    Toast.makeText(MainActivity_TaiXe.this, "Có lỗi xảy ra, vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    txtMaTX.setText("");
                    txtNgaySinh.setText("");
                    txtTenTX.setText("");
                    txtDiaChi.setText("");
                } catch (Exception ex) {
                    Toast.makeText(MainActivity_TaiXe.this, "Không thể làm mới lúc này!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setControl() {
        btnThem = findViewById(R.id.btn_them);
        btnClear = findViewById(R.id.btn_clear);
        txtMaTX = findViewById(R.id.txtMaTX);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTenTX = findViewById(R.id.txtTenTX);
        txtNgaySinh = findViewById(R.id.txtNgaySinh);
        lvDanhSach = findViewById(R.id.lv_danhSachTaiXe);
    }
    private void hienthiDL(){
        databaseTaiXe = new DatabaseTaiXe(this);
        arrTaiXe = databaseTaiXe.layDL();
        adapterTaiXe = new CustomAdapterTaiXe(MainActivity_TaiXe.this, R.layout.activity_listvew_tai_xe, arrTaiXe);
        lvDanhSach.setAdapter(adapterTaiXe);
        Toast.makeText(this, "Đọc dữ liệu!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu: {
                try {
                    databaseTaiXe = new DatabaseTaiXe(MainActivity_TaiXe.this);
//                    databaseTaiXe.themDanhSachTaiXe(arrTaiXe);
                    Toast.makeText(MainActivity_TaiXe.this, "Đã lưu", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(MainActivity_TaiXe.this, "Lỗi, không thể lưu!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.mnThoat: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity_TaiXe.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn thoát?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
