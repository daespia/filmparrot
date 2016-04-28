package filmparrot.movil.informatica.filmparrot;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class VoteActivity extends AppCompatActivity
{
    private Button acceptButton;
    private RatingBar ratingBar;
    private EditText titulo;
    private EditText cuerpo;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        acceptButton = (Button) findViewById(R.id.acceptButton);
        ratingBar = (RatingBar) findViewById(R.id.RatingBar);
        titulo = (EditText) findViewById(R.id.reviewTitleText);
        cuerpo = (EditText) findViewById(R.id.reviewBodyText);

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        if(sharedPref.contains("puntos")){
            acceptButton.setText("Eliminar");
            acceptButton.setBackgroundColor(Color.RED);

            ratingBar.setRating(sharedPref.getFloat("puntos", 0.0f));
            titulo.setEnabled(false);
            titulo.setText(sharedPref.getString("titulo", ""));
            cuerpo.setEnabled(false);
            cuerpo.setText(sharedPref.getString("cuerpo", ""));
            ratingBar.setIsIndicator(true);
        }

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acceptButton.getText().equals("Eliminar")) {
                    sharedPref.edit().remove("puntos").apply();
                    sharedPref.edit().remove("titulo").apply();
                    sharedPref.edit().remove("cuerpo").apply();
                    Toast.makeText(getApplicationContext(), "Tu crítica ha sido eliminada", Toast.LENGTH_SHORT).show();

                } else {
                    sharedPref.edit().putFloat("puntos", ratingBar.getRating()).apply();
                    sharedPref.edit().putString("titulo", titulo.getText().toString()).apply();
                    sharedPref.edit().putString("cuerpo", cuerpo.getText().toString()).apply();
                    Toast.makeText(getApplicationContext(), "Has añadido una crítica", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });


    }
}
