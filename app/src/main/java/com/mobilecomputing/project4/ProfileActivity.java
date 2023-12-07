package com.mobilecomputing.project4;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    DBHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        DB = new DBHelper(this);
        TextView textView1 = findViewById(R.id.tvName);
        textView1.setText("Email : sanugu@gmail.com");
        TextView textView2 = findViewById(R.id.tvHeight);
        textView2.setText("Height : "+DB.getUserHeight("sanugu@gmail.com"));
        TextView textView3 = findViewById(R.id.tvWeight);
        textView3.setText("Weight : "+DB.getUserWeight("sanugu@gmail.com"));
        TextView textView4 = findViewById(R.id.tvDiabetes);
        textView4.setText("Diabetes : "+DB.getUserDiabetes("sanugu@gmail.com"));
        TextView textView5 = findViewById(R.id.tvVNV);
        textView5.setText("Your Preference of Diet is "+DB.getUserVNV("sanugu@gmail.com"));

    }
}
