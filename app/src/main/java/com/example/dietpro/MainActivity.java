package com.example.dietpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dietpro.bs.DietRecommendationBusinessService;
import com.example.dietpro.bs.DietRecommendationCallback;
import com.example.dietpro.bs.SymptomsAggregator;
import com.example.dietpro.pojo.DietResponseWrapper;
import com.example.dietpro.pojo.SymptomsWrapper;

public class MainActivity extends AppCompatActivity {
    public static SymptomsAggregator aggregator = new SymptomsAggregator();
    private static String intent_aggregator_key = "symptomAggregator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton profileButton = findViewById(R.id.btnProfile);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle profile button click, for now, navigate to ProfileActivity
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra(intent_aggregator_key,aggregator);
                startActivity(intent);
            }
        });

        Button WaterIntake = findViewById(R.id.btnWater);
        WaterIntake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WaterActivity.class);
                intent.putExtra(intent_aggregator_key,aggregator);
                startActivity(intent);
            }
        });

        Button btnMood = findViewById(R.id.btnMood);
        btnMood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Mood Selection Activity
                Intent intent = new Intent(MainActivity.this, MoodActivity.class);
                startActivity(intent);
            }
        });

        Button btnMetrics = findViewById(R.id.btnMetrics);
        btnMetrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MetricsActivity.class);
                intent.putExtra(intent_aggregator_key,aggregator);
                startActivity(intent);
            }
        });

        Button btnSymptoms = findViewById(R.id.btnSymptoms);
        btnSymptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SymptomsActivity.class);
                intent.putExtra(intent_aggregator_key,aggregator);
                startActivity(intent);
            }
        });

        Button dietRecommendation = findViewById(R.id.btnRecommendations);
        dietRecommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DietRecommendationBusinessService bs = new DietRecommendationBusinessService();
                //The callback here is implemented so that the API call does not interfere with the main thread.
                //THe callback allows the main thread and the background thread to communicate.
                SymptomsWrapper symptomsWrapper = SymptomsAggregator.symptomsWrapper;
                //symptomsWrapper.getHealthData().setHeartRate(100.0);
                //symptomsWrapper.getHealthData().setAge(25);
               // symptomsWrapper.getHealthData().setRespiratoryRate(67);
               // symptomsWrapper.getHealthData().setWeight(80);
                System.out.println("CALLING APIIIII");
                bs.getDietRecommendations(new DietRecommendationCallback() {
                    @Override
                    public void onComplete(DietResponseWrapper dietResponseWrapper) {
                        //UI display code would be here.
                        System.out.println("The recommended meal type is " + dietResponseWrapper.getDietInfoList().get(0).getDiet());
                        Intent intent = new Intent(MainActivity.this, OutputActivity.class);
                        intent.putExtra("dietResponse",dietResponseWrapper);
                        startActivity(intent);

                    }
                },symptomsWrapper,getApplicationContext());
            }
        });

    }
}
