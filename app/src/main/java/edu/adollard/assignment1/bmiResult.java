package edu.adollard.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class bmiResult extends AppCompatActivity {
    double cm, kg, bmi;
    TextView bmiResult, note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_result);
        bmiResult = findViewById(R.id.txt_bmiResult);
        note = findViewById(R.id.txt_personalNote);

        kg = getIntent().getIntExtra("weight", -1);
        cm = getIntent().getIntExtra("height", -1);
        bmi = (100*100*kg)/(cm*cm);
        bmiResult.setText("Your bmi is "+ Math.round(bmi));

        if(bmi <= 18.5) {
            note.setText("You are underweight");
            note.setTextColor(Color.rgb(255,128,0));
        }else if (bmi >= 18.6 && bmi <= 24.9) {
            note.setText("Your bmi is normal");
        }else if (bmi >= 25) {
            note.setText("You are overweight");
            note.setTextColor(Color.rgb(255,128,0));
        }
        else if (bmi >= 29.9) {
        note.setText("You are obese. Please seek a medical professional");
            note.setAllCaps(true);
            note.setTextColor(Color.rgb(255,0,0));
        }
    }
    public void doExit(View view) {
        Intent Splash = new Intent(view.getContext(), MainActivity.class);
        startActivity(Splash); //Returns you to the previous page with empty boxes
    }
}