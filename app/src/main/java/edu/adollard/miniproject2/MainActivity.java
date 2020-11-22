package edu.adollard.miniproject2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Values for high and low magnitude
    private final double High_Step = 11.0; // Upper limit
    private final double Low_Step = 8.0; // Lower limit
    boolean highLimit = false;
    int StepCounter = 0; // Counter for steps

    //Using the timer code
    CountUpTimer timer;
    //Views for the information
    TextView stepsView, timeViewHour, timeViewMinute, timeViewSecond,
            Start_Button, Stop_Button, Reset_Button, Results_Button;
    int minute = 0, hour = 0, timerT = 0;
    boolean active;

    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stepsView = findViewById(R.id.txtv_steps);
        timeViewHour = findViewById(R.id.txtv_timer_hour);
        timeViewMinute = findViewById(R.id.txtv_timer_minute);
        timeViewSecond = findViewById(R.id.txtv_timer_second);
        Start_Button = findViewById(R.id.btn_start);
        Stop_Button = findViewById(R.id.btn_stop);
        Reset_Button = findViewById(R.id.btn_reset);
        Results_Button = findViewById(R.id.btn_results);

        timer = new CountUpTimer(300000) {  // should be high for the run (ms)
            public void onTick(int second) {
                timeViewSecond.setText(second + "s");
                timerT++;
                if (second == 60){
                    timeViewSecond.setText(String.valueOf(second));
                    timeViewSecond.setText("0");
                    minute++;
                    timeViewMinute.setText(minute + "m");
                    timer.cancel(); //Resets the timer to 0
                    timer.start(); //Starts it again

                }
                if (minute == 60)
                {
                    timeViewHour.setText(hour + "h");
                    hour++;
                }
            }
        };

        // Sensor service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    //Show results page
    public void doResult(View view) {
        if (active == true)
        {
            Toast.makeText(this, "Active run. Stop to see results!", Toast.LENGTH_LONG).show();
        }

        if (StepCounter == 0 )
        {
            Toast.makeText(this, "Get moving to see results!", Toast.LENGTH_LONG).show();
        }

        if (active == true && StepCounter == 0)
        {
            Toast.makeText(this, "Take a few steps then stop to see results!", Toast.LENGTH_LONG).show();
        }

        if (active == false && StepCounter >= 1)
        {
            Intent Splash = new Intent(view.getContext(), MainPage.class);
            double ranM = Integer.valueOf(stepsView.getText().toString());
            Splash.putExtra("stepsView", ranM);

            if (timerT >=1)
            {
                int Second = Integer.valueOf(timeViewSecond.getText().toString());
                Splash.putExtra("timeViewSecond", Second);

                if (timerT >=61)
                {
                    int Minute = Integer.valueOf(timeViewMinute.getText().toString());
                    Splash.putExtra("timeViewMinute", Minute);

                    if (timerT >= 3600)
                    {
                        int Hour = Integer.valueOf(timeViewHour.getText().toString());
                        Splash.putExtra("timeViewHour", Hour);
                    }
                }
            }
            startActivity(Splash);
        }
    }
    //Do the resetting
    public void doReset(View view) {
        timer.cancel();
        active = false;
        timeViewSecond.setText("");
        timeViewMinute.setText("");
        timeViewHour.setText("");
        stepsView.setText("");
        Start_Button.setVisibility(View.VISIBLE);
        Stop_Button.setVisibility(View.INVISIBLE);
        //Toast.makeText(this, "Reset", Toast.LENGTH_LONG).show();
    }
    //Start the time in seconds and steps taken
    public void doStart(View view) {
        timer.start();
        active = true;
        Start_Button.setVisibility(View.INVISIBLE);
        Stop_Button.setVisibility(View.VISIBLE);
        //Toast.makeText(this, "Started counting", Toast.LENGTH_LONG).show();
    }
    //Stop the counter
    public void doStop(View view) {
        timer.cancel();
        active = false;
        Stop_Button.setVisibility(View.GONE);
        //Toast.makeText(this, "Stopped Run", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //Getting the values of the x y z of phone
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        // Get a magnitude number using Pythagorus Theorem
        double mag = round(Math.sqrt((x*x) + (y*y) + (z*z)), 2);

        if ((mag > High_Step) && (highLimit == false)) {
            highLimit = true; //Gets the high point of a step, high = true
        }
        //If the mag is less than the low point and the high point is true.
        if ((mag < Low_Step) && (highLimit == true)) {
            StepCounter++; //Register this as one step
            stepsView.setText(String.valueOf(StepCounter)); //Count the step in the txtbox
            highLimit = false; //Set high point as false to detect next step
        }
    }


    /*When the app is brought to the foreground - using app on screen*/
    protected void onResume() {
        super.onResume();
        // Sensor is turned on
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*App running but not on screen - in the background*/
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);//Make sure to turn off to save battery power of phone
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //UNUSED BUT NEEDED TO PREVENT ERROR
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}