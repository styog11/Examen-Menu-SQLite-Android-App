package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_client, menu);
        updateMenu(menu);
        return true;
    }

    public void onLogin(MenuItem item) {
        startActivity(new Intent(this,Login.class));
        isLogIn = true;
    }

    public void onSingUp(MenuItem item) {
        isLogIn = true;
    }

    public void onLogOut(MenuItem item) {
        isLogIn = false;
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


