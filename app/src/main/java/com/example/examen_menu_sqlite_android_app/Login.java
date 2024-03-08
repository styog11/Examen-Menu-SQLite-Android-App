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

public class Login extends AppCompatActivity {
    boolean isLogIn=true;
    private EditText mail;
    private EditText pass;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.password);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mail.getText().toString().trim();
                String password = pass.getText().toString().trim();
                Intent intent = new Intent(Login.this, Connect.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_client, menu);
        updateMenu(menu);
        return true;
    }

    public void onLogin(MenuItem item) {
    }

    public void onSingUp(MenuItem item) {
        isLogIn = true;
    }

    public void onLogOut(MenuItem item) {

    }
    public void oncalcul(MenuItem item) {

    }
    public void onAffichage(MenuItem item) {

    }

    public void updateMenu(Menu menu) {
        MenuItem login = menu.findItem(R.id.Login);
        MenuItem logout = menu.findItem(R.id.Logout);
        login.setVisible(!isLogIn);
        logout.setVisible(false);
    }

}