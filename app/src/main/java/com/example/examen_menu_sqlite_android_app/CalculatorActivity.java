package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
TextView calculatorBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        GridLayout pads = findViewById(R.id.pads);
        calculatorBoard=findViewById(R.id.resultat);
        for (int i=0 ; i<pads.getChildCount();i++){
            pads.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button button=(Button) view;
        switch (button.getText().toString()){
            case "=":
                // evaluer
                // ...
                break;
            case "AC":
                calculatorBoard.setText("0");
                break;
            default:
                calculatorBoard.setText(calculatorBoard.getText()+button.getText().toString());
        }
    }
}