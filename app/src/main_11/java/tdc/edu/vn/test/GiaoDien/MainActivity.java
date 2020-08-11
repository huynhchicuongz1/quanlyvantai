package tdc.edu.vn.test.GiaoDien;

import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.test.Adapter.CustomAdapter;
import tdc.edu.vn.test.DataBase.DatabasePhanCong;
import tdc.edu.vn.test.R;
import tdc.edu.vn.test.Model.PhanCong;

public class MainActivity extends AppCompatActivity {
    public static final String TUYEN_1 = "Hà Nội - Hải Phòng";
    public static final String TUYEN_2 = "Hà Nội - Lào Cai";
    public static final String TUYEN_3 = "HCM - Quảng Ninh";
    public static final String XE_1 = "Hải Ninh";
    public static final String XE_2 = "Đông Long";
    public static final String XE_3 = "Phũ Mĩ";

    private ArrayList<String> arrTuyen = new ArrayList<>();
    private ArrayList<String> arrXe = new ArrayList<>();
    protected ArrayList<PhanCong> arrPhanCong = new ArrayList<>();
    private Button btnThem, btnSua, btnXoa, btnClear;
    private ListView lvDanhSach;
    private EditText etSoPhieu, etNgayTao, etNgayXuatPhat;
    private Spinner spnrXe, spnrTuyen;
    private CustomAdapter adapterPhanCong;
    int indexItem = -1;

    private DatabasePhanCong databasePhanCong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setControl();
        setEvent();
    }

    private boolean checkInput() {
        for (int i = 0; i < arrPhanCong.size(); i++) {
            if (arrPhanCong.get(i).getSoPhieu().toString().equals(etSoPhieu.getText().toString())) {
                etSoPhieu.setError("Số phiếu không được trùng");
                return false;
            }
        }
        if (etNgayTao.getText().toString().length() == 0) {
            etNgayTao.setError("Thiếu ngày tạo phiếu");
            return false;
        }
        if (etNgayXuatPhat.getText().toString().length() == 0) {
            etNgayXuatPhat.setError("Thiếu ngày xuât phát");
            return false;
        }
        if (etSoPhieu.getText().toString().length() == 0) {
            etSoPhieu.setError("Thiếu số phiếu");
            return false;
        }
        return true;
    }

    private void setEvent() {
        setData();

        ArrayAdapter adapterTuyen = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrTuyen);
        spnrTuyen.setAdapter(adapterTuyen);

        ArrayAdapter adapterXe = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrXe);
        spnrXe.setAdapter(adapterXe);
        hienthiDL();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (checkInput()) {
                        PhanCong xt = new PhanCong(etSoPhieu.getText().toString(),
                                etNgayTao.getText().toString(),
                                etNgayXuatPhat.getText().toString(),
                                spnrTuyen.getSelectedItem().toString(),
                                spnrXe.getSelectedItem().toString());
                        databasePhanCong.themXe(xt);
                        hienthiDL();
                    }

                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Có lỗi xảy ra, vui lòng kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    etSoPhieu.setText("");
                    etNgayTao.setText("");
                    etNgayXuatPhat.setText("");
                    spnrTuyen.setSelection(0);
                    spnrXe.setSelection(0);
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Không thể làm mới lúc này!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    PhanCong xt = arrPhanCong.get(position);

                    etSoPhieu.setText(xt.getSoPhieu().toString());
                    etNgayTao.setText(xt.getNgayXuatPhieu().toString());
                    etNgayXuatPhat.setText(xt.getNgayXuatPhat().toString());

                    spnrTuyen.setSelection(arrTuyen.indexOf(xt.getTuyen()));
                    spnrXe.setSelection(arrXe.indexOf(xt.getXe()));

                    indexItem = position;
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Đã có lỗi xảy ra!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa?");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        arrPhanCong.remove(position);
                        adapterPhanCong.notifyDataSetChanged();
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
                return true;
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PhanCong xt = arrPhanCong.get(indexItem);
                    xt.setSoPhieu(etSoPhieu.getText().toString());
                    xt.setNgayXuatPhieu(etNgayTao.getText().toString());
                    xt.setNgayXuatPhat(etNgayXuatPhat.getText().toString());
                    xt.setTuyen(spnrTuyen.getSelectedItem().toString());
                    xt.setXe(spnrXe.getSelectedItem().toString());
                    databasePhanCong.XoaXe(xt);
                    hienthiDL();
                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Không thể xóa, hãy kiểm tra lại!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PhanCong xt = arrPhanCong.get(indexItem);
                    xt.setSoPhieu(etSoPhieu.getText().toString());
                    xt.setNgayXuatPhieu(etNgayTao.getText().toString());
                    xt.setNgayXuatPhat(etNgayXuatPhat.getText().toString());
                    xt.setTuyen(spnrTuyen.getSelectedItem().toString());
                    xt.setXe(spnrXe.getSelectedItem().toString());
                    databasePhanCong.SuaXe(xt);
                    hienthiDL();

                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "Không thể sửa!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData() {
        arrTuyen.add(TUYEN_1);
        arrTuyen.add(TUYEN_2);
        arrTuyen.add(TUYEN_3);

        arrXe.add(XE_1);
        arrXe.add(XE_2);
        arrXe.add(XE_3);
    }


    private void setControl() {
        btnThem = findViewById(R.id.btn_them);
        btnSua = findViewById(R.id.btn_sua);
        btnXoa = findViewById(R.id.btn_xoa);
        btnClear = findViewById(R.id.btn_clear);

        etSoPhieu = findViewById(R.id.et_soPhieu);
        etNgayTao = findViewById(R.id.et_ngayXuatPhieu);
        etNgayXuatPhat = findViewById(R.id.et_ngayXuatPhat);

        spnrTuyen = findViewById(R.id.spnr_tuyen);
        spnrXe = findViewById(R.id.spnr_xe);

        lvDanhSach = findViewById(R.id.lv_danhSach);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void hienthiDL(){
        databasePhanCong = new DatabasePhanCong(this);
        arrPhanCong = databasePhanCong.layDL();
        adapterPhanCong = new CustomAdapter(this, R.layout.listview, arrPhanCong);
        lvDanhSach.setAdapter(adapterPhanCong);
        Toast.makeText(this, "Đọc dữ liệu!", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnLuu: {
                try {
                    databasePhanCong = new DatabasePhanCong(this);
                    databasePhanCong.themDanhSachXe(arrPhanCong);
                    Toast.makeText(this, "Đã lưu", Toast.LENGTH_SHORT).show();
                } catch (Exception ex) {
                    Toast.makeText(this, "Lỗi, không thể lưu!", Toast.LENGTH_SHORT).show();
                }
                break;
            }
//            case R.id.mnDoc: {
//                databaseTaiXe = new DatabasePhanCong(this);
//                arrPhanCong = databaseTaiXe.layDL();
//                adapterXeTai = new CustomAdapter(this, R.layout.listview, arrPhanCong);
//                lvDanhSach.setAdapter(adapterXeTai);
//                Toast.makeText(this, "Đọc dữ liệu!", Toast.LENGTH_SHORT).show();
//                break;
//            }
            case R.id.mnThoat: {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
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
