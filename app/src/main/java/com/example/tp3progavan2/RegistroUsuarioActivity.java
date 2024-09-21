package com.example.tp3progavan2;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Button;

public class RegistroUsuarioActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_usuario);
        Button btnAceptar = findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

}

    public void RegistrarUsuario() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administration", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        EditText et_name = (EditText) findViewById(R.id.editTextNombre);
        EditText et_email = (EditText) findViewById(R.id.editTextEmail);
        EditText et_pass = (EditText) findViewById(R.id.editTextPass);
        EditText et_pass2 = (EditText) findViewById(R.id.editTextPass2);

        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        String pass2 = et_pass2.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !pass2.isEmpty()) {
            if (pass.equals(pass2)) {
                Cursor fila = BaseDeDatos.rawQuery("select * from usuarios where email = ?", new String[]{email});
                if (!fila.moveToFirst()) {
                    ContentValues registro = new ContentValues();

                    registro.put("nombre", name);
                    registro.put("email",email);
                    registro.put("password",pass);

                    long resultado = BaseDeDatos.insert("usuarios", null, registro);
                    if (resultado != -1) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();

                        // Limpiar los campos
                        et_name.setText("");
                        et_email.setText("");
                        et_pass.setText("");
                        et_pass2.setText("");
                        BaseDeDatos.close();
                        finish();
                    } else {
                        Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(this, "El mail ya está registrado", Toast.LENGTH_SHORT).show();
                }
                fila.close();

            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes llenar todos lo campos", Toast.LENGTH_SHORT).show();
        }
        BaseDeDatos.close();
    }

    public void LoginActivity(View view) {
        //Devuelve a la pantalla de LogIn (En este caso, MainActivity
        setContentView(R.layout.activity_main);
    }
}
