package com.example.dietpro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

public class MoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        // Mood Buttons Click Listeners
        ImageButton btnHappy = findViewById(R.id.btnHappy);
        ImageButton btnSad = findViewById(R.id.btnSad);
        ImageButton btnExcited = findViewById(R.id.btnExcited);
        ImageButton btnStress = findViewById(R.id.btnStress);
        ImageButton btnAngry = findViewById(R.id.btnAngry);

        btnHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Happy Mood Selected");
            }
        });

        btnSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Sad Mood Selected");
            }
        });

        btnExcited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Excited Mood Selected");
            }
        });

        btnStress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Stress Mood Selected");
            }
        });

        btnAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Angry Mood Selected");
            }
        });

        Button SaveButton2 = findViewById(R.id.btnSave2);
        SaveButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoodActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}