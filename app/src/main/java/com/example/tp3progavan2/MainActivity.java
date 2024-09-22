package com.example.tp3progavan2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.example.tp3progavan2.clases.Usuario;
import com.example.tp3progavan2.negocio.NegocioUsuario;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tp3progavan2.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");


        setSupportActionBar(binding.appBarMain.toolbar);
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        // Acceder al encabezado de la vista de navegación
        View headerView = navigationView.getHeaderView(0);

        // Referencias a los TextView en el encabezado de navegación
        TextView textViewName = headerView.findViewById(R.id.textView);
        TextView textViewEmail= headerView.findViewById(R.id.textViewEmail); // Asume que agregarás un TextView para el email

        // Verificar que el objeto Usuario no sea null y asignar los valores
        if (usuario != null) {
            textViewName.setText(usuario.getNombre());
            textViewEmail.setText(usuario.getEmail());  // Asegúrate de tener un TextView para el email

        }

        // Configurar el DrawerLayout y el NavController
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();

      //  NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
      //  NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
       /// NavigationUI.setupWithNavController(navigationView, navController);

      //  setContentView(R.layout.activity_main); // o el layout correspondiente

      FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> mostrarDialogRegistroParqueo());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    // Método para mostrar el diálogo
    public void mostrarDialogRegistroParqueo() {
        // Inflar el diseño del diálogo
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_registro_parqueo, null);

        // Obtener los elementos del layout
        EditText nroMatricula = dialogView.findViewById(R.id.editTextNroMatricula);
        EditText tiempo = dialogView.findViewById(R.id.editTextTiempo);
        TextView btnCancelar = dialogView.findViewById(R.id.btnCancelar);
        TextView btnRegistrar = dialogView.findViewById(R.id.btnRegistrar);

        // Crear el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView); // Establecer el layout inflado en el diálogo

        // Mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();

        // Configurar acciones de los botones
        btnCancelar.setOnClickListener(v -> {
            // Acción al presionar "Cancelar"
            dialog.dismiss(); // Cerrar el diálogo
        });

        btnRegistrar.setOnClickListener(v -> {
            // Acción al presionar "Registrar"
            String matricula = nroMatricula.getText().toString();
            String tiempoRegistro = tiempo.getText().toString();

            // Aquí puedes agregar la lógica para manejar los datos registrados
            // Ejemplo: guardar la información o validar los campos.

            dialog.dismiss(); // Cerrar el diálogo
        });
    }
}