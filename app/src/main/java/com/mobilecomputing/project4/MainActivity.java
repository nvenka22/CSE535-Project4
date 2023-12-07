package com.mobilecomputing.project4;
import com.mobilecomputing.project4.pojo.Symptoms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.ArrayList;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private EditText SymptomName;

    private EditText SymptomSeverity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button LoginButton = (Button)findViewById(R.id.loginButton);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int2 = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(int2);

            }
            });





//
//        Button buttonGetDiet = (Button)findViewById(R.id.buttonGetDiet);
//
//        buttonGetDiet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                DietRecommendationBusinessService bs = new DietRecommendationBusinessService();
//                //The callback here is implemented so that the API call does not interfere with the main thread.
//                //THe callback allows the main thread and the background thread to communicate.
//                bs.getDietRecommendations(new DietRecommendationCallback() {
//                    @Override
//                    public void onComplete(DietResponseWrapper dietResponseWrapper) {
//                        //UI display code would be here.
//                        System.out.println(dietResponseWrapper.getRes());
//                    }
//                });
//            }
//        });
//    }
//
//    public void OnSubmitSymptom(){
//        SymptomName=findViewById(R.id.editTextSymptomName);
//        SymptomSeverity=findViewById(R.id.editTextSymptomSeverity);
//
//        List<Symptoms> symlist= new ArrayList<>();
//        String symName=SymptomName.getText().toString();
//        String symSeverity=SymptomSeverity.getText().toString();
//        Symptoms Sym=new Symptoms();
//        Sym.setSeverity(symSeverity);
//        Sym.setSymptom(symName);
//        symlist.add(Sym);
//
//    }


}