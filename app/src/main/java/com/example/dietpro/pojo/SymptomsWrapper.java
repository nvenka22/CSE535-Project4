package com.example.dietpro.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SymptomsWrapper
{
    @SerializedName("healthData")
    @Expose
    private HealthData healthData;
    @SerializedName("symptoms")
    @Expose
    private List<String> symptomsList;


    public SymptomsWrapper(){
        symptomsList = new ArrayList<>();
    }

    public List<String> getSymptomsList()
    {
        if(symptomsList == null)
            symptomsList = new ArrayList<>();
        return symptomsList;
    }

    public void setSymptomsList(List<String> symptomsList) {
        this.symptomsList = symptomsList;
    }

    public HealthData getHealthData() {
        return healthData;
    }

    public void setHealthData(HealthData healthData) {
        this.healthData = healthData;
    }
}
