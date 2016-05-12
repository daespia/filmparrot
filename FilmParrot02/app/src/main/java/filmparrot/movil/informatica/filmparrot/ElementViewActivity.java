package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class ElementViewActivity extends AppCompatActivity {

    private Button vote;
    private ImageView addList;
    private Elemento elemento;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null){
            int id = intent.getIntExtra("id", 0);
            elemento = Utils.fachada.getElementoPorId(id);
        }

        setTitle(elemento.getTitulo());

        ImageView imageCover = (ImageView) findViewById(R.id.coverImage);
        imageCover.setImageResource(elemento.getImagen());

        TextView pointAverage = (TextView) findViewById(R.id.pointAverage);
        pointAverage.setBackgroundColor(Utils.getProgressiveColor(elemento.getMedia(), getApplicationContext()));
        pointAverage.setText(String.valueOf(elemento.getMedia()));

        TextView pointsLabel = (TextView) findViewById(R.id.pointsLabel);
        pointsLabel.setText(elemento.getPuntuaciones().size() + " votos");

        TextView reviewsLabel = (TextView) findViewById(R.id.reviewsLabel);
        reviewsLabel.setText(elemento.getNumCriticas() + " críticas");
        reviewsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllReviewsActivity.class);
                intent.putExtra("id", elemento.getId());
                startActivity(intent);
            }
        });

        ((TextView) findViewById(R.id.descriptionText)).setText(elemento.getDescripcion());
        ((TextView) findViewById(R.id.countryText)).setText(elemento.getPais());
        ((TextView) findViewById(R.id.elementLabel)).setText(elemento.getTitulo());

        vote = (Button) findViewById(R.id.PointsButton);
        vote.setOnClickListener(new View.OnClickListener(){
                                    @Override public void onClick(View v) {
                                        Intent i = new Intent(ElementViewActivity.this, VoteActivity.class);
                                        startActivity(i);
                                    }
                                }
        );

        addList = (ImageView) findViewById(R.id.addList);
        registerForContextMenu(addList);
        addList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContextMenu(addList);
            }
        });

        loadFragment(elemento);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
    }

    @Override
    protected void onResume(){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPref.contains("sessionActive")) {
            vote.setEnabled(true);
            if (sharedPref.contains("puntos")) {
                vote.setText("Tu voto: " + sharedPref.getFloat("puntos", 0.0f)*2);
            } else vote.setText("Votar");

        } else vote.setEnabled(false);

        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplication());
        if (sharedPref.contains("sessionActive")){
            menu.setHeaderTitle("Añadir a una lista");
            HashMap<String, List<Elemento>> elementos = Utils.fachada.getUsuario(
                    sharedPref.getString("sessionActive", null)).getListas();

            for(Map.Entry e: elementos.entrySet()) menu.add(e.getKey().toString());
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).getListas().get(item.getTitle()).add(elemento);
        Toast.makeText(this, "Has añadido '" + elemento.getTitulo() + "' a '" + item.getTitle()+"'.", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void loadFragment(Elemento e){

        FragmentManager fragmentManager = getSupportFragmentManager();

        if(e.getTipo().equalsIgnoreCase("Película")){
            fragmentManager.beginTransaction().replace(R.id.element_frame, FilmViewFragment.newInstance(e.getId())).commit();
        }else if(e.getTipo().equalsIgnoreCase("Bso")){
            fragmentManager.beginTransaction().replace(R.id.element_frame, BsoViewFragment.newInstance(e.getId())).commit();
        }else if(e.getTipo().equalsIgnoreCase("Actor")||e.getTipo().equalsIgnoreCase("Director")||(e.getTipo().equalsIgnoreCase("Actor/Director"))){
            fragmentManager.beginTransaction().replace(R.id.element_frame, PersonViewFragment.newInstance(e.getId())).commit();
        }else if(e.getTipo().equalsIgnoreCase("Serie")){
            fragmentManager.beginTransaction().replace(R.id.element_frame, SerieViewFragment.newInstance(e.getId())).commit();
        }
    }


}