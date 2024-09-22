package com.example.tp3progavan2.negocio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.tp3progavan2.AdminSQLiteOpenHelper;
import com.example.tp3progavan2.clases.Parking;

public class NegocioParking {

    private AdminSQLiteOpenHelper dbHelper;

    public NegocioParking(Context context) {
        dbHelper = new AdminSQLiteOpenHelper(context, "administration", null, 1);
    }

    public boolean registrarParking(Parking parking) {
        if (!verificarParking(parking)) {
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put("matricula", parking.getMatricula());
            valores.put("tiempo", parking.getTiempo());
            valores.put("usuario", parking.getUsuario());
            valores.put("borrado", parking.getBorrado());

            long resultado = db.insert("parking", null, valores);
            db.close();

            return (resultado != -1); // Retorna true si el registro fue exitoso
        }
        return false; // Si ya existe, no se registra
    }

    public boolean verificarParking(Parking parking) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM parking WHERE matricula = ? AND borrado = ''", new String[]{parking.getMatricula()});

        boolean existe = cursor.moveToFirst();
        cursor.close();
        db.close();
        return existe;
    }
}
