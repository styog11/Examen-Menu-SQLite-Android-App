package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends BaseActivity {
    boolean isLogIn;
    private EditText mail;
    private TextView error;
    private EditText pass;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        isLogIn = intent.getBooleanExtra("isLogIn", false);

        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);
        error = findViewById(R.id.error);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if (email.isEmpty() || !isValidEmail(email)) {
                    error.setVisibility(View.VISIBLE);
                    error.setText("The adress mail is invalid");
                    return;
                }

                boolean clientisExistet = trouverClient(email, password);

                if (clientisExistet) {
                    error.setVisibility(View.GONE);
                    Intent intent = new Intent(Login.this, Connect.class);
                    intent.putExtra("isLogIn",true);
                    startActivity(intent);
                      } else {
                    error.setVisibility(View.VISIBLE);
                    error.setText("*Email or Password is incorrect");
                    //Toast.makeText(Login.this,"User not Founded :) ",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private boolean trouverClient(String email, String password) {
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        return dbHelper.chercherClient(email, password);
    }
    @Override
    public void updateMenu(Menu menu) {
        MenuItem login = menu.findItem(R.id.Login);
        MenuItem logout = menu.findItem(R.id.Logout);
        login.setVisible(!isLogIn);
        logout.setVisible(!isLogIn);
    }

}