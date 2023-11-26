package com.mobilecomputing.project4;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.mobilecomputing.project4.bs.DietRecommendationBusinessService;
import com.mobilecomputing.project4.pojo.DietRecommendationCallback;
import com.mobilecomputing.project4.databinding.ActivityMainBinding;
import com.mobilecomputing.project4.pojo.DietResponseWrapper;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //button for recording video for measuring heart rate
        Button btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DietRecommendationBusinessService bs = new DietRecommendationBusinessService();
                //The callback here is implemented so that the API call does not interfere with the main thread.
                //THe callback allows the main thread and the background thread to communicate.
                bs.getDietRecommendations(new DietRecommendationCallback() {
                    @Override
                    public void onComplete(DietResponseWrapper dietResponseWrapper) {
                        //UI display code would be here.
                        System.out.println(dietResponseWrapper.getRes());
                    }
                });
            }
        });
    }


}