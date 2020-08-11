package tdc.edu.vn.test.view_model;



import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import tdc.edu.vn.test.R;

public class ProximitySensor extends AppCompatActivity {
    SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_sensor);
        setEvent();

        SensorManager sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        final Sensor proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getWindow().getDecorView().setBackgroundColor(Color.RED);
                }
                else{
                    getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager.registerListener(sensorEventListener,proximitySensor,2 * 1000 * 1000);
    }
    private void setEvent() {
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ArrayList sensorList = new ArrayList<String>();
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor item : sensors){
            sensorList.add(item.getName() + " : " + item.getVendor());
        }
        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorList);
        listView.setAdapter(adapter);
    }
}
