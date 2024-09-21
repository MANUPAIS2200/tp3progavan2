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

    public boolean verificarUsuario(String email) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Usamos '?' para evitar inyecciones SQL y asegurar la consulta
        Cursor cursor = db.rawQuery("SELECT * FROM usuarios WHERE email = ?", new String[]{email});

        boolean existe = cursor.moveToFirst(); // Si hay algún resultado, el usuario ya existe
        cursor.close();
        db.close();
        return existe;
    }

    // Registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        if (!verificarUsuario(usuario.getEmail())) {
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


}
