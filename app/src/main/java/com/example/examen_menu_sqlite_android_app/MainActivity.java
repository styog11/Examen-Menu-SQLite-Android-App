package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private boolean isLogIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_client, menu);
        return true;
    }

    public void onLogin(MenuItem item) {
        startActivity(new Intent(this,Login.class));
        isLogIn = true;
        updateMenu();
    }

    public void onSingUp(MenuItem item) {
        isLogIn = true;
        updateMenu();
    }

    public void onLogOut(MenuItem item) {
        isLogIn = false;
        updateMenu();
    }

    public void updateMenu() {
        MenuItem login = findViewById(R.id.Login);
        MenuItem logout = findViewById(R.id.Logout);
        login.setVisible(!isLogIn);
        logout.setVisible(isLogIn);
    }
}


