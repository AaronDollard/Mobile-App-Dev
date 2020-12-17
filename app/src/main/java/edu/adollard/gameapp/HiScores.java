package edu.adollard.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HiScores extends AppCompatActivity {

    private DatabaseHandler HiScoreDatabase;
    TextView ScoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_scores);

        ScoreBoard = findViewById(R.id.txtView_Scoreboard);
        String HiScores = String.format("Player | Score | Date Played");
        ScoreBoard.setText(HiScores);
        HiScoreDatabase = new DatabaseHandler(this);

        //Getting top 5 HiScores from the database
        List<HighScoreClass> Top5 = HiScoreDatabase.getTopFiveScores();


    }

    public void playAgain(View view) {
        Intent mainActivity = new Intent(view.getContext(), MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}