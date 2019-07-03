package com.example.android.yagami;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CrimeActivity extends AppCompatActivity {
    EditText year;
    EditText month;
    EditText latitude;
    EditText longitude;
    Button crime_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime);
        year = findViewById(R.id.yearText);
        month = findViewById(R.id.monthText);
        latitude = findViewById(R.id.latitudeText);
        longitude = findViewById(R.id.longitudeText);
        crime_button = findViewById(R.id.crime_button);

        crime_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getYear = year.getText().toString();
                String getMonth = month.getText().toString();
                String getLatitude = latitude.getText().toString();
                String getLongitude = longitude.getText().toString();
                if("".equals(getYear)  || "".equals(getMonth) || "".equals(getLatitude) || "".equals(getLongitude)){
                    Toast.makeText(CrimeActivity.this, "Enter Valid Data", Toast.LENGTH_SHORT).show();
                }
                else{
                    year.setText("");
                    month.setText("");
                    latitude.setText("");
                    longitude.setText("");
                    Intent intent = new Intent(CrimeActivity.this, CrimeListActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("key1",getYear);
                    bundle.putString("key2",getMonth);
                    bundle.putString("key3",getLatitude);
                    bundle.putString("key4",getLongitude);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }

            }
        });
    }
}