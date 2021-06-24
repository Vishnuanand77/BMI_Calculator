package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ResultsPage extends AppCompatActivity {

    TextView BMIScore_display, BMIResult_display;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);
        
//        Toolbar toolbar = findViewById(R.id.materialToolbar2);
//        setSupportActionBar(toolbar);


        BMIScore_display = findViewById(R.id.BMIScoreDisplay);
        BMIResult_display = findViewById(R.id.BMI_resultDisplay);
        backButton = findViewById(R.id.backbutton);

        //Getting BMI score from previous activity
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        double BMI_Score = bundle.getDouble(MainActivity.EXTRA_MESSAGE);
        int BMI_Score_int = (int) BMI_Score;

        BMIScore_display.setText(String.valueOf(BMI_Score_int));

        if (BMI_Score < 18.5) {
            BMIResult_display.setText(R.string.underwreight);
            BMIResult_display.setTextColor(getResources().getColor(R.color.red));
        }
        else if (BMI_Score >= 18.5 && BMI_Score <= 20.4) {
            BMIResult_display.setText(R.string.perfect);
            BMIResult_display.setTextColor(getResources().getColor(R.color.green));
        }
        else if (BMI_Score >= 20.5 && BMI_Score <= 24.9) {
            BMIResult_display.setText(R.string.decent);
            BMIResult_display.setTextColor(getResources().getColor(R.color.yellow));
        }
        else if (BMI_Score >= 25) {
            BMIResult_display.setText(R.string.overweight);
            BMIResult_display.setTextColor(getResources().getColor(R.color.red));
        } else {
            BMIResult_display.setText("");
            BMIScore_display.setText("");
            Toast.makeText(this, "Oops check your input!", Toast.LENGTH_SHORT).show();
        }

        backButton.setOnClickListener(v -> finish());

    }

}