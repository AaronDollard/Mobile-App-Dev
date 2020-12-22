package edu.adollard.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class game_screen extends AppCompatActivity implements SensorEventListener {
    int NumberSelected = 0, playerScore = 0, playerCount = 0, selectedColour = 0;
    Button btn1RED, btn2YELLOW, btn3GREEN, btn4BLUE;
    int[] gameSequence = new int[20];
    int IndexArray = 0;
    TextView ScoreBoard;

    private final double NORTH_MOVE_FORWARD = 9.0;// NORTH mag limit
    private final double NORTH_MOVE_BACKWARD = 6.0;// NORTH mag limit

    private final double EAST_MOVE_FORWARD = 6.0;
    private final double EAST_MOVE_BACKWARD = 9.0;

    private final double SOUTH_MOVE_FORWARD = -6.0;
    private final double SOUTH_MOVE_BACKWARD = -9.0;

    private final double WEST_MOVE_FORWARD = -9.0;
    private final double WEST_MOVE_BACKWARD = -6.0;

    boolean NorthReached = false; //Changes when that direction is peaked
    boolean EastReached = false;
    boolean SouthReached = false;
    boolean WestReached = false;


    private SensorManager mSensorManager;
    private Sensor mSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        btn1RED = findViewById(R.id.btn_seq1);
        btn2YELLOW = findViewById(R.id.btn_seq2);
        btn3GREEN = findViewById(R.id.btn_seq3);
        btn4BLUE = findViewById(R.id.btn_seq4);
        ScoreBoard = findViewById(R.id.txtView_Score);

        //Used for checking what colour is selected
        selectedColour = 0;
        //Reset player score on retry
        playerScore = 0;
        //Reset games rounds
        playerCount = 0;

        //Player Score
        playerScore = getIntent().getIntExtra("playerScore",0);
        //Current Completed games Count
        playerCount = getIntent().getIntExtra("playerCount",0);
        IndexArray = getIntent().getIntExtra("IndexArray",0);
        Intent GameIntent = getIntent();
        gameSequence = GameIntent.getIntArrayExtra("gameSequence");

        //Using the sensor service
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }


    /*When the app is brought to the foreground - using app on screen*/
    protected void onResume() {
        super.onResume();
        // turn on the sensor
        mSensorManager.registerListener(this, mSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*App running but not on screen - in the background*/
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);    // turn off listener to save power
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        if ((x > NORTH_MOVE_FORWARD) && (NorthReached == false)) {
            NorthReached = true;
        }
        if ((x < NORTH_MOVE_BACKWARD) && (NorthReached == true)) {
            NorthReached = false;
            btn2YELLOW.setPressed(true);
            GameSequenceCorrect(btn2YELLOW);
        }

        if ((x > EAST_MOVE_FORWARD) && (EastReached == false)) {
            EastReached = true;
        }
        if ((x < EAST_MOVE_BACKWARD) && (EastReached == true)) {
            EastReached = false;
            btn4BLUE.setPressed(true);
            GameSequenceCorrect(btn4BLUE);
        }

        if ((x > SOUTH_MOVE_FORWARD) && (SouthReached == false)) {
            SouthReached = true;
        }
        if ((x < SOUTH_MOVE_BACKWARD) && (SouthReached == true)) {
            SouthReached = false;
            btn3GREEN.setPressed(true);
            GameSequenceCorrect(btn3GREEN);
        }

        if ((x > WEST_MOVE_FORWARD) && (WestReached == false)) {
            WestReached = true;
        }
        if ((x < WEST_MOVE_BACKWARD) && (WestReached == true)) {
            WestReached = false;
            btn1RED.setPressed(true);
            GameSequenceCorrect(btn1RED);

        }
    }

    public void GameSequenceCorrect(View view){
        //If the selected colour matches the sequence
        if(NumberSelected == gameSequence[selectedColour]){
            //Toast.makeText(this,"Correct",Toast.LENGTH_SHORT).show();
            playerScore = playerScore + 1;
            ScoreBoard.setText("Score: " + playerScore);
        }
        //Otherwise exit to game over
        else {
            Intent GameOver = new Intent(view.getContext(), GameOver.class);
            GameOver.putExtra("playerScore", playerScore);
            GameOver.putExtra("playerCount", playerCount);
            startActivity(GameOver);

            playerScore = 0;
            finish();
        }
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //NOT USED BUT NEEDED TO PREVENT AN ERROR
    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}