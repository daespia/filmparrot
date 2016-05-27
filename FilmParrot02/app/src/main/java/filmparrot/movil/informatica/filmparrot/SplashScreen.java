package filmparrot.movil.informatica.filmparrot;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Window;
import android.widget.TextView;

import org.w3c.dom.Text;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;

public class SplashScreen extends Activity {

    private int i = 1;
    private TextView progressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash_screen);

        progressText = (TextView) findViewById(R.id.progressText);
        progressText.bringToFront();

        new CountDownTimer(3000, 30) {

            public void onTick(long millisUntilFinished) {
                progressText.setText(i + "%");
                i++;
            }

            public void onFinish() {
                Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
