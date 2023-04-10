package com.example.mobile_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        String history;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 20; i > (count - 20); i--) {
            history = sharedPreferences.getString("history" + String.valueOf(i - 1), null);
            stringBuilder.append(i).append(" ").append(history).append("\n\n");
        }
        tvHistory.setText(stringBuilder.toString());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}