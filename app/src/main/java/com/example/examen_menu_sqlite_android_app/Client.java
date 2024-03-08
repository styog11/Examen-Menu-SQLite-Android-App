package com.example.examen_menu_sqlite_android_app;

public class Client {
    static int count=0;
    int id;
    String nom;
    String email;
    String password ;
     public Client(String nom,String email , String password){

         this.nom=nom;
         this.email=email;
         this.password=password;
         this.id = ++count;
     }

    @Override
    public String toString() {
        return "id: "+id +"nom: "+nom +"email: "+email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
