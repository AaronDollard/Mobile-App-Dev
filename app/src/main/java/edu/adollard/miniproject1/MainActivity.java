package edu.adollard.miniproject1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.NavigableMap;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    int band1 = -99, band2 = -99, bandsTogether = 0, listPosition= 0, counter = 0;
    double bandsMulti = -99, band3multiDec = -99, tolerance = 0, MaxTolDec = 0, MinTolDec = 0, resistanceValueDec = 0;
    long band3multi = -99, resistanceValue = 0, MaxTol = 0, MinTol = 0;

    TextView Resistance, Tolerance, Tolerance2, MinMaxTolerance,
            band1D, band2D, band3D, band4D,
            btn1, btn2, btn3, btn4, btn_calc, btn_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Text views for the band numbers
        band1D = findViewById(R.id.txt_band1);
        band2D = findViewById(R.id.txt_band2);
        band3D = findViewById(R.id.txt_band3);
        band4D = findViewById(R.id.txt_band4);

        //buttons for the bands
        btn1 = findViewById(R.id.btn_band1);
        btn2 = findViewById(R.id.btn_band2);
        btn3 = findViewById(R.id.btn_band3);
        btn4 = findViewById(R.id.btn_band4);
        btn_calc = findViewById(R.id.btn_calc);
        btn_reset = findViewById(R.id.btn_calc2);
        Tolerance = findViewById(R.id.txtView_toleranceValue);
        Tolerance2 = findViewById(R.id.txtView_toleranceValue2);
    }

    //Do the calculation for the resistor using the inputted colours
    public void doCalc(View view) { //Button to calculate the resistor

        if (band1 != -99 && band2 != -99 && band3multi != -99  || band3multiDec != -99){
            btn_calc.setVisibility(View.GONE);
            btn_reset.setVisibility(View.VISIBLE);
            Resistance = findViewById(R.id.txtView_resistValue);
            Tolerance = findViewById(R.id.txtView_toleranceValue);
            MinMaxTolerance = findViewById(R.id.txtView_toleranceValue2);

            String i1 = Integer.toString(band1);
            String i2 = Integer.toString(band2);
            String i = i1 + i2; // Concatenate both strings

            bandsTogether = Integer.parseInt(i); // Convert the concatenated string to an int

            if (band3multiDec != 0) //If the calc is decimal values
            {
                resistanceValueDec = bandsTogether * band3multiDec;

                if (tolerance != 0){
                    Tolerance.setText("Formula: " + bandsTogether + "x" + band3multiDec + "±" + tolerance + "%"  + " = " + resistanceValueDec+ " Ohms");
                    Resistance.setText("Resistance: " + resistanceValueDec +" Ohms");
                    tolerance = (resistanceValueDec * tolerance) /100;

                    MinTolDec = (resistanceValueDec - tolerance);
                    MaxTolDec = (resistanceValueDec + tolerance);
                    MinMaxTolerance.setText("Minimum resistance: " + MinTolDec + "Ω\nMaximum resistance: " + MaxTolDec +"Ω");
                }

                if (tolerance == 0 ){
                    Tolerance.setText("Formula: " + bandsTogether + "x" + band3multiDec  + " = " + resistanceValueDec + " Ohms");
                    Resistance.setText("Resistance: " + resistanceValueDec +" Ohms");
                }

            }

            if (band3multi != 0) //If the calc is longs
            {
                if (tolerance != 0)
                {
                    resistanceValue = (bandsTogether * band3multi);
                    Tolerance.setText("Formula: " + bandsTogether + " x " + band3multi + "±" + tolerance + "%"  + " = " + resistanceValue + " Ohms");
                    Resistance.setText("Resistance: " + resistanceValue +" Ohms");
                    tolerance = (resistanceValue * tolerance) /100;

                    MinTol = (long) (resistanceValue - tolerance);
                    MaxTol = (long) (resistanceValue + tolerance);
                    MinMaxTolerance.setText("Minimum resistance: " + MinTol + "Ω\nMaximum resistance: " + MaxTol +"Ω");
                }

                if (tolerance == 0)
                {
                    resistanceValue = (bandsTogether * band3multi);
                    Tolerance.setText("Formula: " + bandsTogether + " x " + band3multi + " = " + resistanceValue + " Ohms");
                    Resistance.setText("Resistance: " + resistanceValue +" Ohms");
                }
            }
        }

        else {
            Toast.makeText(this, "Error! Not enough bands selected!", Toast.LENGTH_SHORT).show();
        }
    }

    public void doClick_Band1(View view) { //Band 1
        listPosition = 1;
        PopupMenu popup_band1 = new PopupMenu(this, view); //Makes the popup
        popup_band1.setOnMenuItemClickListener(this); //Listens for when this is clicked
        popup_band1.inflate(R.menu.popup_menu); //Calls the popup menu from the popup xml
        popup_band1.show(); //Shows the popup on screen
    }

    public void doClick_Band2(View view) { //Band 2
        listPosition = 2;
        PopupMenu popup_band2 = new PopupMenu(this, view);
        popup_band2.setOnMenuItemClickListener(this);
        popup_band2.inflate(R.menu.popup_menu);
        popup_band2.show();
    }

    public void doClick_Band3(View view) { //Band 3
        listPosition = 3;
        PopupMenu popup_band3 = new PopupMenu(this, view);
        popup_band3.setOnMenuItemClickListener(this);
        popup_band3.inflate(R.menu.popup_menu_band3); //Calls the popup menu from the popup xml for band 3
        popup_band3.show();
    }

    public void doClick_Band4(View view) {
        listPosition = 4;
        PopupMenu popup_band4 = new PopupMenu(this, view);
        popup_band4.setOnMenuItemClickListener(this);
        popup_band4.inflate(R.menu.popup_menu_band4); //Calls the popup menu from the popup xml for band 3
        popup_band4.show();
    }

    public void doReset(View view) { //Reset the entered resistor values
        counter-=1;
        Resistance = findViewById(R.id.txtView_resistValue);
        band1 = 0;
        band2 = 0 ;
        bandsTogether = 0;
        listPosition= 0;
        counter = 0;
        bandsMulti = 0 ;
        band3multiDec = 0;
        tolerance = 0;
        MaxTolDec = 0;
        MinTolDec = 0 ;
        resistanceValueDec = 0;
        band3multi = 0;
        resistanceValue = 0;
        MaxTol = 0;
        MinTol = 0;
        Resistance.setText("Ω");
        btn_calc.setVisibility(View.VISIBLE);
        Tolerance.setText("");
        MinMaxTolerance.setText("");
        band1D.setText("");
        band2D.setText("");
        band3D.setText("");
        band4D.setText("");
        btn1.setBackgroundColor(Color.rgb(214,215,215));
        btn2.setBackgroundColor(Color.rgb(214,215,215));
        btn3.setBackgroundColor(Color.rgb(214,215,215));
        btn4.setBackgroundColor(Color.rgb(214,215,215));
        btn_reset.setBackgroundColor(Color.rgb(214,215,215));
        Resistance.setBackgroundColor(Color.rgb(234,205,104));
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        //This is what happens when you click something in the popup for the bands
        switch (listPosition) { //Watches for which band button was pressed
            case 1: //Case 1 being button 1
                switch (item.getItemId()) { //Checks which item in the dropdown was clicked
                    case R.id.item1: //In this case item 1
                        //Toast.makeText(this, "Black selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(37,37,37));
                        band1 = 0;
                        band1D.setText("" + band1); //Displays values of selected colour in box above the button
                        return true;

                    case R.id.item2:
                        //Toast.makeText(this, "Brown selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(101,79,47));
                        band1 = 1;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item3:
                        //Toast.makeText(this, "Red selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(156,26,26));
                        band1 = 2;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item4:
                        //Toast.makeText(this, "Orange selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(255,152,0));
                        band1 = 3;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item5:
                        //Toast.makeText(this, "Yellow selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(255,235,59));
                        band1 = 4;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item6:
                        //Toast.makeText(this, "Green selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(76,175,80));
                        band1 = 5;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item7:
                        //Toast.makeText(this, "Blue selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(3,169,244));
                        band1 = 6;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item8:
                        //Toast.makeText(this, "Violet selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(130,97,159));
                        band1 = 7;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item9:
                        //Toast.makeText(this, "Gray selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(149,148,147));
                        band1 = 8;
                        band1D.setText("" + band1);
                        return true;

                    case R.id.item10:
                        //Toast.makeText(this, "White selected", Toast.LENGTH_SHORT).show();
                        btn1.setBackgroundColor(Color.rgb(255,255,255));
                        band1 = 9;
                        band1D.setText("" + band1);
                        return true;

                    default:
                        return false;
                }

            case 2:
                switch (item.getItemId()) {
                    case R.id.item1:
                        //Toast.makeText(this, "Black selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(37,37,37));
                        band2 = 0;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item2:
                        //Toast.makeText(this, "Brown selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(101,79,47));
                        band2 = 1;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item3:
                        //Toast.makeText(this, "Red selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(156,26,26));
                        band2 = 2;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item4:
                        //Toast.makeText(this, "Orange selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(255,152,0));
                        band2 = 3;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item5:
                        //Toast.makeText(this, "Yellow selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(255,235,59));
                        band2 = 4;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item6:
                        //Toast.makeText(this, "Green selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(76,175,80));
                        band2 = 5;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item7:
                        //Toast.makeText(this, "Blue selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(3,169,244));
                        band2 = 6;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item8:
                        //Toast.makeText(this, "Violet selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(130,97,159));
                        band2 = 7;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item9:
                        //Toast.makeText(this, "Gray selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(149,148,147));
                        band2 = 8;
                        band2D.setText("" + band2);
                        return true;

                    case R.id.item10:
                        //Toast.makeText(this, "White selected", Toast.LENGTH_SHORT).show();
                        btn2.setBackgroundColor(Color.rgb(255,255,255));
                        band2 = 9;
                        band2D.setText("" + band2);
                        return true;

                    default:
                        return false;
                }

            case 3:
                switch (item.getItemId()) {
                    case R.id.item1:
                        //Toast.makeText(this, "Black selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(37,37,37));
                        band3multi = 1;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item2:
                        //Toast.makeText(this, "Brown selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(101,79,47));
                        band3multi = 10;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item3:
                        //Toast.makeText(this, "Red selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(156,26,26));
                        band3multi = 100;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item4:
                        //Toast.makeText(this, "Orange selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(255,152,0));
                        band3multi = 1000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item5:
                        //Toast.makeText(this, "Yellow selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(255,235,59));
                        band3multi = 10000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item6:
                        //Toast.makeText(this, "Green selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(76,175,80));
                        band3multi = 100000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item7:
                        // Toast.makeText(this, "Blue selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(3,169,244));
                        band3multi = 1000000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item8:
                        //Toast.makeText(this, "Violet selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(130,97,159));
                        band3multi = 10000000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item9:
                        //Toast.makeText(this, "Gray selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(149,148,147));
                        band3multi = 100000000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item10:
                        //Toast.makeText(this, "White selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(255,255,255));
                        band3multi = 1000000000;
                        band3D.setText("x" + band3multi);
                        return true;

                    case R.id.item11:
                        //Toast.makeText(this, "Gold selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(255,221,0));
                        band3multiDec = 0.1;
                        band3D.setText("x" + band3multiDec);
                        return true;

                    case R.id.item12:
                        //Toast.makeText(this, "Silver selected", Toast.LENGTH_SHORT).show();
                        btn3.setBackgroundColor(Color.rgb(151,151,151));
                        band3multiDec = 0.01;
                        band3D.setText("x" + band3multiDec);
                        return true;

                    default:
                        return false;
                }

            case 4:
                switch (item.getItemId()) {
                    case R.id.item2:
                        //Toast.makeText(this, "Brown selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(101,79,47));
                        tolerance = 1;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item3:
                        //Toast.makeText(this, "Red selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(156,26,26));
                        tolerance = 2;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item6:
                        //Toast.makeText(this, "Green selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(76,175,80));
                        tolerance = 0.5;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item7:
                        //Toast.makeText(this, "Blue selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(3,169,244));
                        tolerance = 0.25;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item8:
                        //Toast.makeText(this, "Violet selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(130,97,159));
                        tolerance = 0.1;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item9:
                        //Toast.makeText(this, "Gray selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(149,148,147));
                        tolerance = 0.05;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item11:
                        //Toast.makeText(this, "Gold selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(255,221,0));
                        tolerance = 5;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item12:
                        //Toast.makeText(this, "Silver selected", Toast.LENGTH_SHORT).show();
                        btn4.setBackgroundColor(Color.rgb(151,151,151));
                        tolerance = 10;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    case R.id.item13:
                        //Toast.makeText(this, "No tolerance selected", Toast.LENGTH_SHORT).show();
                        tolerance = 20;
                        band4D.setText("±" + tolerance +"%");
                        return true;

                    default:
                        return false;
                }
            default:
                return false;
        }
    }
}



