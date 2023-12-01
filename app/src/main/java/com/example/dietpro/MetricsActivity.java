package com.example.dietpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class MetricsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metrics);

        Button btnCalculateHeartRate = findViewById(R.id.btnCalculateHeartRate);
        btnCalculateHeartRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle heart rate calculation logic here
                showToast("Heart Rate Calculated");
            }
        });

        Button btnCalculateRespiratoryRate = findViewById(R.id.btnCalculateRespiratoryRate);
        btnCalculateRespiratoryRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle respiratory rate calculation logic here
                showToast("Respiratory Rate Calculated");
            }
        });

        Button btnSaveMetrics = findViewById(R.id.btnSaveMetrics);
        btnSaveMetrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Metrics Saved");
                Intent intent = new Intent(MetricsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}