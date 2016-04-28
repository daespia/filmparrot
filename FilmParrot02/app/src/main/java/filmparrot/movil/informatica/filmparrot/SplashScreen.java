package filmparrot.movil.informatica.filmparrot;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.TextView;

public class SplashScreen extends Activity {

    private TextView percentage;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        percentage = (TextView) findViewById(R.id.percentageText);
        contador = 0;
        
        new CountDownTimer(2000, 20) {

            public void onTick(long millisUntilFinished) {
                percentage.setText(contador + "%");
                contador++;
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
