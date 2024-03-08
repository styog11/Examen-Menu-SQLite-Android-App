package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TemperatureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Spinner firstDegree = findViewById(R.id.first_degree);
        Spinner secondDegree = findViewById(R.id.second_degree);
        EditText firstInput = findViewById(R.id.first_input);
        EditText secondInput = findViewById(R.id.second_input);
        Button swap = findViewById(R.id.swap);
    }
}