package com.example.dietpro.pojo;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class DietResponseWrapper implements Serializable {

   private List<DietInfo> dietInfoList;

    public List<DietInfo> getDietInfoList() {
        return dietInfoList;
    }

    public void setDietInfoList(List<DietInfo> dietInfoList) {
        this.dietInfoList = dietInfoList;
    }
}