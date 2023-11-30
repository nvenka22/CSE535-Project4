package com.mobilecomputing.project4.pojo;

import java.util.ArrayList;
import java.util.List;

public class SymptomsWrapper
{
    private HealthData healthData;
    private List<Symptoms> symptomsList;


    public SymptomsWrapper(){
        symptomsList = new ArrayList<>();
    }
    public List<Symptoms>  getSymptomsList() {
        return symptomsList;
    }

    public void setSymptomsList(List<Symptoms> symptomsList) {
        this.symptomsList = symptomsList;
    }

    public HealthData getHealthData() {
        return healthData;
    }

    public void setHealthData(HealthData healthData) {
        this.healthData = healthData;
    }
}
