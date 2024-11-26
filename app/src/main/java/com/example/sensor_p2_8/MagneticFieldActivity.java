package com.example.sensor_p2_8;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MagneticFieldActivity extends AppCompatActivity {

    private LineChart lineChart;
    private List<Entry> entriesX = new ArrayList<>();
    private List<Entry> entriesY = new ArrayList<>();
    private List<Entry> entriesZ = new ArrayList<>();
    private float time = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_field);

        lineChart = findViewById(R.id.lineChart);

        LineDataSet dataSetX = new LineDataSet(entriesX, "Pole Magnetyczne X");
        dataSetX.setColor(Color.RED);
        dataSetX.setDrawValues(false);
        dataSetX.setDrawCircles(false);

        LineDataSet dataSetY = new LineDataSet(entriesY, "Pole Magnetyczne Y");
        dataSetY.setColor(Color.GREEN);
        dataSetY.setDrawValues(false);
        dataSetY.setDrawCircles(false);

        LineDataSet dataSetZ = new LineDataSet(entriesZ, "Pole Magnetyczne Z");
        dataSetZ.setColor(Color.BLUE);
        dataSetZ.setDrawValues(false);
        dataSetZ.setDrawCircles(false);

        LineData lineData = new LineData();
        lineData.addDataSet(dataSetX);
        lineData.addDataSet(dataSetY);
        lineData.addDataSet(dataSetZ);

        lineChart.setData(lineData);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensorMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        SensorEventListener sensorEventListenerMagneticField = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                entriesX.add(new Entry(time, x));
                entriesY.add(new Entry(time, y));
                entriesZ.add(new Entry(time, z));
                time += 0.1f;

                dataSetX.notifyDataSetChanged();
                dataSetY.notifyDataSetChanged();
                dataSetZ.notifyDataSetChanged();
                lineData.notifyDataChanged();
                lineChart.notifyDataSetChanged();
                lineChart.setVisibleXRangeMaximum(50);
                lineChart.moveViewToX(time);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {}
        };

        sensorManager.registerListener(sensorEventListenerMagneticField, sensorMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
    }
}
