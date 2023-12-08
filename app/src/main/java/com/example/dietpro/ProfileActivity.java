package com.example.dietpro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.dietpro.bs.SymptomsAggregator;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button BackButton = findViewById(R.id.btnBack);
        BackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText weightInfo  = findViewById(R.id.editTextWeight);
                Integer weight = Integer.parseInt(weightInfo.getText().toString());

                EditText ageInfo = findViewById(R.id.editTextAge);
                Integer age = Integer.parseInt(ageInfo.getText().toString());

                SymptomsAggregator aggregator = (SymptomsAggregator) getIntent().getSerializableExtra("symptomAggregator");
                aggregator.setPersonalHealthData(weight,age);
                // Handle profile button click, for now, navigate to ProfileActivity
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}