package com.example.dietpro.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HealthData
{
    private int age;
    private String gender;
    private int weight;
    private String height;

    private String mood;

    private String smoking;
    private String medication;
    private String alcoholConsumption;

    private String waterIntake;

    @SerializedName("heartrate")
    @Expose
    private Float heartRate;
    @SerializedName("resprate")
    @Expose
    private Float respiratoryRate;



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getSmoking() {
        return smoking;
    }

    public void setSmoking(String smoking) {
        this.smoking = smoking;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getAlcoholConsumption() {
        return alcoholConsumption;
    }

    public void setAlcoholConsumption(String alcoholConsumption) {
        this.alcoholConsumption = alcoholConsumption;
    }

    public String getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(String waterIntake) {
        this.waterIntake = waterIntake;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Float getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Float heartRate) {
        this.heartRate = heartRate;
    }

    public Float getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(Float respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }
}
