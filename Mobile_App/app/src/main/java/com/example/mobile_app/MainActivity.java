package com.example.mobile_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvOutput, tv_output2, tvHistory;
    private StringBuilder stringBuilder;
    private boolean isDecimalPressed;
    private double num1;
    private double num2;
    private double result;
    private String operator;
    private SharedPreferences sharedPreferences;
    private int count;
    private String history;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("CalculationHistory", MODE_PRIVATE);

        stringBuilder = new StringBuilder();
        isDecimalPressed = false;
        result = 0;
        count = 0;
        history = "";

        tvOutput = findViewById(R.id.tv_output);
        tv_output2 = findViewById(R.id.tv_output2);
        tvHistory = findViewById(R.id.tvHistory);

        findViewById(R.id.btn_output2).setOnClickListener(this);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnDot).setOnClickListener(this);
        findViewById(R.id.btnPlus).setOnClickListener(this);
        findViewById(R.id.btnMinus).setOnClickListener(this);
        findViewById(R.id.btnDivide).setOnClickListener(this);
        findViewById(R.id.btnMultiply).setOnClickListener(this);
        findViewById(R.id.btnClear).setOnClickListener(this);
        findViewById(R.id.btnEquals).setOnClickListener(this);
        findViewById(R.id.btnHistory).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn0:
                stringBuilder.append("0");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn1:
                stringBuilder.append("1");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn2:
                stringBuilder.append("2");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn3:
                stringBuilder.append("3");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn4:
                stringBuilder.append("4");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn5:
                stringBuilder.append("5");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn6:
                stringBuilder.append("6");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn7:
                stringBuilder.append("7");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn8:
                stringBuilder.append("8");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btn9:
                stringBuilder.append("9");
                tvOutput.setText(stringBuilder.toString());
                break;
            case R.id.btnDot:
                if (!isDecimalPressed) {
                    stringBuilder.append(".");
                    tvOutput.setText(stringBuilder.toString());
                    isDecimalPressed = true;
                }
                break;
            case R.id.btnPlus:
                if (!stringBuilder.toString().isEmpty()) {
                    num1 = Double.parseDouble(stringBuilder.toString());
                    operator = "+";
                    stringBuilder = new StringBuilder();
                    tvOutput.setText(stringBuilder.toString());
                    isDecimalPressed = false;
                }
                break;
            case R.id.btnMinus:
                if (!stringBuilder.toString().isEmpty()) {
                    num1 = Double.parseDouble(stringBuilder.toString());
                    operator = "-";
                    stringBuilder = new StringBuilder();
                    tvOutput.setText(stringBuilder.toString());
                    isDecimalPressed = false;
                }
                break;
            case R.id.btnDivide:
                if (!stringBuilder.toString().isEmpty()) {
                    num1 = Double.parseDouble(stringBuilder.toString());
                    operator = "/";
                    stringBuilder = new StringBuilder();
                    tvOutput.setText(stringBuilder.toString());
                    isDecimalPressed = false;
                }
                break;
            case R.id.btnMultiply:
                if (!stringBuilder.toString().isEmpty()) {
                    num1 = Double.parseDouble(stringBuilder.toString());
                    operator = "*";
                    stringBuilder = new StringBuilder();
                    tvOutput.setText(stringBuilder.toString());
                    isDecimalPressed = false;
                }
                break;
            case R.id.btnClear:
                stringBuilder = new StringBuilder();
                tvOutput.setText(stringBuilder.toString());
                isDecimalPressed = false;
                break;
            case R.id.btnEquals:
                if (!stringBuilder.toString().isEmpty()) {
                    num2 = Double.parseDouble(stringBuilder.toString());
                    if (operator.equals("+")) {
                        result = num1 + num2;
                    } else if (operator.equals("-")) {
                        result = num1 - num2;
                    } else if (operator.equals("*")) {
                        result = num1 * num2;
                    } else if (operator.equals("/")) {
                        result = num1 / num2;
                    }
                    tvOutput.setText(String.valueOf(result));
                    isDecimalPressed = false;
                    count++;
                    stringBuilder = new StringBuilder();
                    history = sharedPreferences.getString("history" + count, "") + num1 + " "+ operator + " " + num2 + " = " + result + "\n";
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("history" + String.valueOf(count), history);
                    editor.apply();
                }
                break;
            case R.id.btn_output2:
                tv_output2.setText(String.valueOf(count));
                break;
            case R.id.btnHistory:
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
                break;
        }
    }
}