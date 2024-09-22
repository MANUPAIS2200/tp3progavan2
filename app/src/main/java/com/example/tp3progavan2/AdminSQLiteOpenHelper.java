package com.example.tp3progavan2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("Create table IF NOT EXISTS usuarios(nombre text, email text, password text)");
        BaseDeDatos.execSQL("Create table IF NOT EXISTS parking(matricula text, tiempo text, usuario text, borrado text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Create table IF NOT EXISTS usuarios(nombre text, email text, password text)");
        db.execSQL("Create table IF NOT EXISTS parking(matricula text, tiempo text, usuario text, borrado text)");
    }
}
