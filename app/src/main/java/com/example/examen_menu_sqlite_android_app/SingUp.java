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
import android.widget.Toast;

public class SingUp extends AppCompatActivity {
    boolean isLogIn;
    private EditText nam;
    private EditText mail;
    private EditText pass;
    private Button registerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        isLogIn = intent.getBooleanExtra("isLogIn", false);

        mail = findViewById(R.id.mail);
        nam = findViewById(R.id.name);
        pass = findViewById(R.id.password);
        registerBtn = findViewById(R.id.inscriptBtn);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mail.getText().toString().trim();
                String name = nam.getText().toString().trim();
                String password = pass.getText().toString().trim();
                long Id = register(name,email,password);
                if (Id != -1) {
                    Toast.makeText(SingUp.this,"DONE!!",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SingUp.this, Connect.class);
                    intent.putExtra("isLogIn",true);
                    startActivity(intent);
                } else {
                    Toast.makeText(SingUp.this,"Error!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    private long register(String name, String email, String password) {
        DataBaseHelper dbHelper = new DataBaseHelper(this);
        Client client = new Client(name,email,password);
        return dbHelper.ajouterClient(client);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_client, menu);
        updateMenu(menu);
        return true;
    }

    public void onLogin(MenuItem item) {
        Intent intent = new Intent(SingUp.this, Login.class);
        intent.putExtra("isLogIn",true);
        startActivity(intent);
    }

    public void onSingUp(MenuItem item) {

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
        MenuItem singup = menu.findItem(R.id.singUp);
        login.setVisible(isLogIn);
        singup.setVisible(!isLogIn);
        logout.setVisible(!isLogIn);
    }
}