package com.mobilecomputing.project4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        Button MoodButton = (Button) findViewById(R.id.btnMood);

        MoodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(getApplicationContext(), MoodActivity.class);
                startActivity(int2);

            }
        });

        Button SymptomsButton = (Button) findViewById(R.id.btnSymptoms);

        SymptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(getApplicationContext(), SymptomsActivity.class);
                startActivity(int2);

            }
        });

        Button WaterButton = (Button) findViewById(R.id.btnWater);

        WaterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int3 = new Intent(getApplicationContext(), WaterActivity.class);
                startActivity(int3);

            }
        });

        Button MetricsButton = (Button) findViewById(R.id.btnMetrics);

        MetricsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int4 = new Intent(getApplicationContext(), MetricsActivity.class);
                startActivity(int4);

            }
        });

        AppCompatImageButton ProfileButton = (AppCompatImageButton) findViewById(R.id.btnProfile);

        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int5 = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(int5);

            }
        });


    }
}
