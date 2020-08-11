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

import tdc.edu.vn.doan.Adapter.CustomAdapterSpinnerTaiXe;
import tdc.edu.vn.doan.Adapter.CustomAdapterXe;
import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Database.DataXe;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.Model.Xe;
import tdc.edu.vn.doan.R;

public class MainActivityXe extends AppCompatActivity {
    ArrayList<TaiXe> dataTaiXe;
    ArrayList<Xe> arrXe = new ArrayList<Xe>();
    private CustomAdapterXe customAdapterXe;
    Spinner spnr_maTX;
    DataTaiXe databaseTX;
    CustomAdapterXe adapterXe;
    DataXe databaseXe;
    ListView lvDanhSach;
    int indexItem = -1;
    Button bt_them,bt_xoa,bt_sua,bt_clear,bt_finish;
    int idTaiTX;
    EditText edit_maXe,edit_tenXe,edit_namxs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_xe_tai);
        setControl();
        setEvent();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    private void setEvent() {
        khoiTao();
        if (TextUtils.isEmpty(dataTaiXe.get(0).getId()+"")) {
            idTaiTX=0;
        }else{
            idTaiTX = dataTaiXe.get(0).getId();
        }

        spnr_maTX.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                TaiXe item = dataTaiXe.get(pos);
                idTaiTX = item.getId() ;
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(isValid()) {
                        Xe xe =getXe(idTaiTX);
                        databaseXe.themXe(xe);
                        arrXe.add(xe);
                        adapterXe.notifyDataSetChanged();
                        Toast.makeText(MainActivityXe.this,  "Thêm thành công!", Toast.LENGTH_SHORT).show();
                        khoiTao();

                    }
                }catch (Exception e){
                    Toast.makeText(MainActivityXe.this,  "Lỗi thêm ", Toast.LENGTH_SHORT).show();
                }

            }
        });

        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(indexItem!=-1) {
                        Xe xe =getXe(idTaiTX);
                        databaseXe.sua(xe);
                        arrXe.set(indexItem, xe);
                        adapterXe.notifyDataSetChanged();
                        Toast.makeText(MainActivityXe.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        khoiTao();
                    }else{
                        Toast.makeText(MainActivityXe.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(MainActivityXe.this,  "Lỗi thêm ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Xe xe = arrXe.get(position);
                    edit_maXe.setText(xe.getMaXe());
                    edit_tenXe.setText(xe.getTenXe());
                    edit_namxs.setText(xe.getNamSX());

                    for(int i = 0; i < dataTaiXe.size(); i++){
                        if(dataTaiXe.get(i).getId() == xe.getIdTaiXe()){
                            spnr_maTX.setSelection(i);
                            break;
                        }
                    }
                    indexItem = position;
                } catch (Exception e) {
                    Toast.makeText(MainActivityXe.this, "Đã có lỗi xảy ra!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_maXe.setText("");
                edit_tenXe.setText("");
                edit_namxs.setText("");
                spnr_maTX.setSelection(0);
                indexItem=-1;
            }
        });
        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexItem!=-1) {
                    Xe xe = arrXe.get(indexItem);
                    databaseXe.xoa(xe);
                    arrXe.remove(indexItem);
                    adapterXe.notifyDataSetChanged();
                    Toast.makeText(MainActivityXe.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    khoiTao();
                    indexItem=-1;
                }else{
                    Toast.makeText(MainActivityXe.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
    private void setControl() {
        dataTaiXe=new ArrayList<>();
        spnr_maTX=findViewById(R.id.spnr_maTX);
        edit_maXe=findViewById(R.id.edit_maXe);
        edit_tenXe=findViewById(R.id.edit_tenXe);
        edit_namxs=findViewById(R.id.edit_sxXe);
        lvDanhSach = findViewById(R.id.listDevice);

        bt_them=findViewById(R.id.bt_add);
        bt_xoa=findViewById(R.id.bt_remove);
        bt_sua=findViewById(R.id.bt_update);
        bt_clear=findViewById(R.id.bt_clear);

    }
    private boolean isValid(){
        if(this.edit_maXe.getText().toString().equals("") && this.edit_tenXe.getText().toString().equals("") ) {
            edit_maXe.setError("Bạn nên nhập đầy đủ");
            return false;
        }
        return true;
    }
    private void khoiTao() {
        databaseTX=new DataTaiXe(MainActivityXe.this);
        dataTaiXe = databaseTX.getAll();
        databaseXe = new DataXe(MainActivityXe.this);
        arrXe = databaseXe.getAll();

        adapterXe=new CustomAdapterXe(MainActivityXe.this,R.layout.list_custom_xe, arrXe,dataTaiXe);
        lvDanhSach.setAdapter(adapterXe);
        CustomAdapterSpinnerTaiXe adapterTX =new CustomAdapterSpinnerTaiXe(getApplicationContext(),dataTaiXe);
        spnr_maTX.setAdapter(adapterTX);
//        databaseTX=new DataTaiXe(this);
//        for(TaiXe taiXe:databaseTX.getAll())
//            dataTaiXe.add(new TaiXe(taiXe.getId(),taiXe.getMaTX(),taiXe.getTenTX(), taiXe.getNgaySinh(),taiXe.getDiaChi(),taiXe.getImgSignature()));
    }

    private Xe getXe(int idTaiTX){
        String ma=edit_maXe.getText().toString();
        String ten =edit_tenXe.getText().toString();
        String namsx =edit_namxs.getText().toString();
        Xe  xe=new Xe(0,ma,ten,namsx,idTaiTX);
        return xe;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
