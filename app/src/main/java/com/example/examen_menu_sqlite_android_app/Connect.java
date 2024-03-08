package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Connect extends AppCompatActivity {
    boolean isLogIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connect);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();
        isLogIn = intent.getBooleanExtra("isLogIn", false);
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
        Intent intent = new Intent(Connect.this, MainActivity.class);
        startActivity(intent);
    }

    public void oncalcul(MenuItem item) {

    }

    public void onAffichage(MenuItem item) {
        startActivity(new Intent(this,AffichageActivity.class));
    }

    public void updateMenu(Menu menu) {
        MenuItem login = menu.findItem(R.id.Login);
        MenuItem logout = menu.findItem(R.id.Logout);
        MenuItem SignUp = menu.findItem(R.id.singUp);
        SignUp.setVisible(!isLogIn);
        login.setVisible(!isLogIn);
        logout.setVisible(isLogIn);
    }
}