package com.mobilecomputing.project4.pojo;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class DietResponseWrapper {

    @SerializedName("Meal_Id")
    @Expose
    private String mealId;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("catagory")
    @Expose
    private String catagory;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("Veg_Non")
    @Expose
    private String vegNon;
    @SerializedName("Nutrient")
    @Expose
    private String nutrient;
    @SerializedName("Disease")
    @Expose
    private String disease;
    @SerializedName("Diet")
    @Expose
    private String diet;
    @SerializedName("Price")
    @Expose
    private Integer price;
    @SerializedName("SimScore")
    @Expose
    private Double simScore;

    public String getMealId() {
        return mealId;
    }

    public void setMealId(String mealId) {
        this.mealId = mealId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVegNon() {
        return vegNon;
    }

    public void setVegNon(String vegNon) {
        this.vegNon = vegNon;
    }

    public String getNutrient() {
        return nutrient;
    }

    public void setNutrient(String nutrient) {
        this.nutrient = nutrient;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Double getSimScore() {
        return simScore;
    }

    public void setSimScore(Double simScore) {
        this.simScore = simScore;
    }

}