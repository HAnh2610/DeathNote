package com.example.mobile_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HistoryActivity extends AppCompatActivity {

    private TextView tvHistory;
    private SharedPreferences sharedPreferences;
    private int count;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        btnBack = findViewById(R.id.btnBack);
        tvHistory = findViewById(R.id.tvHistory);
        sharedPreferences = getSharedPreferences("CalculationHistory", MODE_PRIVATE);

        if (getIntent().getExtras() != null) {
            count = getIntent().getExtras().getInt("count");
        }

        ArrayList<MainActivity.Calculation> history = new ArrayList<>();
        for (int i = 20; i > (count - 20); i--) {
            String historyString = sharedPreferences.getString("history" + String.valueOf(i - 1), null);
            MainActivity.Calculation calculation = MainActivity.Calculation.fromString(historyString);
            if (calculation != null) {
                history.add(calculation);
            }
        }

        String historyText = "";
        for (MainActivity.Calculation calculation : history) {
            historyText += calculation.toString() + "\n\n";
        }
        tvHistory.setText(historyText);

        // Store history object in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("history_object", MainActivity.Calculation.toSerializedString(history));
        editor.apply();

        // Show toast message to indicate successful storage
        Toasty.success(this, "Calculation history stored successfully", Toasty.LENGTH_SHORT).show();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
