package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AffichageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_affichage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DataBaseHelper db = new DataBaseHelper(this);
        ListView theList = findViewById(R.id.the_list);
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, db.getAllClients());
        theList.setAdapter(ad);

    }
    @Override
    public void updateMenu(Menu menu) {
        android.view.MenuItem login = menu.findItem(R.id.Login);
        android.view.MenuItem logout = menu.findItem(R.id.Logout);
        MenuItem SignUp = menu.findItem(R.id.singUp);
        SignUp.setVisible(!isLogIn);
        login.setVisible(!isLogIn);
        logout.setVisible(isLogIn);
    }
}