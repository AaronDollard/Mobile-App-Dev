package edu.adollard.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText weight, height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.txt_weight);
        height = findViewById(R.id.txt_height);
    }

    public void doReset(View view) { //Resets the values to 0 within the box
        weight.setText("");
        height.setText("");
    }

    public void calcBMI(View view) { //Goes to the next page for the bmi after calc
        Intent BMIResult = new Intent(view.getContext(), bmiResult.class);
        Boolean weightPass = false;
        Boolean heightPass = false;

        int kg = Integer.valueOf(weight.getText().toString()); //Sends these vars to next page
        if (kg < 20 || kg > 200) {
            Toast.makeText(this, kg + " is an invalid weight. Please try again.", Toast.LENGTH_LONG).show();
        }
        else {
            BMIResult.putExtra("weight", kg);
            weightPass = true;
        }
        int cm = Integer.valueOf(height.getText().toString());

        if (cm < 80 || cm > 300) {
            Toast.makeText(this, kg + " is an invalid height. Please try again.", Toast.LENGTH_LONG).show();
        }
        else {
            BMIResult.putExtra("height", cm);
            heightPass = true;
        }
        BMIResult.putExtra("height", cm);

        if (weightPass && heightPass == true)
        {
            startActivity(BMIResult);
            //weight.setText(""); //clears the bars for next entry
            //height.setText("");
        }
        else
        {
            Toast.makeText(this, "Please enter a valid value.", Toast.LENGTH_LONG).show();
        }
    }
}