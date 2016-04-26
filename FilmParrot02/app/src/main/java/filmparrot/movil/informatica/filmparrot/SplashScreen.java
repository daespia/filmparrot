package filmparrot.movil.informatica.filmparrot;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.ProgressBar;

import filmparrot.movil.informatica.filmparrot.logica.Fachada;
import filmparrot.movil.informatica.filmparrot.logica.Pelicula;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;

public class SplashScreen extends Activity {

    private ProgressBar progressBar;
    private int i;
    public static Fachada fachada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY);
        progressBar.setMax(9);
        i = 1;


        fachada = new Fachada();

        Usuario raul = new Usuario("raulher", "1234");
        Usuario manu = new Usuario("manugar", "1234");
        manu.setEsAdministrador(true);
        Usuario dani = new Usuario("daniesp", "1234");

        fachada.anadirUsuario(raul);
        fachada.anadirUsuario(manu);
        fachada.anadirUsuario(dani);
        
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                progressBar.setProgress(i);
                i++;
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
            }
        }.start();
    }
}
