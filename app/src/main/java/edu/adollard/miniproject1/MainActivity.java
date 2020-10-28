package edu.adollard.miniproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    int bandsValue, band1, band2, bandsTogether;
    double bandsMulti, tolerance;
    long band3multi, resistanceValue;
    TextView Resistance, band1D, band2D, band3D, band4D, btn1, btn2, btn3, btn4; //Displays the result

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        band1D = findViewById(R.id.txt_band1);
        btn1 = findViewById(R.id.btn_band1);
        btn2 = findViewById(R.id.btn_band2);
        btn3 = findViewById(R.id.btn_band3);
        btn4 = findViewById(R.id.btn_band4);
    }

    //Do the calculation for the resistor using the inputted colours
    public void doCalc(View view) { //Button to calculate the resistor
        Resistance = findViewById(R.id.txtView_resistValue);

            String i1 = Integer.toString(band1);
            String i2 = Integer.toString(band2);
            // Concatenate both strings
            String i = i1 + i2;
            // Convert the concatenated string to an int
            bandsTogether = Integer.parseInt(i);

        resistanceValue = bandsTogether * band3multi;
        Resistance.setText("Resistance: " + resistanceValue +" Ohms");

    }

    public void doClick_Band1(View view) { //Band 1
        PopupMenu popup_band1 = new PopupMenu(this, view); //Makes the popup
        popup_band1.setOnMenuItemClickListener(this); //Listens for when this is clicked
        popup_band1.inflate(R.menu.popup_menu); //Calls the popup menu from the popup xml
        popup_band1.show(); //Shows the popup on screen
        band1 = bandsValue; //Sets the value of selected colour

        if (band1 == 0){
            btn1.setBackgroundColor(Color.rgb(37,37,37));
        }
        else if (band1 == 1){
            btn1.setBackgroundColor(Color.rgb(101,79,47));
        }
        else if (band1 == 2){
            btn1.setBackgroundColor(Color.rgb(156,26,26));
        }
        else if (band1 == 3){
            btn1.setBackgroundColor(Color.rgb(255,152,0));
        }
        else if (band1 == 4){
            btn1.setBackgroundColor(Color.rgb(255,235,59));
        }
        else if (band1 == 5){
            btn1.setBackgroundColor(Color.rgb(76,175,80));
        }
        else if (band1 == 6){
            btn1.setBackgroundColor(Color.rgb(3,169,244));
        }
        else if (band1 == 7){
            btn1.setBackgroundColor(Color.rgb(130,97,159));
        }
        else if (band1 == 8){
            btn1.setBackgroundColor(Color.rgb(149,148,147));
        }
        else if (band1 == 9){
            btn1.setBackgroundColor(Color.rgb(255,255,255));
        }
        else if (band1 == 10){
            btn1.setBackgroundColor(Color.rgb(255,221,0));
        }
        else if (band1 == 11){
            btn1.setBackgroundColor(Color.rgb(151,151,151));
        }

        bandsValue = 0; //Resets value after being applied
        band1D.setText("" + band1); //Displays values of selected colour in box above the button
    }

    public void doClick_Band2(View view) { //Band 2
        PopupMenu popup_band2 = new PopupMenu(this, view);
        popup_band2.setOnMenuItemClickListener(this);
        popup_band2.inflate(R.menu.popup_menu);
        popup_band2.show();
        band2 = bandsValue;

        if (band2 == 0){
            btn2.setBackgroundColor(Color.rgb(37,37,37));
        }
        else if (band2 == 1){
            btn2.setBackgroundColor(Color.rgb(101,79,47));
        }
        else if (band2 == 2){
            btn2.setBackgroundColor(Color.rgb(156,26,26));
        }
        else if (band2 == 3){
            btn2.setBackgroundColor(Color.rgb(255,152,0));
        }
        else if (band2 == 4){
            btn2.setBackgroundColor(Color.rgb(255,235,59));
        }
        else if (band2 == 5){
            btn2.setBackgroundColor(Color.rgb(76,175,80));
        }
        else if (band2 == 6){
            btn2.setBackgroundColor(Color.rgb(3,169,244));
        }
        else if (band2 == 7){
            btn2.setBackgroundColor(Color.rgb(130,97,159));
        }
        else if (band2 == 8){
            btn2.setBackgroundColor(Color.rgb(149,148,147));
        }
        else if (band2 == 9){
            btn2.setBackgroundColor(Color.rgb(255,255,255));
        }
        else if (band2 == 10){
            btn2.setBackgroundColor(Color.rgb(255,221,0));
        }
        else if (band2 == 11){
            btn2.setBackgroundColor(Color.rgb(151,151,151));
        }

        bandsValue = 0;
        band2D = findViewById(R.id.txt_band2);
        band2D.setText("" + band2);

    }

    public void doClick_Band3(View view) { //Band 3
        PopupMenu popup_band3 = new PopupMenu(this, view);
        popup_band3.setOnMenuItemClickListener(this);
        popup_band3.inflate(R.menu.popup_menu_band3); //Calls the popup menu from the popup xml for band 3
        popup_band3.show();

        band3multi = (new Double(bandsMulti)).longValue();

        if (band3multi == 1){
            btn3.setBackgroundColor(Color.rgb(37,37,37));
        }
        else if (band3multi == 10){
            btn3.setBackgroundColor(Color.rgb(101,79,47));
        }
        else if (band3multi == 100){
            btn3.setBackgroundColor(Color.rgb(156,26,26));
        }
        else if (band3multi == 1000){
            btn3.setBackgroundColor(Color.rgb(255,152,0));
        }
        else if (band3multi == 10000){
            btn3.setBackgroundColor(Color.rgb(255,235,59));
        }
        else if (band3multi == 100000){
            btn3.setBackgroundColor(Color.rgb(76,175,80));
        }
        else if (band3multi == 1000000){
            btn3.setBackgroundColor(Color.rgb(3,169,244));
        }
        else if (band3multi == 10000000){
            btn3.setBackgroundColor(Color.rgb(130,97,159));
        }
        else if (band3multi == 100000000){
            btn3.setBackgroundColor(Color.rgb(149,148,147));
        }
        else if (band3multi == 1000000000){
            btn3.setBackgroundColor(Color.rgb(255,255,255));
        }
        else if (band3multi == 0.1){
            btn3.setBackgroundColor(Color.rgb(255,221,0));
        }
        else if (band3multi == 0.01){
            btn3.setBackgroundColor(Color.rgb(151,151,151));
        }

        bandsMulti = 0;
        band3D = findViewById(R.id.txt_band3);
        band3D.setText("" + band3multi);

    }

    public void doClick_Band4(View view) {
        PopupMenu popup_band4 = new PopupMenu(this, view);
        popup_band4.setOnMenuItemClickListener(this);
        popup_band4.inflate(R.menu.popup_menu_band4); //Calls the popup menu from the popup xml for band 3
        popup_band4.show();

        tolerance = (new Double(tolerance)).longValue();

        if (tolerance == 0){
            btn4.setBackgroundColor(Color.rgb(37,37,37));
        }
        else if (tolerance == 1){
            btn4.setBackgroundColor(Color.rgb(101,79,47));
        }
        else if (tolerance == 2){
            btn4.setBackgroundColor(Color.rgb(156,26,26));
        }
        else if (tolerance == .5){
            btn4.setBackgroundColor(Color.rgb(76,175,80));
        }
        else if (tolerance == 0.25){
            btn4.setBackgroundColor(Color.rgb(3,169,244));
        }
        else if (tolerance == 0.1){
            btn4.setBackgroundColor(Color.rgb(130,97,159));
        }
        else if (tolerance == 0.05){
            btn4.setBackgroundColor(Color.rgb(149,148,147));
        }
        else if (tolerance == 5){
            btn4.setBackgroundColor(Color.rgb(255,221,0));
        }
        else if (tolerance == 10){
            btn4.setBackgroundColor(Color.rgb(151,151,151));
        }
        else if (tolerance == 20){
            btn4.setBackgroundColor(Color.rgb(255,255,255));
        }

        //tolerance = 0;
        band4D = findViewById(R.id.txt_band4);
        band4D.setText("" + tolerance);
    }

    public void doReset(View view) { //Reset the entered resistor values
        Resistance = findViewById(R.id.txtView_resistValue);
        bandsValue = 0;
        bandsMulti = 0;
        band1 = 0;
        band2 = 0;
        band3multi = 0;
        bandsTogether = 0;
        resistanceValue = 0;

        Resistance.setText("Ω");
        band1D.setText("");
        band2D.setText("");
        band3D.setText("");

        btn1.setBackgroundColor(Color.rgb(214,215,215));
        btn2.setBackgroundColor(Color.rgb(214,215,215));
        btn3.setBackgroundColor(Color.rgb(214,215,215));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        //This is what happens when you click something in the popup for the first two bands
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Black selected", Toast.LENGTH_SHORT).show();
                bandsValue = 0;
                bandsMulti = 1;
                return true;

            case R.id.item2:
                Toast.makeText(this, "Brown selected", Toast.LENGTH_SHORT).show();
                bandsValue = 1;
                bandsMulti = 10;
                tolerance = 1;
                return true;

            case R.id.item3:
                Toast.makeText(this, "Red selected", Toast.LENGTH_SHORT).show();
                bandsValue = 2;
                bandsMulti = 100;
                tolerance = 2;
                return true;

            case R.id.item4:
                Toast.makeText(this, "Orange selected", Toast.LENGTH_SHORT).show();
                bandsValue = 3;
                bandsMulti = 1000;
                return true;

            case R.id.item5:
                Toast.makeText(this, "Yellow selected", Toast.LENGTH_SHORT).show();
                bandsValue = 4;
                bandsMulti = 10000;
                return true;

            case R.id.item6:
                Toast.makeText(this, "Green selected", Toast.LENGTH_SHORT).show();
                bandsValue = 5;
                bandsMulti = 100000;
                tolerance = 0.5;
                return true;

            case R.id.item7:
                Toast.makeText(this, "Blue selected", Toast.LENGTH_SHORT).show();
                bandsValue = 6;
                bandsMulti = 1000000;
                tolerance = 0.25;
                return true;

            case R.id.item8:
                Toast.makeText(this, "Violet selected", Toast.LENGTH_SHORT).show();
                bandsValue = 7;
                bandsMulti = 10000000;
                tolerance = 0.1;
                return true;

            case R.id.item9:
                Toast.makeText(this, "Gray selected", Toast.LENGTH_SHORT).show();
                bandsValue = 8;
                bandsMulti = 100000000;
                tolerance = 0.05;
                return true;

            case R.id.item10:
                Toast.makeText(this, "White selected", Toast.LENGTH_SHORT).show();
                bandsValue = 9;
                bandsMulti = 1000000000;
                return true;

            case R.id.item11:
                Toast.makeText(this, "Gold selected", Toast.LENGTH_SHORT).show();
                bandsMulti = 0.1;
                tolerance = 5;
                return true;

            case R.id.item12:
                Toast.makeText(this, "Silver selected", Toast.LENGTH_SHORT).show();
                bandsMulti = 0.01;
                tolerance = 10;
                return true;

            case R.id.item13:
                Toast.makeText(this, "No tolerance selected", Toast.LENGTH_SHORT).show();
                tolerance = 20;
                return true;

            default:
                return false;
        }
    }


}



