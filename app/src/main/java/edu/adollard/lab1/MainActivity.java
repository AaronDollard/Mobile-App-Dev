package edu.adollard.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userName = null; //This is for when the user inputs their name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link up the users name
        userName = findViewById(R.id.nameBox); //This is what the person enters.
    }

    //This is created for the onclick of the Name button
    public void clickEnterName(View view) {
        String name = null;
        name = userName.getText().toString();
        Toast.makeText(this, "Hi " + name, Toast.LENGTH_LONG).show();
    }
}