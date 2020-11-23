package edu.adollard.miniproject2;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.Date;

public class MainPage extends AppCompatActivity {

    double ranM, caloriesB;
    int Hour, Minute, Second;
    TextView date, Ran, Burned, TimeTaken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        date = findViewById(R.id.txtDate);
        Ran = findViewById(R.id.txtRan);
        Burned = findViewById(R.id.txtBurned);
        TimeTaken = findViewById(R.id.txtTimetaken);


        //Get the date
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate = dateFormat.format(c.getTime());
        date.setText(formattedDate);


        ranM = getIntent().getDoubleExtra("stepsView", ranM);
        //Calories burned
        caloriesB = ranM * 0.04;
        Burned.setText(String.valueOf(caloriesB));
        //Number of metres ran
        ranM = ranM *0.8;
        Ran.setText(ranM + " metres");

        //Get time taken
        Hour = getIntent().getIntExtra("timeViewHour", Hour);
        Minute = getIntent().getIntExtra("timeViewMinute", Minute);
        Second = getIntent().getIntExtra("timeViewSecond", Second);
        TimeTaken.setText(Hour + "H " + Minute + "M " + Second + "S");
    }



    //Return user to main page
    public void doReturn(View view) {
        Intent Splash = new Intent(view.getContext(), MainActivity.class);
        startActivity(Splash);
    }
}