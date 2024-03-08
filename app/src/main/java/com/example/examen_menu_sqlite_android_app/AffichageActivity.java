package com.example.examen_menu_sqlite_android_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AffichageActivity extends AppCompatActivity {
    ArrayList<Integer> allClientsIds= new ArrayList() ;
    DataBaseHelper db;
    ListView theList;
    List<Client> clients;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_affichage);
            db = new DataBaseHelper(this);
            theList = findViewById(R.id.the_list);
            clients=db.getAllClients();
            for (Client client : clients){
                    allClientsIds.add(client.id);

            }
            ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,clients);
            Button btnAjouter = findViewById(R.id.btn_ajouter);
            Button btnModifier = findViewById(R.id.btn_modifier);
            Button btnSupprimer = findViewById(R.id.btn_supprimer);
            btnAjouter.setOnClickListener(view -> {
                    startActivity(new Intent(AffichageActivity.this,SignUp.class));
            });
            btnSupprimer.setOnClickListener(view -> {
                int id=checkAndGetClientId();
                if (id!=-1) {
                    Client clientToDelete = new Client(id, "", "", "");
                    db.supprimerClient(clientToDelete);
                    updateList();
                }
            });
            btnModifier.setOnClickListener(view -> {
                int id=checkAndGetClientId();
                if (id!=-1) {
                    Client client = getClientById(id);
                    Intent intent = new Intent(AffichageActivity.this, SignUp.class);
                    intent.putExtra("id", id);
                    intent.putExtra("nom", client.nom);
                    intent.putExtra("email", client.email);
                    intent.putExtra("password", client.password);
                    startActivity(intent);
                }

            });
            theList.setAdapter(ad);
    }

    private void updateList() {
        clients=db.getAllClients();
        allClientsIds.clear();
        for (Client client : clients){
            allClientsIds.add(client.id);
        }
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_list_item_1,clients);
        theList.setAdapter(ad);
    }

    private int checkAndGetClientId(){
        EditText idTextField = findViewById(R.id.client_id);
        if (!idTextField.getText().toString().equals("")){
            int id=Integer.parseInt(idTextField.getText().toString());
            if (allClientsIds.contains(id)){
                return id;
            }else{
                idTextField.setError("aucun client avec cet ID : "+id);
            }
        }else{
            idTextField.setError("l'id est obligatoire pour cette action");
        }
        return -1;
    }
    private Client getClientById(int id){
        return clients.get(allClientsIds.indexOf(id));
    }
}