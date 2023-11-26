package com.mobilecomputing.project4.pojo;

import java.util.List;

public class SymptomsWrapper
{
    private HealthData healthData;
    private List<Symptoms> symptomsList;

    public List<Symptoms> getSymptomsList() {
        return symptomsList;
    }

    public void setSymptomsList(List<Symptoms> symptomsList) {
        this.symptomsList = symptomsList;
    }


}
