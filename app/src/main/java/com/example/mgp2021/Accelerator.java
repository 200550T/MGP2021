package com.example.mgp2021;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.TextView;

// This accelerator class is modified from the link below:
// https://examples.javacodegeeks.com/android/core/hardware/sensor/android-accelerometer-example/

// Extra references also used from link below:
// https://code.tutsplus.com/tutorials/using-the-accelerometer-on-android--mobile-22125


public class Accelerator extends Activity implements SensorEventListener {

    private long lastUpdate = 0;
    private float lastX, lastY, lastZ;
    private static final int SHAKE_THRESHOLD = 600;

    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    //private float deltaXMax = 0;
    //private float deltaYMax = 0;
    //private float deltaZMax = 0;

    /*
    private float deltaX = 0;
    private float deltaY = 0;
    private float deltaZ = 0;
    */
    private float vibrateThreshold = 0;


    public Vibrator v;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);

        //initialize vibration
        v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        Log.i("Initiated Accelerator", "Accelerator created...");
    }

    //onResume() register the accelerometer for listening the events
    protected void onResume() {
        super.onResume();
        senSensorManager.registerListener(this, senAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    //onPause() unregister the accelerometer for stop listening the events
    protected void onPause() {
        super.onPause();
        senSensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
/* Old method for detecting sensor change
        // get the change of the x,y,z values of the accelerometer
        deltaX = Math.abs(lastX - event.values[0]);
        deltaY = Math.abs(lastY - event.values[1]);
        deltaZ = Math.abs(lastZ - event.values[2]);

        // if the change is below 2, it is just plain noise
        if (deltaX < 2)
            deltaX = 0;
        if (deltaY < 2)
            deltaY = 0;
        if ((deltaX >  vibrateThreshold) || (deltaY > vibrateThreshold) || (deltaZ > vibrateThreshold)) {
            v.vibrate(50);
            Log.i("Shake detect", "Shake Detected");
        }*/

        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();

            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float speed = Math.abs(x + y + z - lastX - lastY - lastZ)/ diffTime * 10000;

                if (speed > SHAKE_THRESHOLD) {
                    Log.i("Shake detected", "Shake Detected");
                }

                lastX = x;
                lastY = y;
                lastZ = z;
            }
        }
    }
}