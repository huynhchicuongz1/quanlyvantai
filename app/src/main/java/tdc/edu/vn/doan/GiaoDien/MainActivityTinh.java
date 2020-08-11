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

import tdc.edu.vn.doan.Adapter.CustomAdapterTX;
import tdc.edu.vn.doan.Adapter.CustomAdapterTinh;
import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Database.DataTinh;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.Model.Tinh;
import tdc.edu.vn.doan.R;

public class MainActivityTinh extends AppCompatActivity {
    private ArrayList<Tinh> arrTinh = new ArrayList<>();
    private CustomAdapterTinh customAdapterTinh;
    private Button bt_them,bt_xoa,bt_sua,bt_clear,bt_finish;
    private ListView lvDanhSach;
    int indexItem = -1;
    private DataTinh dataTinh;
    EditText edit_maTinh,edit_tenTinh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tinh);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setControl() {
        edit_maTinh=findViewById(R.id.edit_maTinh);
        edit_tenTinh=findViewById(R.id.edit_tenTinh);

        bt_them=findViewById(R.id.bt_add);
        bt_xoa=findViewById(R.id.bt_remove);
        bt_sua=findViewById(R.id.bt_update);
        bt_clear=findViewById(R.id.bt_clear);



        lvDanhSach = findViewById(R.id.listTinh);
    }
    private void setEvent() {
        dataTinh = new DataTinh(this);
        arrTinh = dataTinh.getAll();
        customAdapterTinh = new CustomAdapterTinh(this,R.layout.list_custom_tinh, arrTinh);
        lvDanhSach.setAdapter(customAdapterTinh);

        //button listener
        bt_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tinh tinh =getTinh();
                if(isValid()) {
                    dataTinh.themTinh(getTinh());
                    arrTinh.add(tinh);
                    customAdapterTinh.notifyDataSetChanged();
                }
            }
        });

        bt_xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexItem!=-1) {
                    Tinh tinh = arrTinh.get(indexItem);
                    dataTinh.xoaTinh(tinh);
                    arrTinh.remove(indexItem);
                    customAdapterTinh.notifyDataSetChanged();
                    indexItem=-1;
                }else{
                    Toast.makeText(MainActivityTinh.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
                }
            }
        });
        bt_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_maTinh.setText("");
                edit_tenTinh.setText("");
                indexItem=-1;
            }
        });
        bt_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indexItem!=-1) {
                    Tinh tinh =getTinh();
                    dataTinh.suaTinh(tinh);
                    arrTinh.set(indexItem, tinh);
                    customAdapterTinh.notifyDataSetChanged();
                }else
                    Toast.makeText(MainActivityTinh.this, "Bạn chưa chọn bất cứ thứ gì", Toast.LENGTH_SHORT).show();
            }
        });

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Tinh tinh = arrTinh.get(position);
                    edit_maTinh.setText(tinh.getMaTinh().toString());
                    edit_tenTinh.setText(tinh.getTenTinh().toString());

                    indexItem = position;
                } catch (Exception e) {
                    Toast.makeText(MainActivityTinh.this, "Đã có lỗi xảy ra!!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private boolean isValid(){
        if(this.edit_maTinh.getText().toString().equals("")) {
            edit_tenTinh.setError("Bạn nên nhập đầy đủ tên Tinh");
            return false;
        }
        return true;
    }
    private Tinh getTinh(){
        String ma=edit_maTinh.getText().toString();
        String ten =edit_tenTinh.getText().toString();
        Tinh  tinh=new Tinh(0,ma,ten);
        return tinh;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
