package com.example.goodjob.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.goodjob.R;
import com.example.goodjob.classes.ValidSession;
import com.example.goodjob.fragments.EstadoMisActividadesFragment;
import com.example.goodjob.fragments.EstadoMisPublicacionesFragment;
import com.example.goodjob.fragments.HomeFragment;
import com.example.goodjob.fragments.ListaEmpresasEsperaFragment;
import com.example.goodjob.fragments.ListadoActividadEmpresaEstadoFragment;
import com.example.goodjob.fragments.ProductoEsperaFragment;
import com.example.goodjob.fragments.ProductosCanjeFragment;
import com.example.goodjob.fragments.ProfileFragment;
import com.example.goodjob.fragments.RegistrarProductoFragment;
import com.example.goodjob.fragments.SolicitudActividadesEsperaFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigation;
    private FloatingActionButton publicarActividad;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();

        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        publicarActividad = findViewById(R.id.fabPublicarActividad);

        cargarFragment(new HomeFragment());

        // navigation drawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarBotonPublicar();
        publicarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidSession.empresaLogueada != null)
                    startActivity(new Intent(MainActivity.this, PublicarActividadActivity.class));
                else
                    Toast.makeText(getApplicationContext(), "No puedes realizar esta acción", Toast.LENGTH_LONG).show();
            }
        });
        // here i'll do the magic trick
        if (ValidSession.empresaLogueada != null) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.empresa_menu);
        } else if (ValidSession.usuarioLogueado != null && ValidSession.usuarioLogueado.getTipoUsuario().equals("Administrador")) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.administrador_menu);
        }
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void mostrarBotonPublicar() {
        if (ValidSession.empresaLogueada != null)
            publicarActividad.setVisibility(View.VISIBLE);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.navigation_productos:
                    selectedFragment = new ProductosCanjeFragment();
                    break;
                case R.id.navigation_estado_mis_actividades:
                    if (ValidSession.usuarioLogueado == null && ValidSession.empresaLogueada == null) {
                        cuadroDialogo();
                        return true;
                    } else if (ValidSession.empresaLogueada != null) {
                        selectedFragment = new EstadoMisPublicacionesFragment();
                    } else {
                        selectedFragment = new EstadoMisActividadesFragment();
                    }
                    break;
            }
            cargarFragment(selectedFragment);
            return true;
        }
    };

    private void cuadroDialogo() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.inicio_sesion);
        builder.setMessage(R.string.iniciar_sesion);
        builder.setPositiveButton(R.string.ok_sesion, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        setMenu(menu);
        SearchView search = getSearchView(menu);
        setSearchViewHint(search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.ImgProfile:
                sendToLoginOrProfile();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
    }

    private SearchView getSearchView(Menu menu) {
        MenuItem item = menu.findItem(R.id.buscador);
        return (SearchView) item.getActionView();
    }

    private void setSearchViewHint(SearchView search) {
        search.setQueryHint("Buscar...");
    }

    private void sendToLoginOrProfile() {
        if (ValidSession.usuarioLogueado == null && ValidSession.empresaLogueada == null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            navigation.setSelectedItemId(R.id.navigation_profile);
        }
    }

    // mavigation drawer

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_registrar_producto) { // empresas
            cargarFragment(new RegistrarProductoFragment());
        } else if (id == R.id.nav_productos_espera) {
            cargarFragment(new ProductoEsperaFragment());
        } else if (id == R.id.nav_productos_aceptados) {
            // TODO: refactorizar producto espera fragment para que liste acà tmb
        } else if (id == R.id.nav_productos_rechazados) {
            //TODO: refactorizar producto espera fragment para que liste acà tmb
        } else if (id == R.id.nav_actividades_registro) {
            startActivity(new Intent(getApplication(), PublicarActividadActivity.class));
        } else if (id == R.id.nav_actividades_espera) {
            cargarFragmentActividadesEmpresa(0);
        } else if (id == R.id.nav_actividades_aceptadas) {
            cargarFragmentActividadesEmpresa(1);
        } else if (id == R.id.nav_actividades_rechazadas) {
            cargarFragmentActividadesEmpresa(2);
        } else if (id == R.id.nav_solicitud_empresas) { // administradores
            cargarFragment(new ListaEmpresasEsperaFragment());
        } else if (id == R.id.nav_solicitud_actividades) {
            cargarFragment(new SolicitudActividadesEsperaFragment());
        } else if (id == R.id.nav_solicitud_productos) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void cargarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, fragment)
                .commit();
    }

    private void cargarFragmentActividadesEmpresa(int estado) {
        Fragment fragment = new ListadoActividadEmpresaEstadoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("estado", estado);
        fragment.setArguments(bundle);
        cargarFragment(fragment);
    }
}