package edu.adollard.assignment3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity { //start

    TextView tvDisplay; //Mapping for fields and button
    EditText etName, etAddress;
    private IntentIntegrator qrScan = new IntentIntegrator(this);  // ZXing lib linker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Finishing linking
        tvDisplay = findViewById(R.id.tvDisplay);
        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
    }

    public void doScan(View view) { //When pressed does the scan on the QR Code
        qrScan.initiateScan(); //Starts the scan
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) //If QR has nothing in it
        {
            if (result.getContents() == null) {
                Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
            } else {

                try { //If QR contains data converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //Setting values to textviews
                    etName.setText(obj.getString("title"));
                    etAddress.setText(obj.getString("website"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //If control comes here
                    //That means the encoded format not matches in this case you can display
                    //Whatever data is available on the QR to a toast.
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}


