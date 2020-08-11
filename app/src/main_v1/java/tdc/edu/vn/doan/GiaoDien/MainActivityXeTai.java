package tdc.edu.vn.doan.GiaoDien;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.R;

public class MainActivityXeTai extends AppCompatActivity {
    ArrayList<String> dataTaiXe;
    Spinner spnr_maTX;
    DataTaiXe databaseTX;
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
        databaseTX=new DataTaiXe(this);
        final ArrayAdapter adapterTX=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataTaiXe);
        spnr_maTX.setAdapter(adapterTX);

    }
    private void setControl() {
        dataTaiXe=new ArrayList<>();
        spnr_maTX=findViewById(R.id.spnr_maTX);
    }
    private void khoiTao() {
        databaseTX=new DataTaiXe(this);
        for(TaiXe taiXe:databaseTX.getAll())
            dataTaiXe.add(taiXe.getMaTX());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
