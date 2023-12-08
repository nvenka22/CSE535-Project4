package com.example.dietpro.bs;

import com.example.dietpro.pojo.DietResponseWrapper;

public interface DietRecommendationCallback {
    void onComplete(DietResponseWrapper dietResponseWrapper);
}
