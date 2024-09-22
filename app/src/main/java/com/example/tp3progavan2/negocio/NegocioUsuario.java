package com.example.tp3progavan2.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tp3progavan2.AdminSQLiteOpenHelper;
import com.example.tp3progavan2.clases.Usuario;

public class NegocioUsuario {

    private AdminSQLiteOpenHelper dbHelper;

    public NegocioUsuario(Context context) {
        dbHelper = new AdminSQLiteOpenHelper(context, "administration", null, 1);
    }

    public boolean verificarUsuario(Usuario usuario) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ? AND nombre = ?", new String[]{usuario.getEmail(), usuario.getNombre()});

        boolean existe = cursor.moveToFirst();
        cursor.close();
        db.close();
        return existe;
    }

    public boolean verificarLogin(String nombre, String pass) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE nombre = ? AND password = ?", new String[]{nombre,pass});
        boolean existe = cursor.moveToFirst();
        cursor.close();
        db.close();
        return existe;
    }



    // Registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        if (!verificarUsuario(usuario)) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("nombre", usuario.getNombre());
            valores.put("email", usuario.getEmail());
            valores.put("password", usuario.getPass());

            long resultado = db.insert("usuarios", null, valores);
            db.close();

            return (resultado != -1); // Retorna true si el registro fue exitoso
        }
        return false; // Si ya existe, no se registra
    }


    public String obtengoMail(String nombre, String pass) {

        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT email FROM usuarios WHERE nombre = ? AND password = ?", new String[]{nombre,pass});
        String email = null;
        if (cursor.moveToFirst()) {
            email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        }
        cursor.close();
        db.close();
        return email;

    }
}
