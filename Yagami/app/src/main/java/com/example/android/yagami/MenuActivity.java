package com.example.android.yagami;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {
    LinearLayout force_button;
    LinearLayout crime_button;
    LinearLayout fav_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        force_button = (LinearLayout) findViewById(R.id.force_button);
        crime_button = (LinearLayout) findViewById(R.id.crime_button);
        fav_button = (LinearLayout) findViewById(R.id.fav_button);
        force_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });
        crime_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, CrimeActivity.class);
                startActivity(intent);

            }
        });
        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, FavoriteActivity.class);
                startActivity(intent);

            }
        });
    }
}
