package com.mobilecomputing.project4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username, password,Age,Height,Weight,VNV,Diabetes;
    Button RegisterButton;
    DBHelper DB;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Age = (EditText) findViewById(R.id.Age);
        Weight = (EditText) findViewById(R.id.Weight);
        Height = (EditText) findViewById(R.id.Height);
        Diabetes = (EditText) findViewById(R.id.Diabetes);
        VNV = (EditText) findViewById(R.id.VNV);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);
        DB = new DBHelper(this);

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String ageStr = Age.getText().toString();
                String Diabe = Diabetes.getText().toString();
                String VN = VNV.getText().toString();
                String WeightStr = Weight.getText().toString();
                String HeightStr = Height.getText().toString();
                int Age=Integer.parseInt(ageStr);
                int Weight=Integer.parseInt(WeightStr);
                int Height=Integer.parseInt(HeightStr);

                Boolean insert = DB.insertuserData(user,pass,Age,Height,Weight,Diabe,VN);
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
