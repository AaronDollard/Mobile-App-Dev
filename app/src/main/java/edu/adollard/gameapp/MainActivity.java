package edu.adollard.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;
    private final int BLUE = 1;

    Button btn1RED, btn2YELLOW, btn3GREEN, btn4BLUE, buttonFlash;

    int sequenceCount = 4, Number = 0, playerScore = 0, playerCount = 0;
    int[] gameSequence = new int[120];
    int IndexArray = 0;

    private Object mutex = new Object();
    //View for when the game is being played
    View playingGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerScore = 0;
        playerCount = 0;

        DatabaseHandler db = new DatabaseHandler(this);
        db.emptyHiScores();     // empty table if required
        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");
        db.addHiScore(new HighScoreClass("10 APR 2020", "Aaron", 12));
        db.addHiScore(new HighScoreClass("18 OCT 2020", "Adam", 16));
        db.addHiScore(new HighScoreClass("10 NOV 2020", "Mathijs", 20));
        db.addHiScore(new HighScoreClass("10 JUL 2020", "Kelly", 18));
        db.addHiScore(new HighScoreClass("12 JUL 2020", "Emma", 22));
        db.addHiScore(new HighScoreClass("10 MAR 2020", "Mary", 30));
        db.addHiScore(new HighScoreClass("11 DEC 2020", "Anthony", 22));
        db.addHiScore(new HighScoreClass("12 NOV 2020", "Owen", 132));

        btn1RED = findViewById(R.id.btn_seq1);
        btn2YELLOW = findViewById(R.id.btn_seq2);
        btn3GREEN = findViewById(R.id.btn_seq3);
        btn4BLUE = findViewById(R.id.btn_seq4);
    }


    CountDownTimer CountdownTimerRound = new CountDownTimer(6000,  2000) {
        public void onTick(long millisUntilFinished) {
            //mTextField.setText("seconds remaining: " + millisUntilFinished / 1500);
            oneButton();
            //here you can have your logic to set text to edittext
        }

        //On completion sends data to next intent. Also logs into LogCat
        public void onFinish() {
            //mTextField.setText("done!");
            // we now have the game sequence
            for (int i = 0; i < IndexArray; i++)
                Log.d("Game sequence ", String.valueOf(gameSequence[i]));
            System.out.println("####");

            Intent gameStarted = new Intent(playingGame.getContext(), game_screen.class);
            //Sending the data onto the next screen
            gameStarted.putExtra("gameSequence", gameSequence);
            gameStarted.putExtra("playerScore", playerScore);
            gameStarted.putExtra("indexArray", IndexArray);
            gameStarted.putExtra("playerCount", playerCount);

            startActivity(gameStarted);
        }
    };

    public void doPlay(View view) {
        //Start the countdown timer
        CountdownTimerRound.start();
        playingGame = view;

        if(IndexArray <= 3){
            //20 sequences
            gameSequence = new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
            CountdownTimerRound.start();
        }
    }

    //Flashes the buttons that get selected at random
    private void oneButton() {
        Number = getRandom();
        Toast.makeText(this, "Number = " + Number, Toast.LENGTH_SHORT).show();
        switch (Number) {
            case 1:
                flashButton(btn1RED);
                gameSequence[IndexArray++] = BLUE;
                break;
            case 2:
                flashButton(btn2YELLOW);
                gameSequence[IndexArray++] = RED;
                break;
            case 3:
                flashButton(btn3GREEN);
                gameSequence[IndexArray++] = YELLOW;
                break;
            case 4:
                flashButton(btn4BLUE);
                gameSequence[IndexArray++] = GREEN;
                break;
            default:
                break;
        }
    }

    // Return random number between 1 to maxValue
    public int getRandom() {
        double maxValue = 4;
        int random = (int) (Math.random() * maxValue + 1);
        return random;
    }

    private void flashButton(Button button) {
        buttonFlash = button;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                buttonFlash.setPressed(true);
                buttonFlash.invalidate();
                buttonFlash.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        buttonFlash.setPressed(false);
                        buttonFlash.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);
            } // end runnable
        };
        handler.postDelayed(r, 600);
    }

}