package edu.adollard.accelerometersensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tvx, tvy, tvz;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private boolean isFlat = false;
    double flatValueX, flatValueY, flatValueZ;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvx = findViewById(R.id.tvxval);  // This assumes there are three TextViews
        tvy = findViewById(R.id.tvyval);  // in your xml file called tvxval, tvyval
        tvz = findViewById(R.id.tvzval);  // and tvzval

        // Choose the sensor you want
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

     //When the app is brought to the foreground - using app on screen
    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }


     //App running but not on screen - in the background
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }


     //Called by the system every x milllisecs when sensor changes
    @Override
    public void onSensorChanged(SensorEvent event) {
        // called byt the system every x ms
        float x, y, z;
        x = event.values[0];    // get x value from sensor
        y = event.values[1];
        z = event.values[2];

        tvx.setText(String.valueOf(x));
        tvy.setText(String.valueOf(y));
        tvz.setText(String.valueOf(z));

        if(x < 1 && y < 1 && z > 9){ //Toast appears when the phone is flat
            if (isFlat == false){
                isFlat = true;
                flatValueX = x;
                flatValueY = y;
                flatValueZ = z;
                Toast.makeText(this, "Phone is flat", Toast.LENGTH_SHORT).show();
            }
        }

        if (isFlat == true) { //If the phone is flat
            if( x != flatValueX &&  y != flatValueY && z != flatValueZ){ //If the coord aren't the same as flat coords
                if (isFlat = true){  //If the phone is flat equals true
                    isFlat = false; //Phone is not flat
                    Toast.makeText(this, "Phone is not flat", Toast.LENGTH_SHORT).show();
                    mediaPlayer = MediaPlayer.create(this, R.raw.yay);
                    mediaPlayer.start();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not using
    }

    public void doReset(View view) { //Reset isFlat to false
        isFlat = false;
    }
}