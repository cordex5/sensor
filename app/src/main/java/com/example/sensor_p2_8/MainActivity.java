package com.example.sensor_p2_8;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private TextView textViewMagnetic, textViewTemperature, textViewProximity,
            textViewPressure, textViewLight, textViewHumidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewHumidity = findViewById(R.id.textViewHumidity);
        textViewLight = findViewById(R.id.textViewLight);
        textViewMagnetic = findViewById(R.id.textViewMagneticField);
        textViewPressure = findViewById(R.id.textViewPressure);
        textViewTemperature = findViewById(R.id.textViewTemperature);
        textViewProximity = findViewById(R.id.textViewProximity);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensorHumidity = sensorManager. getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        Sensor sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor sensorMagnetic = sensorManager. getDefaultSensor(Sensor. TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        Sensor sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        Sensor sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        Sensor sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        SensorEventListener sensorEventListenerHumidity = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                textViewHumidity. setText("wilgotność="+sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        SensorEventListener sensorEventListenerLight = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                textViewLight. setText("światło="+sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        SensorEventListener sensorEventListenerPressure = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                textViewPressure. setText("ciśnienie="+sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        SensorEventListener sensorEventListenerProximity = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                textViewProximity. setText("bliskość="+sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        SensorEventListener sensorEventListenerMagnetic = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                textViewMagnetic. setText("Pole magnetyczne="+sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        SensorEventListener sensorEventListenerTemperature = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                textViewTemperature. setText("Temperatura="+sensorEvent.values[0]);
            }
            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        sensorManager. registerListener(sensorEventListenerHumidity, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
       sensorManager. registerListener (sensorEventListenerLight, sensorLight, SensorManager. SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerPressure, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerProximity, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerMagnetic, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(sensorEventListenerTemperature, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
    }
}