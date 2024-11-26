package com.example.sensor_p2_8;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView textViewMagnetic, textViewTemperature, textViewProximity,
            textViewPressure, textViewLight, textViewHumidity;

    private Button buttonLight, buttonPressure, buttonProximity, buttonMagnetic;

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

        buttonLight = findViewById(R.id.buttonLight);
        buttonPressure = findViewById(R.id.buttonPressure);
        buttonMagnetic = findViewById(R.id.buttonMagnetic);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensorHumidity = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
        Sensor sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor sensorMagnetic = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
        Sensor sensorPressure = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        Sensor sensorTemperature = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        Sensor sensorProximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        if (sensorHumidity == null) {
            textViewHumidity.setText("wilgotność = czujnik niedostępny");
        } else {
            SensorEventListener sensorEventListenerHumidity = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    textViewHumidity.setText("wilgotność =" + sensorEvent.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensorManager.registerListener(sensorEventListenerHumidity, sensorHumidity, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (sensorLight == null) {
            textViewLight.setText("światło = czujnik niedostępny");
        } else {
            SensorEventListener sensorEventListenerLight = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    textViewLight.setText("światło =" + sensorEvent.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensorManager.registerListener(sensorEventListenerLight, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (sensorMagnetic == null) {
            textViewMagnetic.setText("Pole magnetyczne = czujnik niedostępny");
        } else {
            SensorEventListener sensorEventListenerMagnetic = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    textViewMagnetic.setText("Pole magnetyczne=" + sensorEvent.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensorManager.registerListener(sensorEventListenerMagnetic, sensorMagnetic, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (sensorPressure == null) {
            textViewPressure.setText("ciśnienie = czujnik niedostępny");
        } else {
            SensorEventListener sensorEventListenerPressure = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    textViewPressure.setText("ciśnienie =" + sensorEvent.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensorManager.registerListener(sensorEventListenerPressure, sensorPressure, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (sensorTemperature == null) {
            textViewTemperature.setText("Temperatura = czujnik niedostępny");
        } else {
            SensorEventListener sensorEventListenerTemperature = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    textViewTemperature.setText("Temperatura=" + sensorEvent.values[0]);
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensorManager.registerListener(sensorEventListenerTemperature, sensorTemperature, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (sensorProximity == null) {
            textViewProximity.setText("odleglość od telefonu = czujnik niedostępny");
        } else {
            SensorEventListener sensorEventListenerProximity = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    if (sensorEvent.values[0] < 1) {
                        textViewProximity.setText("odleglość od telefonu = blisko");
                    } else {
                        textViewProximity.setText("odleglość od telefonu = daleko");
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
            sensorManager.registerListener(sensorEventListenerProximity, sensorProximity, SensorManager.SENSOR_DELAY_NORMAL);
        }

        buttonLight.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LightActivity.class);
            startActivity(intent);
        });

        buttonPressure.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PressureActivity.class);
            startActivity(intent);
        });

        buttonMagnetic.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MagneticFieldActivity.class);
            startActivity(intent);
        });
    }
}
