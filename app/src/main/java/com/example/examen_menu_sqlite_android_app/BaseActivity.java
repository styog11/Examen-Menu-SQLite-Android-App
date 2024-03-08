package com.example.examen_menu_sqlite_android_app;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
boolean isLogIn=false;
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
        Intent intent = new Intent(this, MainActivity.class);
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
        login.setVisible(!isLogIn);
        logout.setVisible(false);
    }
}