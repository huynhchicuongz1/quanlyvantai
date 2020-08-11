package tdc.edu.vn.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.doan.Adapter.CustomAdapterPhanCong;
import tdc.edu.vn.doan.Adapter.CustomAdapterSpinnerTinh;
import tdc.edu.vn.doan.Adapter.CustomAdapterSpinnerTuyen;
import tdc.edu.vn.doan.Adapter.CustomAdapterSpinnerXe;
import tdc.edu.vn.doan.Database.DataPhanCong;
import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Database.DataTinh;
import tdc.edu.vn.doan.Database.DataTuyen;
import tdc.edu.vn.doan.Database.DataXe;
import tdc.edu.vn.doan.Model.PhanCong;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Tuyen;
import tdc.edu.vn.doan.Model.Xe;
import tdc.edu.vn.doan.R;

public class MainActivityPhanCong extends AppCompatActivity {
    ArrayList<Xe> arrXe = new ArrayList<Xe>();
    ArrayList<Tinh> arrTinh = new ArrayList<Tinh>();
    ArrayList<Tuyen> arrTuyen = new ArrayList<Tuyen>();
    ArrayList<PhanCong> arrPhanCong = new ArrayList<>();
    CustomAdapterSpinnerXe adapterSpinnerXe;
    CustomAdapterSpinnerTinh adapterSpinnerTinh;
    CustomAdapterSpinnerTuyen adapterSpinnerTuyen;
    CustomAdapterPhanCong adapterPhanCong;
    DataXe databaseXe;
    DataTinh databaseTinh;
    DataTuyen dataTuyen;
    DataPhanCong dataPhanCong;

    ListView lvDanhSach;
    int indexItem = -1;
    Button bt_them,bt_xoa,bt_sua,bt_clear,bt_finish;

    Spinner spnr_maXe,spnr_maTinh,spnr_maTuyen;
    EditText edit_soPhieu,edit_ngayXP;
    int idXe,idTuyen;
    String maTinh =null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_phan_cong);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        khoiTao();
        if (TextUtils.isEmpty(arrXe.get(0).getId()+"")) {
            idXe=0;
        }else{
            idXe = arrXe.get(0).getId();
        }
        if (TextUtils.isEmpty(arrXe.get(0).getId()+"")) {
            idTuyen=0;
        }else{
            idTuyen = arrTuyen.get(0).getId();
        }

        if (TextUtils.isEmpty(arrTinh.get(0).getId()+"")) {
            maTinh=null;
        }else{
            maTinh = arrTinh.get(0).getMaTinh();
        }

        spnr_maXe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Xe item = arrXe.get(pos);
                idXe = item.getId() ;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnr_maTuyen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Tuyen item = arrTuyen.get(pos);
                idTuyen = item.getId() ;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spnr_maTinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Tinh item = arrTinh.get(pos);
                maTinh = item.getMaTinh() ;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isValid()) {
                        PhanCong phanCong = new PhanCong(edit_ngayXP.getText().toString(),maTinh,idXe,idTuyen);
                        dataPhanCong.them(phanCong);
                        adapterPhanCong.notifyDataSetChanged();
                        Toast.makeText(MainActivityPhanCong.this,  "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        khoiTao();

                    }
                }catch (Exception e){
                    Toast.makeText(MainActivityPhanCong.this,  "Lỗi thêm ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(indexItem!=-1) {
                        PhanCong phanCong = new PhanCong(Integer.parseInt(edit_soPhieu.getText().toString()),edit_ngayXP.getText().toString(),maTinh,idXe,idTuyen);
                        dataPhanCong.sua(phanCong);
                        adapterPhanCong.notifyDataSetChanged();
                        Toast.makeText(MainActivityPhanCong.this,  "Sửa thành công!", Toast.LENGTH_SHORT).show();
                        khoiTao();
                    }else{
                        Toast.makeText(MainActivityPhanCong.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivityPhanCong.this,  "Lỗi thêm ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexItem!=-1) {
                    PhanCong phanCong = new PhanCong(Integer.parseInt(edit_soPhieu.getText().toString()),edit_ngayXP.getText().toString(),maTinh,idXe,idTuyen);
                    dataPhanCong.xoa(phanCong);
                    arrPhanCong.remove(indexItem);
                    adapterPhanCong.notifyDataSetChanged();
                    Toast.makeText(MainActivityPhanCong.this,  "Xóa thành công!", Toast.LENGTH_SHORT).show();
                    khoiTao();
                }else{
                    Toast.makeText(MainActivityPhanCong.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_ngayXP.setText("");
                edit_soPhieu.setText("");
                spnr_maXe.setSelection(0);
                spnr_maTuyen.setSelection(0);
                spnr_maTinh.setSelection(0);
                indexItem=-1;
            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    PhanCong phanCong = arrPhanCong.get(position);
                    edit_ngayXP.setText(phanCong.getNgayXuatPhieu());
                    edit_soPhieu.setText(phanCong.getSoPhieu()+"");
                    for(int i = 0; i < arrXe.size(); i++){
                        if(arrXe.get(i).getId() == phanCong.getId_xe()){
                            spnr_maXe.setSelection(i);
                            break;
                        }
                    }
                    for(int i = 0; i < arrTuyen.size(); i++){
                        if(arrTuyen.get(i).getId() == phanCong.getId_tuyen()){
                            spnr_maTuyen.setSelection(i);
                            break;
                        }
                    }
                    for(int i = 0; i < arrTinh.size(); i++){
                        if(arrTinh.get(i).getMaTinh().equals(phanCong.getNoiXuatPhat())){
                            spnr_maTinh.setSelection(i);
                            break;
                        }
                    }

                    indexItem = position;
                } catch (Exception e) {
                    Toast.makeText(MainActivityPhanCong.this, "Đã có lỗi xảy ra!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void khoiTao() {

        databaseXe = new DataXe(MainActivityPhanCong.this);
        arrXe = databaseXe.getAll();
        adapterSpinnerXe =new CustomAdapterSpinnerXe(getApplicationContext(),arrXe);
        spnr_maXe.setAdapter(adapterSpinnerXe);

        databaseTinh = new DataTinh(MainActivityPhanCong.this);
        arrTinh = databaseTinh.getAll();
        adapterSpinnerTinh =new CustomAdapterSpinnerTinh(MainActivityPhanCong.this,arrTinh);
        spnr_maTinh.setAdapter(adapterSpinnerTinh);

        dataTuyen = new DataTuyen(MainActivityPhanCong.this);
        arrTuyen = dataTuyen.getAll();
        adapterSpinnerTuyen =new CustomAdapterSpinnerTuyen(MainActivityPhanCong.this,arrTuyen);
        spnr_maTuyen.setAdapter(adapterSpinnerTuyen);

        dataPhanCong = new DataPhanCong(MainActivityPhanCong.this);
        arrPhanCong = dataPhanCong.getAll();
        adapterPhanCong = new CustomAdapterPhanCong(MainActivityPhanCong.this,R.layout.list_custom_phan_cong,arrPhanCong, arrXe , arrTuyen , arrTinh);
        lvDanhSach.setAdapter(adapterPhanCong);

    }
    private boolean isValid(){
        if(this.edit_ngayXP.getText().toString().equals("")) {
//            edit_ngayXP.setError("Bạn nên nhập đầy đủ");
            return false;
        }
        return true;
    }

    private void setControl() {
        spnr_maXe=findViewById(R.id.spnr_maXe);
        spnr_maTuyen=findViewById(R.id.spnr_maTuyen);
        spnr_maTinh=findViewById(R.id.spnr_maTinh);

        edit_soPhieu=findViewById(R.id.edit_soPhieu);
        edit_ngayXP=findViewById(R.id.edit_ngayXP);

        bt_them=findViewById(R.id.bt_add);
        bt_xoa=findViewById(R.id.bt_remove);
        bt_sua=findViewById(R.id.bt_update);
        bt_clear=findViewById(R.id.bt_clear);



        lvDanhSach = findViewById(R.id.listDevice);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
