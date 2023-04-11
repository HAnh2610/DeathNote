package com.example.mobile_app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

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
            // Retrieve history object from SharedPreferences
            SharedPreferences prefs = getSharedPreferences("CalculationHistory", MODE_PRIVATE);
            Gson gson = new Gson();
            String json = prefs.getString("history_object", "");
            Type type = new TypeToken<ArrayList<MainActivity.Calculation>>() {
            }.getType();
            ArrayList<MainActivity.Calculation> history_output = gson.fromJson(json, type);
            tvHistory.setText((CharSequence) history);
            // Show toast message to indicate successful storage


            btnBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }
}