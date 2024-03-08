package com.example.examen_menu_sqlite_android_app;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "db";
    static final String TABLE_NAME = "client";
    static final int VERSION = 1;


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableQuery = "Create Table " + TABLE_NAME + "(id integer primary key autoincrement," +
                "nom text ," +
                "email text unique," +
                "mot_de_passe text );";
        sqLiteDatabase.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long ajouterClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", client.nom);
        cv.put("email", client.email);
        cv.put("mot_de_passe", client.password);
        long resultat= db.insert(TABLE_NAME, null, cv);
        db.close();
        return resultat;
    }

    @SuppressLint("Range")
    public List<Client> getAllClients() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<Client> clients = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                clients.add(new Client(cursor.getInt(cursor.getColumnIndex("id")),cursor.getString(cursor.getColumnIndex("nom")), cursor.getString(cursor.getColumnIndex("email")), cursor.getString(cursor.getColumnIndex("mot_de_passe"))));
            } while (cursor.moveToNext());
        }
        return clients;
    }

    public int modifierClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom", client.nom);
        cv.put("email", client.email);
        cv.put("mot_de_passe", client.password);
        int resultat= db.update(TABLE_NAME,  cv,"id=?", new String[]{String.valueOf(client.id)});
        db.close();
        return resultat;
    }

    public int supprimerClient(Client client) {
        SQLiteDatabase db = this.getWritableDatabase();
        int resultat= db.delete(TABLE_NAME, "id=?", new String[]{String.valueOf(client.id)});
        db.close();
        return resultat;
    }


    public boolean chercherClient(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id"};
        String selection = "email = ? AND mot_de_passe = ?";
        String[] selectionArgs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }


}
