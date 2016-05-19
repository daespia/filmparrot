package filmparrot.movil.informatica.filmparrot;

import android.app.LauncherActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.provider.SearchRecentSuggestions;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filmparrot.movil.informatica.filmparrot.auxiliar.ListItemAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.SuggestionsProvider;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class SearchActivity extends AppCompatActivity {

    private List<Elemento> items;
    private SharedPreferences sharedPref;
    private SearchRecentSuggestions suggestions;
    private ImageButton az, rating, popularity;
    ListItemAdapter listItemAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        handleIntent(getIntent());
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            if(query != null) {
                setTitle("Búsqueda de '" + query + "'");
                suggestions = new SearchRecentSuggestions(this, SuggestionsProvider.AUTHORITY, SuggestionsProvider.MODE);
                suggestions.saveRecentQuery(query, null);

                FloatingActionButton removeSuggest = (FloatingActionButton) findViewById(R.id.removeSuggestButton);
                removeSuggest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        suggestions.clearHistory();
                    }
                });

                doSearch(query);
            }
        }
    }

    private void doSearch(String queryStr) {
        ListView lista = (ListView) findViewById(R.id.ResultList);

        items = Utils.fachada.getElementoPorNombre(queryStr);
        if(items.size() == 0){
            findViewById(R.id.no_results_image).setVisibility(View.VISIBLE);
        }

        listItemAdapter = new ListItemAdapter(this, items);

        az = (ImageButton) findViewById(R.id.az_button);
        az.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemAdapter.sortListByAlfa();
            }
        });
        rating = (ImageButton) findViewById(R.id.rating_button);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemAdapter.sortListByRating();
            }
        });
        popularity = (ImageButton) findViewById(R.id.popularity_button);
        popularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItemAdapter.sortListByPopularity();
            }
        });

        lista.setAdapter(listItemAdapter);

        registerForContextMenu(lista);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ElementViewActivity.class);
                intent.putExtra("id", items.get(position).getId());
                startActivity(intent);
                overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int idElemento = items.get(info.position).getId();
        Elemento elemento = Utils.fachada.getElementoPorId(idElemento);
        Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).anadirElementoALista(item.getTitle().toString(), elemento);
        Toast.makeText(this, "Has añadido '" + elemento.getTitulo() + "' a '" + item.getTitle()+"'.", Toast.LENGTH_SHORT).show();
        return true;
    }

}
