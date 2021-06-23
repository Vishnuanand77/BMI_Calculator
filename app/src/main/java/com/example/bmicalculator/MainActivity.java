package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    //XML resource declarations
    TextView BMI_Display, WeightDisplay;
    Button CalculateButton;

    //Slider
    Slider WeightSlider;
    double UserWeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML resource initializations
        BMI_Display = findViewById(R.id.BMI_Output);
        WeightDisplay = findViewById(R.id.WeightDisplay);
        CalculateButton = findViewById(R.id.CalculateButton);

        //Slider
        WeightSlider = findViewById(R.id.WeightSlider);

        WeightSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {

            @Override
            public void onStartTrackingTouch(@NonNull @NotNull Slider slider) {

            }

            @Override
            public void onStopTrackingTouch(@NonNull @NotNull Slider slider) {
                UserWeight = Float.parseFloat(String.valueOf(slider.getValue()));
                WeightDisplay.setText(String.valueOf(UserWeight));
                Log.d("BMI Calculator : Slider Value", String.valueOf(UserWeight));
            }

        });


        CalculateButton.setOnClickListener(v -> {
            //Converting User Input into doubles
            double HeightInFeet = 0.3048; //Converting Foot to meters
            double HeightInInch = 0.0254; //Converting Inches to meters

            Log.d("BMI Calculator :", String.valueOf(HeightInFeet));
            Log.d("BMI Calculator :", String.valueOf(HeightInInch));

            //Calculating BMI
            calculateBMI(UserWeight, HeightInFeet, HeightInInch);
        });
    }

    private void calculateBMI(double Weight, double HeightInFeet, double HeightInInch) {
        //Calculating BMI
        double BMI = Weight/Math.pow(HeightInFeet + HeightInInch, 2);

        //Assigning BMI value to TextView
        BMI_Display.setText(String.valueOf(BMI));

        //Logs
        Log.d("BMI Calculator :", String.valueOf(BMI));
    }
}