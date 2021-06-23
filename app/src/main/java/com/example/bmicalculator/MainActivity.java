package com.example.bmicalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import org.jetbrains.annotations.NotNull;

import static java.lang.Double.MAX_EXPONENT;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "BMI_Score";

    //XML resource declarations

    //BMI Display TextView
//    TextView BMI_Display;

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
    private double UserWeight = 0;
    private int userHeight_foot = 0;
    private int userHeight_inch = 0;
    private double BMI_Result = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //XML resource initializations
//        BMI_Display = findViewById(R.id.BMI_Output);
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
            IncrementValue(1);
        });
        Decrement_Foot.setOnClickListener(v -> {
            DecrementValue(1);
        });

        //Height_inch_card
        Increment_Inch.setOnClickListener(v -> {
            IncrementValue(2);
        });
        Decrement_Inch.setOnClickListener(v -> {
            DecrementValue(2);
        });

        //Calculate button
        CalculateBMIButton.setOnClickListener(v -> {

//            Toast.makeText(this, "Foot : " + userHeight_foot + " Inch : " + userHeight_inch, Toast.LENGTH_SHORT).show();

            //Getting values
            UserWeight = Float.parseFloat(WeightCard_Display.getText().toString());
            userHeight_foot = Integer.parseInt(HeightInFoot_Display.getText().toString());
            userHeight_inch = Integer.parseInt(HeightInInch_Display.getText().toString());

            //Converting User Input into doubles
            double HeightInFeet = userHeight_foot * 0.3048; //Converting Foot to meters
            double HeightInInch = userHeight_inch * 0.0254; //Converting Inches to meters

            Log.d("BMI Calculator :", String.valueOf(HeightInFeet));
            Log.d("BMI Calculator :", String.valueOf(HeightInInch));

            //Calculating BMI
            calculateBMI(UserWeight, HeightInFeet, HeightInInch);

            //Start a new Activity
            Intent intent = new Intent(this, ResultsPage.class);
            intent.putExtra(EXTRA_MESSAGE, BMI_Result);
            startActivity(intent);
        });
    }

    private void DecrementValue(int switcher) {

        switch (switcher) {
            case 1:
                userHeight_foot = Integer.parseInt(HeightInFoot_Display.getText().toString());
                if(!(userHeight_foot <= 0) ) {
                    userHeight_foot -= 1;
                    HeightInFoot_Display.setText(String.valueOf(userHeight_foot));
                }
                if (userHeight_foot <= 0){
                    userHeight_foot = 0;
                    HeightInFoot_Display.setText(String.valueOf(userHeight_foot));
                }
                break;
            case 2:
                userHeight_inch = Integer.parseInt(HeightInInch_Display.getText().toString());
                if(!(userHeight_inch <= 0) ) {
                    userHeight_inch -= 1;
                    HeightInInch_Display.setText(String.valueOf(userHeight_inch));
                }
                if (userHeight_inch <= 0){
                    userHeight_inch = 0;
                    HeightInInch_Display.setText(String.valueOf(userHeight_inch));
                }
                break;
            default:
                userHeight_inch = 0;
                userHeight_foot = 0;
                HeightInFoot_Display.setText(userHeight_foot);
                HeightInInch_Display.setText(userHeight_inch);
                break;
        }

    }

    private void IncrementValue(int switcher) {
        switch (switcher) {
            case 1:
                userHeight_foot = Integer.parseInt(HeightInFoot_Display.getText().toString());
                userHeight_foot +=1;
                HeightInFoot_Display.setText(String.valueOf(userHeight_foot));
                break;
            case 2:
                userHeight_inch = Integer.parseInt(HeightInInch_Display.getText().toString());
                userHeight_inch +=1;
                HeightInInch_Display.setText(String.valueOf(userHeight_inch));
                break;
            default:
                userHeight_inch = 0;
                userHeight_foot = 0;
                HeightInFoot_Display.setText(userHeight_foot);
                HeightInInch_Display.setText(userHeight_inch);
                break;
        }

    }

    private void calculateBMI(double Weight, double HeightInFeet, double HeightInInch) {
        //Calculating BMI
        double BMI = (int) Weight/Math.pow(HeightInFeet + HeightInInch, 2);
        //Assigning BMI value to TextView
//        BMI_Display.setText(String.valueOf(BMI));
        BMI_Result = BMI;
        //Logs
        Log.d("BMI Calculator :", String.valueOf(BMI));
    }
}