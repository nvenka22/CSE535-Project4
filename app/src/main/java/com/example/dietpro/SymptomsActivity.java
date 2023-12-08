package com.example.dietpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dietpro.bs.SymptomsAggregator;

public class SymptomsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        Spinner spinnerSymptoms = findViewById(R.id.spinnerSymptoms);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.symptoms_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSymptoms.setAdapter(adapter);

        Button btnSaveSymptoms = findViewById(R.id.btnSaveSymptoms);
        btnSaveSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedSymptom = spinnerSymptoms.getSelectedItem().toString();

                SymptomsAggregator aggregator = (SymptomsAggregator) getIntent().getSerializableExtra("symptomAggregator");
                aggregator.setSymptoms(selectedSymptom);
                showToast("Selected Symptom: " + selectedSymptom);



                Intent intent = new Intent(SymptomsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}