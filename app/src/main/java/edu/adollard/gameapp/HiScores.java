package edu.adollard.gameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HiScores extends AppCompatActivity {
    ListView ScoreBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hi_scores);
        ScoreBoard = findViewById(R.id.LstView_Scorboard);
        DatabaseHandler db = new DatabaseHandler(this);

        db.emptyHiScores();     // empty table if required

        // Inserting hi scores
        Log.i("Insert: ", "Inserting ..");
        db.addHiScore(new HighScoreClass("20 DEC 2020", "Aaron", 12));
        db.addHiScore(new HighScoreClass("28 DEC 2020", "Mathijs", 16));
        db.addHiScore(new HighScoreClass("20 NOV 2020", "Adam", 20));
        db.addHiScore(new HighScoreClass("20 NOV 2020", "Mary", 18));
        db.addHiScore(new HighScoreClass("22 NOV 2020", "Kelly", 22));
        db.addHiScore(new HighScoreClass("30 NOV 2020", "Anthony", 30));
        db.addHiScore(new HighScoreClass("01 DEC 2020", "Mark", 22));
        db.addHiScore(new HighScoreClass("02 DEC 2020", "Aimee", 132));

        // Reading all scores
        Log.i("Reading: ", "Reading all scores..");
        List<HighScoreClass> hiScores = db.getAllHiScores();
        for (HighScoreClass hs : hiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        HighScoreClass singleScore = db.getHiScore(5);
        Log.i("High Score 5 is by ", singleScore.getPlayer_name() + " with a score of " +
                singleScore.getScore());

        Log.i("divider", "====================");
        // Calling SQL statement
        List<HighScoreClass> top5HiScores = db.getTopFiveScores();

        for (HighScoreClass hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        HighScoreClass hiScore = top5HiScores.get(top5HiScores.size() - 1);
        // hiScore contains the 5th highest score
        Log.i("fifth Highest score: ", String.valueOf(hiScore.getScore()) );

        // simple test to add a hi score
        int myCurrentScore = 40;
        // if 5th highest score < myCurrentScore, then insert new score
        if (hiScore.getScore() < myCurrentScore) {
            db.addHiScore(new HighScoreClass("08 DEC 2020", "Elrond", 40));
        }

        Log.i("divider", "====================");
        // Calling SQL statement
        top5HiScores = db.getTopFiveScores();
        List<String> scoresStr;
        scoresStr = new ArrayList<>();

        int j = 1;
        for (HighScoreClass hs : top5HiScores) {
            String log =
                    "Id: " + hs.getScore_id() +
                            ", Date: " + hs.getGame_date() +
                            " , Player: " + hs.getPlayer_name() +
                            " , Score: " + hs.getScore();

            // store score in string array
            scoresStr.add(j++ + " : "  +
                    hs.getPlayer_name() + "\t" +
                    hs.getScore());
            // Writing HiScore to log
            Log.i("Score: ", log);
        }

        Log.i("divider", "====================");
        Log.i("divider", "Scores in list <>>");
        for (String ss : scoresStr) {
            Log.i("Score: ", ss);
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresStr);
        ScoreBoard.setAdapter(itemsAdapter);
    }

    public void playAgain(View view) {
        Intent mainActivity = new Intent(view.getContext(), MainActivity.class);
        startActivity(mainActivity);
        finish();
    }
}