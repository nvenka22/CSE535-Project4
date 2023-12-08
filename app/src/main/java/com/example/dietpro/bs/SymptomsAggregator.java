package com.example.dietpro.bs;

import com.example.dietpro.pojo.HealthData;
import com.example.dietpro.pojo.SymptomsWrapper;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.Collection;

@SuppressWarnings("serial")
public class SymptomsAggregator implements Serializable
{

    public static SymptomsWrapper symptomsWrapper = new SymptomsWrapper();

    static
    {
        HealthData healthData = symptomsWrapper.getHealthData();
        if(healthData == null)
        {
            healthData = new HealthData();
            symptomsWrapper.setHealthData(healthData);
        }


    }

    public void setSymptoms(String symptom){

        symptomsWrapper.getSymptomsList().add(symptom);
        System.out.println(symptomsWrapper.getSymptomsList().size());
        symptomsWrapper.getSymptomsList().stream().forEach(System.out::println);

    }

    public void setWaterIntake(String waterIntake)
    {
        HealthData healthData = symptomsWrapper.getHealthData();
//        if(healthData == null)
//        {
//            healthData = new HealthData();
//            symptomsWrapper.setHealthData(healthData);
//        }

        healthData.setWaterIntake(waterIntake);
        System.out.println(new Gson().toJson(symptomsWrapper));

    }

    public void setPersonalHealthData(Integer weight, Integer age)
    {
        symptomsWrapper.getHealthData().setAge(age);
        symptomsWrapper.getHealthData().setWeight(weight);
    }

    public void setMetricData(Float heartRate, Float respRate ){
        if(heartRate!=null)
            symptomsWrapper.getHealthData().setHeartRate(heartRate);

        if(respRate!=null)
            symptomsWrapper.getHealthData().setRespiratoryRate(respRate);
    }
}
