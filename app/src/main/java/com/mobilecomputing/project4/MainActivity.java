package com.mobilecomputing.project4;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mobilecomputing.project4.bs.DietRecommendationBusinessService;
import com.mobilecomputing.project4.pojo.DietRecommendationCallback;
import com.mobilecomputing.project4.databinding.ActivityMainBinding;
import com.mobilecomputing.project4.pojo.DietResponseWrapper;
import com.mobilecomputing.project4.pojo.HealthData;
import com.mobilecomputing.project4.pojo.Symptoms;
import com.mobilecomputing.project4.pojo.SymptomsWrapper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button for triggering the API calls on my component
        Button btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //The following variables are mocked to test my component. This would be taken from the UI while integration.
                String symptom1 = "fever";String rating1="7";
               String symptom2 = "cough";String rating2="10";
               String heartRate="154";String respiratoryRate="100";
               String alcohol="Y";
               List<String> symptoms = new ArrayList<>();
               List<String> ratings = new ArrayList<>();
                symptoms.add(symptom1);
                symptoms.add(symptom2);
                ratings.add(rating1);
                ratings.add(rating2);
               DietRecommendationBusinessService bs = new DietRecommendationBusinessService();
                //The callback here is implemented so that the API call does not interfere with the main thread.
                //THe callback allows the main thread and the background thread to communicate.
               SymptomsWrapper symptomsWrapper = aggregateSymptoms(symptoms,ratings,heartRate,respiratoryRate,alcohol);
                bs.getDietRecommendations(new DietRecommendationCallback() {
                    @Override
                    public void onComplete(DietResponseWrapper dietResponseWrapper) {
                        //UI display code would be here.
                        System.out.println(dietResponseWrapper.getRes());
                    }
                },symptomsWrapper);
            }
        });
    }

    private SymptomsWrapper aggregateSymptoms(List<String> symptoms,List<String> ratings, String heartRate, String respiratoryRate, String alcohol)
    {
        SymptomsWrapper symptomsWrapper = new SymptomsWrapper();
        for(int i=0;i<symptoms.size();i++)
        {
            Symptoms symptom = new Symptoms();

            symptom.setSymptom(symptoms.get(i));
            symptom.setSeverity(ratings.get(i));
            symptomsWrapper.getSymptomsList().add(symptom);

        }

        HealthData healthData = new HealthData();
        setHealthData(healthData,heartRate,respiratoryRate,alcohol);
        symptomsWrapper.setHealthData(healthData);

        return symptomsWrapper;
    }

    private void setHealthData(HealthData healthData, String heartRate, String respiratoryRate, String alcohol)
    {
        if(heartRate != null)
            healthData.setHeartRate(heartRate);

        if(respiratoryRate!=null)
                healthData.setRespiratoryRate(respiratoryRate);

        if(alcohol!=null)
                healthData.setAlcoholConsumption(alcohol);
    }


}