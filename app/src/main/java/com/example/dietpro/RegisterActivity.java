package com.example.dietpro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password,Height,Weight;

    CheckBox diabetes,isveg;
    Button RegisterButton;
    DBHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.editTextEmail);
        password = (EditText) findViewById(R.id.editTextPassword);
        Weight = (EditText) findViewById(R.id.editTextWeight);
        Height = (EditText) findViewById(R.id.editTextHeight);
        diabetes = findViewById(R.id.checkBoxDiabetes);
        isveg = findViewById(R.id.checkBoxVeg);


        RegisterButton = (Button) findViewById(R.id.buttonSignUp);
        DB = new DBHelper(this);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                String WeightStr = Weight.getText().toString();
                String HeightStr = Height.getText().toString();

                int Weight=Integer.parseInt(WeightStr);
                int Height=Integer.parseInt(HeightStr);
                String diabete="";
                String isvege="";

                if (diabetes.isChecked()) {
                    diabete= "Yes";

                }
                else {
                    diabete="No";
                }
                if (isveg.isChecked()) {
                    isvege= "Vegetarian";

                }
                else {
                    isvege="Non-Vegetarian";
                }

                Boolean insert = DB.insertuserData(user,pass,Height,Weight,diabete,isvege);
                if(insert==true) {
                    Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Enter Fields Properly", Toast.LENGTH_SHORT).show();
                }
            }








            });


    }

}
