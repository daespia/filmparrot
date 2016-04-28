package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class ElementViewActivity extends AppCompatActivity {

    private Button vote;
    private Elemento elemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent!=null){
            int id = intent.getIntExtra("id", 0);
            elemento = Utils.fachada.getElementoPorId(id);
        }
        setContentView(R.layout.activity_element_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
}
