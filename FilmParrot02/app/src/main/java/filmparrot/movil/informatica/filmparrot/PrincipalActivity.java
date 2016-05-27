package filmparrot.movil.informatica.filmparrot;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import filmparrot.movil.informatica.filmparrot.profile.ProfileActivity;

public class PrincipalActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        LoginFragment.OnLoginInteractionListener, SigninFragment.OnSignInInteractionListener {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private SharedPreferences sharedPref;
    private SessionManager session_manager;
    private FragmentManager fragmentManager;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        fragmentManager = getSupportFragmentManager();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Añadir el icono a la barra y asociarlo al menú lateral.
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Listener que detecta un click sobre los elementos del menú.
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        // Listener que detecta si el almacenamiento de preferencias ha cambiado.
        session_manager = new SessionManager();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPref.registerOnSharedPreferenceChangeListener(session_manager);

        fragmentManager.beginTransaction().replace(R.id.content_frame, new PrincipalFragment()).commit();

    }

    @Override
    protected void onResume(){
        session_manager.onSharedPreferenceChanged(sharedPref, null);
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Intent intent = null;
        int id = item.getItemId();

        if (id == R.id.home_item) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new PrincipalFragment()).commit();
            setTitle("FilmParrot");

        } else if (id == R.id.releases_item) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ReleasesFragment()).commit();
            setTitle("Estrenos");

        }else if (id == R.id.signin_item) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new SigninFragment()).commit();
            setTitle("Registrarse");

        } else if (id == R.id.lists_item) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ListsFragment()).commit();
            setTitle("Listas");

        } else if (id == R.id.login_item) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
            setTitle("Iniciar sesión");

        } else if (id == R.id.logout_item){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Cerrar sesión");
            builder.setIcon(R.drawable.logout_mini);
            builder.setMessage("¿Estás seguro de que quieres cerrar sesión?")
                    .setNegativeButton("NO", new LogoutDialogListener())
                    .setPositiveButton("SÍ", new LogoutDialogListener()).show();

        } else if (id == R.id.viewprofile_item) {
            intent = new Intent(this, ProfileActivity.class);
        }

        if(intent != null) startActivity(intent);
        else drawer.closeDrawer(GravityCompat.START);
        searchView.onActionViewCollapsed();
        return true;
    }

    private class SessionManager implements SharedPreferences.OnSharedPreferenceChangeListener{
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            Menu menu = navigationView.getMenu();
            boolean condition = sharedPreferences.contains("sessionActive");
            menu.findItem(R.id.login_item).setVisible(!condition);
            menu.findItem(R.id.signin_item).setVisible(!condition);
            menu.findItem(R.id.logout_item).setVisible(condition);
            menu.findItem(R.id.viewprofile_item).setVisible(condition);
            menu.findItem(R.id.logout_item).setVisible(condition);
        }
    }

    private class LogoutDialogListener implements DialogInterface.OnClickListener{
        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which){
                case DialogInterface.BUTTON_POSITIVE:
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("sessionActive");
                    editor.remove("adminUser");
                    editor.apply();
                    Toast.makeText(PrincipalActivity.this, "Has cerrado sesión", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

    @Override
    public void onLoginSuccess(String username){
        fragmentManager.beginTransaction().replace(R.id.content_frame, new PrincipalFragment()).commit();
        Toast.makeText(this, "Bienvenido, " + username, Toast.LENGTH_SHORT).show();
        setTitle("FilmParrot");
    }

    @Override
    public void onSignInSuccess(String username){
        fragmentManager.beginTransaction().replace(R.id.content_frame, new PrincipalFragment()).commit();
        Toast.makeText(this, "Bienvenido, " + username, Toast.LENGTH_SHORT).show();
        setTitle("FilmParrot");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        ComponentName cn = new ComponentName(this, SearchActivity.class);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(cn));
        searchView.setIconifiedByDefault(true);
        searchView.setQueryRefinementEnabled(true);
        return true;
    }
}
