package com.example.dietpro.bs;

import android.content.Context;

import com.example.dietpro.dao.FlaskDAO;
import com.example.dietpro.pojo.DietInfo;
import com.google.gson.Gson;
import com.example.dietpro.pojo.DietResponseWrapper;
import com.example.dietpro.pojo.SymptomsWrapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class DietRecommendationBusinessService
{
    private final Executor executor = Executors.newSingleThreadExecutor();



    //Call this method from activity class for the API response.
    public void getDietRecommendations(DietRecommendationCallback callback, SymptomsWrapper symptomsWrapper, Context applicationContext)//Other Params need to be added accordingly
    {

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {

                    //Serialize Input into the SymptomsWrapper class before calling getResponse, which is the structure Flask API would expect
                    getResponse(symptomsWrapper,applicationContext,callback);
                    System.out.print("Response received");
                    System.out.print("HERE HERE HERE");
                    //Deserialize output into pojo, ie, the DietResponseWrapper class to process on the UI.

                    //This below line of code would be added while integrating the code base
                    //return response

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
       System.out.println("API Thread started");
    }

    private void getResponse(SymptomsWrapper symptomsWrapper, Context context, DietRecommendationCallback callback) throws InterruptedException {
        System.out.println("In getResponse");
        String payload=null; // Payload variable initialization
        String apiUrl = "http://192.168.0.5:5000"; // Would be changed once the ML model has been hosted on a server. This is to enable local testing
        String apiResponse="";//Mock API response string, until integration
        DietResponseWrapper response = new DietResponseWrapper();
        //API Call method here.
        payload = new Gson().toJson(symptomsWrapper);
        System.out.println("Calling API with URL : "+apiUrl+" with payload : "+payload);
        apiResponse = new FlaskDAO().executePostRequest(payload,apiUrl); // Calls the API

        System.out.println("Response returned as : "+apiResponse);

        parseAPIResponse(response,apiResponse);

        System.out.println("Exit getResponse");
        callback.onComplete(response);
    }

    private void parseAPIResponse(DietResponseWrapper response, String apiResponse)
    {
        List<DietInfo> dietInfoList  = Arrays.asList(new Gson().fromJson(apiResponse, DietInfo[].class));
        response.setDietInfoList(dietInfoList);
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
