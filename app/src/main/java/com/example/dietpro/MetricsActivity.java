package com.example.dietpro;

import static java.lang.Math.abs;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.widget.Toast;

import com.example.dietpro.bs.SymptomsAggregator;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MetricsActivity extends AppCompatActivity {
    float breathingRate;

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
                System.out.print("In resp rate on click");
                Intent respIntent = new Intent(MetricsActivity.this, AccelerometerService.class);
                startService(respIntent);
                showToast("Respiratory Rate Calculation Under Progress");
            }
        });

        LocalBroadcastManager.getInstance(MetricsActivity.this).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {


                ArrayList<Integer> accelValuesZ;

                Bundle bundle = intent.getExtras();
                accelValuesZ = bundle.getIntegerArrayList("accelValuesZ");

                //Noise reduction from Accelerometer Z values
                ArrayList<Integer> accelValuesXDenoised = denoise(accelValuesZ, 10);

                //Peak detection algorithm running on denoised Accelerometer Z values
                int  zeroCrossings = peakFinding(accelValuesXDenoised);
                breathingRate = (zeroCrossings*60)/90;
                EditText respText = findViewById(R.id.editRespiratoryRate);
                respText.setText(""+breathingRate);
                Log.i("log", "Respiratory rate" + breathingRate);
                Toast.makeText(MetricsActivity.this, "Respiratory rate calculated!", Toast.LENGTH_SHORT).show();

                bundle.clear();
                System.gc();

            }
        }, new IntentFilter("RespDataBroadcast"));

        Button btnSaveMetrics = findViewById(R.id.btnSaveMetrics);
        btnSaveMetrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("In here at on");
                SymptomsAggregator aggregator = (SymptomsAggregator) getIntent().getSerializableExtra("symptomAggregator");
                EditText respText = findViewById(R.id.editRespiratoryRate);
                EditText heartRateText = findViewById(R.id.editHeartRate);
                aggregator.setMetricData(Float.parseFloat(heartRateText.getText().toString()),Float.parseFloat(respText.getText().toString()));
                System.out.print(new Gson().toJson(aggregator));
                showToast("Metrics Saved");
                Intent intent = new Intent(MetricsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public ArrayList<Integer> denoise(ArrayList<Integer> data, int filter){

        ArrayList<Integer> movingAvgArr = new ArrayList<>();
        int movingAvg = 0;

        for(int i=0; i< data.size(); i++){
            movingAvg += data.get(i);
            if(i+1 < filter) {
                continue;
            }
            movingAvgArr.add((movingAvg)/filter);
            movingAvg -= data.get(i+1 - filter);
        }

        return movingAvgArr;

    }

    public int peakFinding(ArrayList<Integer> data) {

        int diff, prev, slope = 0, zeroCrossings = 0;
        int j = 0;
        prev = data.get(0);

        //Get initial slope
        while(slope == 0 && j + 1 < data.size()){
            diff = data.get(j + 1) - data.get(j);
            if(diff != 0){
                slope = diff/abs(diff);
            }
            j++;
        }

        //Get total number of zero crossings in data curve
        for(int i = 1; i<data.size(); i++) {

            diff = data.get(i) - prev;
            prev = data.get(i);

            if(diff == 0) continue;

            int currSlope = diff/abs(diff);

            if(currSlope == -1* slope){
                slope *= -1;
                zeroCrossings++;
            }
        }

        return zeroCrossings;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}