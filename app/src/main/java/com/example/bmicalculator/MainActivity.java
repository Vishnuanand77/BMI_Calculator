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

    //BMI Display TextView
    TextView BMI_Display;

    //Weight Card Elements
    TextView WeightCard_Display;
    Slider WeightSlider;

    //Height in foot card
    TextView HeightInFoot_Display;
    Button Increment_Foot, Decrement_Foot;

    //Height in Inch card
    TextView HeightInInch_Display;
    Button Increment_Inch, Decrement_Inch;

    //Calculate button
    Button CalculateBMIButton;

    //Variables
    private double UserWeight = 0.0;
    private int userHeight_foot = 0;
    private int userHeight_inch = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //XML resource initializations
        BMI_Display = findViewById(R.id.BMI_Output);
        CalculateBMIButton = findViewById(R.id.CalculateButton);

        WeightCard_Display = findViewById(R.id.WeightDisplay);
        WeightSlider = findViewById(R.id.WeightSlider);

        HeightInFoot_Display = findViewById(R.id.height_Foot_Display);
        Increment_Foot = findViewById(R.id.incrementWeightButton_Foot);
        Decrement_Foot = findViewById(R.id.decrementWeightButton_Foot);

        HeightInInch_Display = findViewById(R.id.height_Inch_Display);
        Increment_Inch = findViewById(R.id.incrementWeightButton_Inch);
        Decrement_Inch = findViewById(R.id.decrementWeightButton_Inch);

        //Weight card - Slider
        WeightSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {

            @Override
            public void onStartTrackingTouch(@NonNull @NotNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull @NotNull Slider slider) {
                UserWeight = Float.parseFloat(String.valueOf(slider.getValue()));
                WeightCard_Display.setText(String.valueOf(UserWeight));
                Log.d("BMI Calculator : Slider Value", String.valueOf(UserWeight));
            }

        });

        //Height_foot_card
        Increment_Foot.setOnClickListener(v -> {
            IncrementValue(HeightInFoot_Display);
        });
        Decrement_Foot.setOnClickListener(v -> {
            DecrementValue(HeightInFoot_Display);
        });

        //Height_inch_card
        Increment_Inch.setOnClickListener(v -> {
            IncrementValue(HeightInInch_Display);
        });
        Decrement_Inch.setOnClickListener(v -> {
            DecrementValue(HeightInInch_Display);
        });


        //Calculate button
        CalculateBMIButton.setOnClickListener(v -> {
            //Converting User Input into doubles
            double HeightInFeet = 0.3048; //Converting Foot to meters
            double HeightInInch = 0.0254; //Converting Inches to meters

            Log.d("BMI Calculator :", String.valueOf(HeightInFeet));
            Log.d("BMI Calculator :", String.valueOf(HeightInInch));

            //Calculating BMI
            calculateBMI(UserWeight, HeightInFeet, HeightInInch);
        });
    }

    private void DecrementValue(TextView DisplayText) {
        userHeight_foot = Integer.parseInt(DisplayText.getText().toString());
        if(!(userHeight_foot <= 0) ) {
            userHeight_foot -= 1;
            DisplayText.setText(String.valueOf(userHeight_foot));
        }
        if (userHeight_foot <= 0){
            userHeight_foot = 0;
            DisplayText.setText(String.valueOf(userHeight_foot));
        }
    }

    private void IncrementValue(TextView DisplayText) {
        userHeight_foot = Integer.parseInt(DisplayText.getText().toString());
        userHeight_foot +=1;
        DisplayText.setText(String.valueOf(userHeight_foot));
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