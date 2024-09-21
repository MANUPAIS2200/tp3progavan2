package com.example.tp3progavan2;

import com.example.tp3progavan2.clases.Usuario;
import com.example.tp3progavan2.negocio.NegocioUsuario;
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
        // Instancia de la clase negocio
        NegocioUsuario negocioUsuario = new NegocioUsuario(this);

        EditText et_name = findViewById(R.id.editTextNombre);
        EditText et_email = findViewById(R.id.editTextEmail);
        EditText et_pass = findViewById(R.id.editTextPass);
        EditText et_pass2 = findViewById(R.id.editTextPass2);

        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String pass = et_pass.getText().toString();
        String pass2 = et_pass2.getText().toString();

        if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !pass2.isEmpty()) {
            if (pass.equals(pass2)) {
                Usuario nuevoUsuario = new Usuario(name, email, pass);
                if (negocioUsuario.registrarUsuario(nuevoUsuario)) {
                    Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                    et_name.setText("");
                    et_email.setText("");
                    et_pass.setText("");
                    et_pass2.setText("");
                    finish();
                } else {
                    Toast.makeText(this, "El email/usuario ya está registrado", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void LoginActivity(View view) {
        setContentView(R.layout.activity_main);
    }
}
