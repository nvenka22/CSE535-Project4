package com.mobilecomputing.project4.pojo;

import java.util.ArrayList;
import java.util.List;

public class DietResponseWrapper
{
    // Mock structure for now, change when we integrate this with the Flask API model. TBD during integration phase.
    List<String> dietOptions;

    public List<String> getDietOptions() {
        if(dietOptions == null)
            dietOptions = new ArrayList<>();
        return dietOptions;
    }

    public void setDietOptions(List<String> dietOptions) {
        this.dietOptions = dietOptions;
    }
}
