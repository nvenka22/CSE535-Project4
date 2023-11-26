package com.mobilecomputing.project4.bs;

import com.mobilecomputing.project4.dao.FlaskDAO;
import com.mobilecomputing.project4.pojo.DietRecommendationCallback;
import com.mobilecomputing.project4.pojo.DietResponseWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DietRecommendationBusinessService
{
    private final Executor executor = Executors.newSingleThreadExecutor();
    DietResponseWrapper response = new DietResponseWrapper();


    //Call this method from activity class for the API response.
    public void getDietRecommendations(final DietRecommendationCallback callback)//Other Params need to be added accordingly
    {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {

                    //Serialize Input into the SymptomsWrapper class before calling getResponse, which is the structure Flask API would expect
                    response = getResponse();
                    response.setRes("API Call Complete");
                    //Deserialize output into pojo, ie, the DietResponseWrapper class to process on the UI.
                    callback.onComplete(response);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       // System.out.println("Response is " +response);
    }

    private DietResponseWrapper getResponse() throws InterruptedException {
        System.out.println("In getResponse");

        //API Call method here.
       Thread.sleep(5000); //To mock API calls for testing

        //new FlaskDAO().executePostRequest("",""); // Calls the API

        System.out.println("Exit getResponse");
        return response;
    }
}
