package com.example.dietpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dietpro.bs.SymptomsAggregator;

public class WaterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        Button profileButton = findViewById(R.id.btnSave);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText waterIntake  = findViewById(R.id.editLitersDrank);
                String waterConsumption = waterIntake.getText().toString();
                SymptomsAggregator aggregator = (SymptomsAggregator) getIntent().getSerializableExtra("symptomAggregator");
                aggregator.setWaterIntake(waterConsumption);
                // Handle profile button click, for now, navigate to ProfileActivity
                Intent intent = new Intent(WaterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}