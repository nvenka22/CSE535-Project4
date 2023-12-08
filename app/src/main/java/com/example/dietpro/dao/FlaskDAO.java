package com.example.dietpro.dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class FlaskDAO {

    public String executePostRequest(String payload,String url) {
        StringBuilder response = new StringBuilder();
        DataOutputStream os = null;

        try {

            System.out.println("Calling Flask API with URL :" + url);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty( "charset", "utf-8");
            connection.setRequestProperty("Content-Length", Integer.toString(payload.length()));

            byte[] payloadInputStreamBytes = payload.getBytes(StandardCharsets.UTF_8);
            os = new DataOutputStream(connection.getOutputStream());
            os.write(payloadInputStreamBytes);
            os.flush();

            if(connection.getResponseCode() == 200)
            {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
            }

            else
            {
                System.out.println("API Error");
                return "Error";
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("API Response is : "+response.toString());
        return response.toString();

    }

}
