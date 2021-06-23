package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //XML resource declarations
    TextView BMI_Display;
    EditText UserWeight, UserFoot, UserInches;
    Button CalculateButton;

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


    }
}