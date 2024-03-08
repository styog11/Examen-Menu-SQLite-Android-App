package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class TemperatureActivity extends AppCompatActivity {
    Spinner firstDegree;
    Spinner secondDegree;
    EditText firstInput;
    TextView secondInput;
    ArrayAdapter firstAd;
    ArrayAdapter secondAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        firstDegree = findViewById(R.id.first_degree);
        secondDegree = findViewById(R.id.second_degree);
        String[] degrees = {"Celsius", "Farenheit", "Kelvin"};
        firstAd = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, degrees);
        secondAd = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, degrees);
        firstInput = findViewById(R.id.first_input);
        secondInput = findViewById(R.id.second_input);
        firstDegree.setAdapter(firstAd);
        secondDegree.setAdapter(secondAd);
        ImageView swap = findViewById(R.id.swap);
        ImageView swapSp = findViewById(R.id.swapSp);
        swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String firstInp=firstInput.getText().toString();
                firstInput.setText(secondInput.getText());
                secondInput.setText(firstInp);
                calculeTemp();
            }
        });
        swapSp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int firstSelected=firstDegree.getSelectedItemPosition();
                firstDegree.setSelection(secondDegree.getSelectedItemPosition());
                secondDegree.setSelection(firstSelected);
                calculeTemp();
            }
        });
        firstDegree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculeTemp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        secondDegree.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                calculeTemp();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        firstInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
calculeTemp();
            }
        });
    }

    @SuppressLint("DefaultLocale")
    private void calculeTemp() {
        if (firstInput.getText().toString().equals(".")){
            firstInput.setText("0");
        }else {
            if (firstInput.getText().toString().equals("")) {
                secondInput.setText("");
            } else {
                double firstDouble = Double.valueOf(firstInput.getText().toString());
                int firstP = firstDegree.getSelectedItemPosition();
                int secondP = secondDegree.getSelectedItemPosition();
                if (firstP == secondP) {
                    secondInput.setText(firstInput.getText());
                } else {
                    if (firstP == 0) {
                        if (secondP == 1) {
                            secondInput.setText(String.format("%.2f", celsiusToFarenheit(firstDouble)));
                        } else {
                            secondInput.setText(String.format("%.2f", celsiusToKelvin(firstDouble)));
                        }
                    } else {
                        if (firstP == 1) {
                            if (secondP == 0) {
                                secondInput.setText(String.format("%.2f", farenheitToCelsius(firstDouble)));
                            } else {
                                secondInput.setText(String.format("%.2f", farenheitToKelvin(firstDouble)));
                            }
                        } else {
                            if (secondP == 0) {
                                secondInput.setText(String.format("%.2f", kelvinToCelsius(firstDouble)));
                            } else {
                                secondInput.setText(String.format("%.2f", kelvinTofarenheit(firstDouble)));

                            }
                        }
                    }
                }
            }
        }
    }

    private double celsiusToFarenheit(double input) {
        return ((9.0 / 5.0) * input) + 32;
    }

    private double celsiusToKelvin(double input) {
        return input + 273.15;
    }

    private double farenheitToCelsius(double input) {
        return (9.0 / 5.0) * (input - 32);
    }

    private double farenheitToKelvin(double input) {
        return celsiusToKelvin(farenheitToCelsius(input));
    }

    private double kelvinToCelsius(double input) {
        return input - 273.15;
    }

    private double kelvinTofarenheit(double input) {
        return celsiusToFarenheit(kelvinToCelsius(input));
    }


}