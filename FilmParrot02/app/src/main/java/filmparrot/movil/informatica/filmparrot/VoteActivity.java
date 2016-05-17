package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
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

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Critica;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;
import filmparrot.movil.informatica.filmparrot.logica.Puntuacion;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;

public class VoteActivity extends AppCompatActivity
{
    private Button acceptButton;
    private RatingBar ratingBar;
    private EditText titulo, cuerpo;
    private Usuario usuario;
    private Puntuacion puntuacion;
    private Elemento elemento;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        acceptButton = (Button) findViewById(R.id.acceptButton);
        ratingBar = (RatingBar) findViewById(R.id.RatingBar);
        titulo = (EditText) findViewById(R.id.reviewTitleText);
        cuerpo = (EditText) findViewById(R.id.reviewBodyText);

        Intent intent = getIntent();
        if(intent != null){
            int id = intent.getIntExtra("id", 0);
            elemento = Utils.fachada.getElementoPorId(id);
        }

        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        usuario = Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null));
        puntuacion = usuario.getPuntuacionDeElemento(elemento);

        if(puntuacion != null){
            acceptButton.setText("Eliminar");
            acceptButton.setBackgroundColor(Color.RED);

            ratingBar.setRating(new Float(puntuacion.getValor())/2f);
            titulo.setEnabled(false);
            cuerpo.setEnabled(false);

            if(puntuacion.getCritica() != null){
                titulo.setText(puntuacion.getCritica().getTitulo());
                cuerpo.setText(puntuacion.getCritica().getCuerpo());
            }
            ratingBar.setIsIndicator(true);
        }

        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (acceptButton.getText().equals("Eliminar")) {
                    elemento.getPuntuaciones().remove(puntuacion);
                    usuario.eliminarPuntuacion(puntuacion);
                    Toast.makeText(getApplicationContext(), "Tu crítica ha sido eliminada", Toast.LENGTH_SHORT).show();
                    finish();

                } else {

                    Critica critica = null;
                    String titString = titulo.getText().toString();
                    String cuerpoString = cuerpo.getText().toString();

                    if((titString.isEmpty() && cuerpoString.isEmpty()) ||
                            (!titString.isEmpty() && !titString.isEmpty())) {

                        if(!titString.isEmpty() && !cuerpoString.isEmpty())
                            critica = new Critica(titString, cuerpoString);

                        elemento.anadirPuntuacion(new Puntuacion(ratingBar.getRating()*2d, critica, usuario));
                        Toast.makeText(getApplicationContext(), "Has añadido una crítica", Toast.LENGTH_SHORT).show();
                        finish();

                    } else{
                        titulo.setError("Añade un título y un cuerpo, o deja la crítica vacía");
                    }
                }


            }
        });


    }
}
