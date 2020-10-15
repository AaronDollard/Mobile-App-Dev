package edu.adollard.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView display_timer; //Create the instance for the timer to display
    CountDownTimer timer = new CountDownTimer(10000, 1000) {

        public void onTick(long millisUntilFinished) {
            display_timer.setText("Seconds remaining: " + millisUntilFinished / 1000);
            //Here you can have your logic to set text to edittext
        }

        public void onFinish() { //Displays the message upon ending
            display_timer.setText("Finished!");
        } //Passing in information
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display_timer = findViewById(R.id.display_timer); //Linking the display for the timer
    }

    public void doStart(View view) {
        timer.start(); //uses the timer method in the global vars
    }

    public void doStop(View view) {
        timer.cancel();
        Toast.makeText(this, "Timer stopped", Toast.LENGTH_LONG).show();
    }

    public void doReset(View view) {
    }
}