package com.example.tp3progavan2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tp3progavan2.clases.Usuario;
import com.example.tp3progavan2.negocio.NegocioUsuario;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setSupportActionBar(findViewById(R.id.toolbar));

        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText editTextUsername = findViewById(R.id.editTextUsername);
                EditText editTextPassword = findViewById(R.id.editTextPassword);

                String name = editTextUsername.getText().toString();
                String pass = editTextPassword.getText().toString();

                NegocioUsuario negocioUsuario = new NegocioUsuario(LoginActivity.this);
                if (!name.isEmpty() && !pass.isEmpty()) {
                    Usuario nuevologuser = new Usuario(name, null, pass);
                    if (negocioUsuario.verificarLogin(nuevologuser.getNombre(), nuevologuser.getPass())) {
                        nuevologuser.setEmail(negocioUsuario.obtengoMail(nuevologuser.getNombre(), nuevologuser.getPass()));
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("usuario", nuevologuser);
                        startActivity(intent);
                } else {
                        Toast.makeText(LoginActivity.this,"Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this,"Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void activity_registro_usuario(View view) {
        Intent intent = new Intent(this, RegistroUsuarioActivity.class);
        startActivity(intent);
    }
}