package com.mobilecomputing.project4.bs;

import android.content.Context;

import com.google.gson.Gson;
import com.mobilecomputing.project4.pojo.DietRecommendationCallback;
import com.mobilecomputing.project4.pojo.DietResponseWrapper;
import com.mobilecomputing.project4.pojo.SymptomsWrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DietRecommendationBusinessService
{
    private final Executor executor = Executors.newSingleThreadExecutor();
    DietResponseWrapper response = new DietResponseWrapper();


    //Call this method from activity class for the API response.
    public void getDietRecommendations(final DietRecommendationCallback callback, SymptomsWrapper symptomsWrapper, Context applicationContext)//Other Params need to be added accordingly
    {

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {

                    //Serialize Input into the SymptomsWrapper class before calling getResponse, which is the structure Flask API would expect
                    response = getResponse(symptomsWrapper,applicationContext);

                    //Deserialize output into pojo, ie, the DietResponseWrapper class to process on the UI.
                    callback.onComplete(response);
                    //This below line of code would be added while integrating the code base
                    //return response

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       // System.out.println("Response is " +response);
    }

    private DietResponseWrapper getResponse(SymptomsWrapper symptomsWrapper, Context context) throws InterruptedException {
        System.out.println("In getResponse");
        String payload=null; // Payload variable initialization
        String apiUrl = "localhost:8080/mock-api/get-diet-recommendations"; // Would be changed once the ML model has been hosted on a server. This is to enable local testing
        String apiResponse="";//Mock API response string, until integration
        //API Call method here.
        Thread.sleep(5000); //To mock API calls for testing

        payload = new Gson().toJson(symptomsWrapper);
        System.out.println("Calling API with URL : "+apiUrl+" with payload : "+payload);
        //apiResponse = new FlaskDAO().executePostRequest(payload,apiUrl); // Calls the API

        //To mock API Response for deserialization testing purposes
        apiResponse = getDataFromMockFile(context);
        System.out.println("Response returned as : "+apiResponse);
        DietResponseWrapper dietResponse = new Gson().fromJson(apiResponse,DietResponseWrapper.class);

        System.out.println("Exit getResponse");
        return dietResponse;
    }

    private String getDataFromMockFile(Context context)
    {
       String resp="";
        try {
            InputStream inputStream = context.getAssets().open("mock.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append("\n").append(receiveString);
                }

                inputStream.close();
                resp = stringBuilder.toString();
            }
        }
        catch (Exception exception) {
            System.out.println(exception.getLocalizedMessage());
        }

        return resp;
    }

}
