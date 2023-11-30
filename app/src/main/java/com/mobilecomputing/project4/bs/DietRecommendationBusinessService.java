package com.mobilecomputing.project4.bs;

import com.google.gson.Gson;
import com.mobilecomputing.project4.dao.FlaskDAO;
import com.mobilecomputing.project4.pojo.DietRecommendationCallback;
import com.mobilecomputing.project4.pojo.DietResponseWrapper;
import com.mobilecomputing.project4.pojo.Symptoms;
import com.mobilecomputing.project4.pojo.SymptomsWrapper;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DietRecommendationBusinessService
{
    private final Executor executor = Executors.newSingleThreadExecutor();
    DietResponseWrapper response = new DietResponseWrapper();


    //Call this method from activity class for the API response.
    public void getDietRecommendations(final DietRecommendationCallback callback, SymptomsWrapper symptomsWrapper)//Other Params need to be added accordingly
    {

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {

                    //Serialize Input into the SymptomsWrapper class before calling getResponse, which is the structure Flask API would expect
                    response = getResponse(symptomsWrapper);
                    //Mock response
                    response.setRes("API Call Complete");
                    //Deserialize output into pojo, ie, the DietResponseWrapper class to process on the UI.
                    callback.onComplete(response);
                    //This would be added while integrating the code base
                    //return response

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       // System.out.println("Response is " +response);
    }

    private DietResponseWrapper getResponse(SymptomsWrapper symptomsWrapper) throws InterruptedException {
        System.out.println("In getResponse");
        String payload=null; // Payload variable initialization
        String apiUrl = ""; // Would be changed once the ML model has been hosted.
        String apiResponse="";//Mock API response string, until integration
        //API Call method here.
        Thread.sleep(5000); //To mock API calls for testing

        payload = new Gson().toJson(symptomsWrapper);
        System.out.println("Calling API with URL : "+apiUrl+" with payload : "+payload);
        //apiResponse = new FlaskDAO().executePostRequest(payload,apiUrl); // Calls the API

        DietResponseWrapper dietResponse = new Gson().fromJson(apiResponse,DietResponseWrapper.class);

        System.out.println("Exit getResponse");
        return response;
    }
}
