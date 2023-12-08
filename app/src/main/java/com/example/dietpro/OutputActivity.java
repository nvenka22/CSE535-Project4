package com.example.dietpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dietpro.pojo.DietInfo;
import com.example.dietpro.pojo.DietResponseWrapper;

import java.util.List;

public class OutputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        Button btnHome = findViewById(R.id.btnHome);



        DietResponseWrapper aggregator = (DietResponseWrapper) getIntent().getSerializableExtra("dietResponse");

        setOutputData(aggregator);
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle profile button click, for now, navigate to ProfileActivity
                Intent intent = new Intent(OutputActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setOutputData(DietResponseWrapper aggregator)
    {
        TextView bfst_meal = findViewById(R.id.tv1);
        TextView bfst_desc = findViewById(R.id.tv2);
       // bfst_meal.setMovementMethod(new ScrollingMovementMethod());



        TextView lunch_meal = findViewById(R.id.tv3);
        TextView lunch_desc = findViewById(R.id.tv4);

        TextView dinn_meal = findViewById(R.id.tv5);
        TextView dinn_desc = findViewById(R.id.tv6);

        List<DietInfo> info = aggregator.getDietInfoList();
        bfst_meal.setText(info.get(0).getName());
        bfst_desc.setText(info.get(0).getDescription());

        lunch_meal.setText(info.get(0).getName());
        lunch_desc.setText(info.get(0).getDescription());

        dinn_meal.setText(info.get(0).getName());
        dinn_desc.setText(info.get(0).getDescription());




    }
}