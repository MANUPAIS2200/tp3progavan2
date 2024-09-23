package com.example.tp3progavan2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.GridView;
import android.widget.TextView;

import com.example.tp3progavan2.clases.Parking;
import com.example.tp3progavan2.clases.Usuario;
import com.example.tp3progavan2.negocio.NegocioParking;
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
import android.widget.Toast;

import com.example.tp3progavan2.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");

        // Configurar el GridView
        GridView gridViewParking = findViewById(R.id.gridViewParking);
        refrescarGridView(usuario.getNombre());
        NegocioParking negocioParking = new NegocioParking(this);
        List<Parking> listaParking = negocioParking.obtenerParkingsPorUsuario(usuario.getNombre());

        // Configurar el adaptador para el GridView
        ParkingAdapter adapter = new ParkingAdapter(this, listaParking);
        gridViewParking.setAdapter(adapter);

        setSupportActionBar(binding.appBarMain.toolbar);
        drawer = binding.drawerLayout;
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
                R.id.nav_home, R.id.nav_gallery, R.id.nav_logout)
                .setOpenableLayout(drawer)
                .build();

        AtomicReference<NavController> navController = new AtomicReference<>();
        binding.getRoot().post(() -> {
            navController.set(Navigation.findNavController(this, R.id.nav_host_fragment_content_main));
            NavigationUI.setupActionBarWithNavController(this, navController.get(), mAppBarConfiguration);
            NavigationUI.setupWithNavController(binding.navView, navController.get());
        });

        binding.navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_logout) {
                // accion de cierre de sesion.

                // Redirigir al login activity
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                return true; // Retorna true si manejaste la selección
            }

            // Si no es "Cerrar sesión", dejar que NavigationUI maneje la acción
            return NavigationUI.onNavDestinationSelected(item, navController.get()) || super.onOptionsItemSelected(item);
        });
      //  setContentView(R.layout.activity_main); // o el layout correspondiente

      FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> mostrarDialogRegistroParqueo(usuario));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment_content_main), drawer) || super.onSupportNavigateUp();
    }

    // Método para mostrar el diálogo
    public void mostrarDialogRegistroParqueo(Usuario usuario) {
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
            String nombre = usuario.getNombre();

            if (!matricula.isEmpty() && !tiempoRegistro.isEmpty() ) {
                Parking parking = new Parking(matricula,tiempoRegistro,nombre,"");
                NegocioParking negocioParking = new NegocioParking(this);
                if (!negocioParking.verificarParking(parking)) {
                    if (negocioParking.registrarParking(parking)) {
                        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        refrescarGridView(usuario.getNombre());
                        nroMatricula.setText("");
                        tiempo.setText("");
                    } else {
                        Toast.makeText(this, "Error en registro", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "La matricula está parkeada", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
            }

            dialog.dismiss(); // Cerrar el diálogo
        });
    }

    private void refrescarGridView(String usuario) {
        NegocioParking negocioParking = new NegocioParking(this);
        List<Parking> listaParking = negocioParking.obtenerParkingsPorUsuario(usuario);
        GridView gridViewParking = findViewById(R.id.gridViewParking);

        ParkingAdapter adapter = new ParkingAdapter(this, listaParking);
        gridViewParking.setAdapter(adapter);
    }
}