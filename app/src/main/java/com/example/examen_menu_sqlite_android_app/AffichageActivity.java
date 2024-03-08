package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AffichageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_affichage);
            DataBaseHelper db = new DataBaseHelper(this);
            ListView theList = findViewById(R.id.the_list);
            ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1, db.getAllClients());
            theList.setAdapter(ad);

    }
}