package tdc.edu.vn.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import tdc.edu.vn.doan.Adapter.CustomAdapterTinh;
import tdc.edu.vn.doan.Adapter.CustomAdapterTuyen;
import tdc.edu.vn.doan.Database.DataTuyen;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.Model.Tuyen;
import tdc.edu.vn.doan.R;

public class MainActivityTuyen extends AppCompatActivity {
    private ArrayList<Tuyen> arrTuyen = new ArrayList<>();
    private CustomAdapterTuyen customAdapterTuyen;
    private ListView lvDanhSach;
    int indexItem = -1;
    private DataTuyen databaseTuyen;
    EditText edit_maTuyen,edit_tenTuyen,edit_giaTuyen;
    private Button bt_them,bt_xoa,bt_sua,bt_clear,bt_finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tuyen);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        khoiTao();
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    Tuyen tuyen =getTuyen();
                    databaseTuyen.them(tuyen);
                    arrTuyen.add(tuyen);
                    customAdapterTuyen.notifyDataSetChanged();
                }
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_maTuyen.setText("");
                edit_tenTuyen.setText("");
                edit_giaTuyen.setText("");
                indexItem=-1;
            }
        });
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexItem!=-1) {
                    Tuyen tuyen =getTuyen();
                    databaseTuyen.sua(tuyen);
                    arrTuyen.set(indexItem, tuyen);
                    customAdapterTuyen.notifyDataSetChanged();
                }else
                    Toast.makeText(MainActivityTuyen.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Tuyen tuyen = arrTuyen.get(position);
                    edit_maTuyen.setText(tuyen.getMaTuyen().toString());
                    edit_tenTuyen.setText(tuyen.getTenTuyen().toString());
                    edit_giaTuyen.setText(tuyen.getGia()+"");

                    indexItem = position;
                } catch (Exception e) {
                    Toast.makeText(MainActivityTuyen.this, "Đã có lỗi xảy ra!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexItem!=-1) {
                    Tuyen tuyen = arrTuyen.get(indexItem);
                    databaseTuyen.xoa(tuyen);
                    arrTuyen.remove(indexItem);
                    customAdapterTuyen.notifyDataSetChanged();
                    indexItem=-1;
                }else{
                    Toast.makeText(MainActivityTuyen.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private  void khoiTao(){
        databaseTuyen = new DataTuyen(this);
        arrTuyen = databaseTuyen.getAll();
        customAdapterTuyen = new CustomAdapterTuyen(this,R.layout.list_custom_tuyen, arrTuyen);
        lvDanhSach.setAdapter(customAdapterTuyen);
    }

    private void setControl() {
        edit_maTuyen=findViewById(R.id.edit_maTuyen);
        edit_tenTuyen=findViewById(R.id.edit_tenTuyen);
        edit_giaTuyen= findViewById(R.id.edit_giaTuyen);

        bt_them=findViewById(R.id.bt_add);
        bt_xoa=findViewById(R.id.bt_remove);
        bt_sua=findViewById(R.id.bt_update);
        bt_clear=findViewById(R.id.bt_clear);


        lvDanhSach = findViewById(R.id.listView);
    }
    private boolean isValid(){
        if(this.edit_maTuyen.getText().toString().equals("")) {
            edit_maTuyen.setError("Bạn nên nhập đầy đủ.");
            return false;
        }
        if(this.edit_tenTuyen.getText().toString().equals("")) {
            edit_tenTuyen.setError("Bạn nên nhập đầy đủ.");
            return false;
        }
        if(this.edit_giaTuyen.getText().toString().equals("")) {
            edit_giaTuyen.setError("Bạn nên nhập đầy.");
            return false;
        }
        return true;
    }
    private Tuyen getTuyen(){
        String ma=edit_maTuyen.getText().toString();
        String ten =edit_tenTuyen.getText().toString();
        int gia = Integer.parseInt(edit_giaTuyen.getText().toString());
        Tuyen  tuyen=new Tuyen(0,ma,ten,gia);
        return tuyen;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
