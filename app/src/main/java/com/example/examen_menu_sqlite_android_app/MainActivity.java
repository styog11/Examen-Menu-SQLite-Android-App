package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
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
        Intent intent = new Intent(this,Login.class);
        intent.putExtra("isLogIn",true);
        startActivity(intent);
    }

    public void onSingUp(MenuItem item) {
        Intent intent = new Intent(this, SignUp.class);
        intent.putExtra("isLogIn",true);
        startActivity(intent);

    }

    public void onLogOut(MenuItem item) {
    }
    public void oncalcul(MenuItem item) {

    }
    public void onAffichage(MenuItem item) {
        startActivity(new Intent(this,AffichageActivity.class));

    }

    public void updateMenu(Menu menu) {
        MenuItem login = menu.findItem(R.id.Login);
        MenuItem logout = menu.findItem(R.id.Logout);
        login.setVisible(!isLogIn);
        logout.setVisible(false);
    }
}


