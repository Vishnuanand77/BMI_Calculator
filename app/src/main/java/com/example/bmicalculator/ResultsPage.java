package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsPage extends AppCompatActivity {

    TextView BMIScore_display, BMIResult_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        BMIScore_display = findViewById(R.id.BMIScoreDisplay);
        BMIResult_display = findViewById(R.id.BMI_resultDisplay);

        //Getting BMI score from previous activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int BMI_Score = (int) bundle.getDouble(MainActivity.EXTRA_MESSAGE);

        BMIScore_display.setText(String.valueOf(BMI_Score));

        if (BMI_Score >= 18.5 && BMI_Score <= 20.0) {
            BMIResult_display.setText("Perfect");
            BMIResult_display.setTextColor(getResources().getColor(R.color.green));
        }
        if (BMI_Score >= 20.1 && BMI_Score <= 24.9) {
            BMIResult_display.setText("Decent");
            BMIResult_display.setTextColor(getResources().getColor(R.color.yellow));
        }
        if (BMI_Score >= 25) {
            BMIResult_display.setText("Overweight");
            BMIResult_display.setTextColor(getResources().getColor(R.color.red));
        }

    }
}