package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView calculatorBoard;
    private int op_count = 0, op_index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        GridLayout pads = findViewById(R.id.pads);
        calculatorBoard = findViewById(R.id.resultat);
        for (int i = 0; i < pads.getChildCount(); i++) {
            pads.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        switch (button.getText().toString()) {
            case "=":
                try {
                    evaluate();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                break;
            case "AC":
                if (calculatorBoard != null) {
                    calculatorBoard.setText("");
                    op_count = 0;
                    op_index = 0;
                }
                break;
            default:
                try {
                    expressionOp(view);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
        }
    }

    public void expressionOp(View view) throws Exception {
        String txt = ((Button) view).getText().toString();
        String currentText = calculatorBoard.getText().toString();
        if (txt.equals("=")) {
            evaluate();
        } else if (txt.equals("AC")) {
            calculatorBoard.setText("");
        } else if (currentText.isEmpty() && isOperator(txt.charAt(0))) {
            Toast.makeText(this, "UU SHOUD Enter  a number first :)!!", Toast.LENGTH_LONG).show();
        } else {
            char lastChar = currentText.isEmpty() ? ' ' : currentText.charAt(currentText.length() - 1);
            if (isOperator(lastChar) && isOperator(txt.charAt(0))) {
            } else {
                calculatorBoard.append(txt);
            }
        }
    }

    private void evaluate() throws Exception {
        String expression = calculatorBoard.getText().toString();
        if (expression.isEmpty()) {
            Toast.makeText(this, "UU SHOUD Enter  a number first :)!!", Toast.LENGTH_LONG).show();
            return;
        }
        try {
            double result = calculate(expression);
            calculatorBoard.setText(String.valueOf(result));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid form", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }


    private double calculate(String exp) throws Exception {
        List<Double> numbers = new ArrayList<>();
        List<Character> op = new ArrayList<>();
        for (int i = 0; i < exp.length(); i++) {
            char c = exp.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder num = new StringBuilder();
                while (i < exp.length() && (Character.isDigit(exp.charAt(i)) || exp.charAt(i) == '.')) {
                    num.append(exp.charAt(i++));
                }
                i--;
                numbers.add(Double.parseDouble(num.toString()));
            } else if (c == '(') {
                op.add(c);
            } else if (c == ')') {
                while (!op.isEmpty() && op.get(op.size() - 1) != '(') {
                    expcalcul(numbers, op.remove(op.size() - 1));
                }
                if (op.isEmpty() || op.remove(op.size() - 1) != '(') {
                    throw new Exception("Mismatched parentheses");
                }
            } else if (isOperator(c)) {
                while (!op.isEmpty() && precedent(c, op.get(op.size() - 1))) {
                    expcalcul(numbers, op.remove(op.size() - 1));
                }
                op.add(c);
            }
        }
        while (!op.isEmpty()) {
            expcalcul(numbers, op.remove(op.size() - 1));
        }
        if (numbers.size() != 1) {
            throw new Exception("Invalid expression");
        }
        return numbers.get(0);
    }

    private void expcalcul(List<Double> numbers, char operator) throws Exception {
        if (numbers.size() < 2) {
            throw new Exception("Invalid expression");
        }
        double b = numbers.remove(numbers.size() - 1);
        double a = numbers.remove(numbers.size() - 1);
        switch (operator) {
            case '+':
                numbers.add(a + b);
                break;
            case '-':
                numbers.add(a - b);
                break;
            case '*':
                numbers.add(a * b);
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                numbers.add(a / b);
                break;
        }
    }

    private boolean precedent(char op1, char op2) {
        if (op2 == '(' || op2 == ')') {
            return false;
        }
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return false;
        }
        return true;
    }





    private boolean isOperator ( char c){
            return c == '+' || c == '-' || c == '*' || c == '/';
        }





    }