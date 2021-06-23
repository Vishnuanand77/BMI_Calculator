package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //XML resource declarations
    TextView BMI_Display;
    EditText UserWeight, UserFoot, UserInches;
    Button CalculateButton;
    Spinner FeetSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML resource initializations
        BMI_Display = findViewById(R.id.BMI_Output);
        UserWeight = findViewById(R.id.UserInput_Weight);
        UserFoot = findViewById(R.id.UserInput_Foot);
        UserInches = findViewById(R.id.UserInput_Inches);
        CalculateButton = findViewById(R.id.CalculateButton);
        FeetSpinner = findViewById(R.id.FootSpinner);

        CalculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Converting User Input into doubles
                double Weight = Float.parseFloat(UserWeight.getText().toString());
                double HeightInFeet = Float.parseFloat(UserFoot.getText().toString()) * 0.3048; //Converting Foot to meters
                double HeightInInch = Float.parseFloat(UserInches.getText().toString()) * 0.0254; //Converting Inches to meters

                Log.d("BMI Calculator :", String.valueOf(HeightInFeet));
                Log.d("BMI Calculator :", String.valueOf(HeightInInch));

                double totalHeight = (HeightInFeet + HeightInInch);

                double squaredHeight = Math.pow(totalHeight, 2); //Squaring the height to get meter squared
                Log.d("BMI Calculator :", String.valueOf(squaredHeight));

                double BMI = Weight/squaredHeight;
                Log.d("BMI Calculator :", String.valueOf(BMI));

                //Assigning BMI value to TextView
                BMI_Display.setText(String.valueOf(BMI));
            }
        });
    }
}