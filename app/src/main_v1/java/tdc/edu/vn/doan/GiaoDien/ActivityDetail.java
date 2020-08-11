package tdc.edu.vn.doan.GiaoDien;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import tdc.edu.vn.doan.Adapter.CustomAdapterTX;
import tdc.edu.vn.doan.Database.DataTaiXe;
import tdc.edu.vn.doan.Model.TaiXe;
import tdc.edu.vn.doan.R;

public class ActivityDetail extends AppCompatActivity {
    RecyclerView recyclerView;
    DataTaiXe databaseTaiXe;
    CustomAdapterTX adapterTX;
    ArrayList<TaiXe> dataTaiXe;
    int position=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setControl();
        setEvent();
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setEvent() {
        adapterTX =new CustomAdapterTX(R.layout.list_custom_taixe, dataTaiXe);
        recyclerView.setAdapter(adapterTX);
        SensorProximity();
    }

    private void setControl() {
        recyclerView=findViewById(R.id.recyclerViewChiTiet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        databaseTaiXe =new DataTaiXe(this);
        dataTaiXe =new ArrayList<>();
    }

    private void getOnceDevice(int position){
        if(position+1 > databaseTaiXe.getAll().size()) {
            Toast.makeText(this, "Đã xem hết danh sách tài xế!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            dataTaiXe.clear();
            dataTaiXe.add(databaseTaiXe.getAll().get(position));
        }
    }
    private void SensorProximity(){
        SensorManager sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);

        final Sensor proximitySensor=sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener=new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getOnceDevice(position++);
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                    adapterTX.notifyDataSetChanged();
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

        sensorManager.registerListener(sensorEventListener,proximitySensor,2*1000*1000);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}