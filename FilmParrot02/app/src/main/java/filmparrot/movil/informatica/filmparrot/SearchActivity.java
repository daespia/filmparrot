package filmparrot.movil.informatica.filmparrot;

import android.app.SearchManager;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import filmparrot.movil.informatica.filmparrot.auxiliar.ListItemAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.SuggestionsProvider;
import filmparrot.movil.informatica.filmparrot.logica.ListItem;

public class SearchActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Búsqueda");
        handleIntent(getIntent());
    }

    public void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        // call detail activity for clicked entry
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, SuggestionsProvider.AUTHORITY, SuggestionsProvider.MODE);
            suggestions.saveRecentQuery(query, null);
            doSearch(query);
        }
    }

    private void doSearch(String queryStr) {
        ListView lista = (ListView) findViewById(R.id.ResultList);

        List items = new ArrayList();
        items.add(new ListItem(R.drawable.cover, "Deadpool"));
        items.add(new ListItem(R.drawable.cover, "Deadpool returns"));
        items.add(new ListItem(R.drawable.cover, "Deadpool II"));
        items.add(new ListItem(R.drawable.cover, "X-MEN: primera generación"));
        // Sets the data behind this ListView
        lista.setAdapter(new ListItemAdapter(this, items));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ElementViewActivity.class);
                startActivity(intent);
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
}
