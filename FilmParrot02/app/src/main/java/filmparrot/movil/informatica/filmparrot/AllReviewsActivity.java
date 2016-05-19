package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import filmparrot.movil.informatica.filmparrot.auxiliar.ListReviewsAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class AllReviewsActivity extends AppCompatActivity {

    private Elemento elemento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_reviews);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null){
            int id = intent.getIntExtra("id", 0);
            elemento = Utils.fachada.getElementoPorId(id);
        }

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.elementLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setTitle("Críticas de '" + elemento.getTitulo() + "'");
        ((TextView) findViewById(R.id.littleElementLabel)).setText(elemento.getTitulo());
        ((TextView) findViewById(R.id.littleElementType)).setText(elemento.getTipo());
        ((ImageView) findViewById(R.id.littleCoverImage)).setImageResource(elemento.getImagen());

        TextView pointAverage = (TextView) findViewById(R.id.littlePointAverage);
        pointAverage.setBackgroundColor(Utils.getProgressiveColor(elemento.getMedia(), getApplicationContext()));
        pointAverage.setText(String.valueOf(elemento.getMedia()));

        ListView lista = (ListView) findViewById(R.id.reviewsList);

        if(elemento.getPuntuacionesConCritica().isEmpty())
            (findViewById(R.id.no_reviews_image)).setVisibility(View.VISIBLE);

        lista.setAdapter(new ListReviewsAdapter(this, elemento.getPuntuacionesConCritica()));
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
