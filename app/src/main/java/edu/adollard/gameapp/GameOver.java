package edu.adollard.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    TextView ScoreBoard;
    int playerScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        ScoreBoard = findViewById(R.id.txt_final_score);
        playerScore = getIntent().getIntExtra("playerScore",0);
        ScoreBoard.setText("Your final score was: " + playerScore);
    }

    public void GoToHiScores(View view) {
        Intent GoToHiScores = new Intent(view.getContext(), HiScores.class);
        startActivity(GoToHiScores);
        finish();
    }

    public void playAgain(View view) {
        Intent mainActivity = new Intent(view.getContext(), MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}