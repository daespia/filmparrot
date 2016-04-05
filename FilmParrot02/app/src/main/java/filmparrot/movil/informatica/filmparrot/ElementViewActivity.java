package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class ElementViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_element_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String elementName = "Deadpool";
        setTitle(elementName);

        Random r = new Random();
        double points = Math.floor(10*(10.0 * r.nextDouble()))/10;

        ImageView imageCover = (ImageView) findViewById(R.id.coverImage);
        imageCover.setImageResource(R.drawable.cover);
        TextView pointAverage = (TextView) findViewById(R.id.pointAverage);
        pointAverage.setText("" + points);

        if(points < 5.0){
            pointAverage.setBackgroundColor(ContextCompat.getColor(this, R.color.colorLess5));
        } else if (points > 5.0 && points < 7.5){
            pointAverage.setBackgroundColor(ContextCompat.getColor(this,R.color.colorBetween5));
        } else if (points > 7.5) pointAverage.setBackgroundColor(ContextCompat.getColor(this,R.color.colorMore7_5));



        Button vote = (Button) findViewById(R.id.PointsButton);

        vote.setOnClickListener(new View.OnClickListener(){
                                 @Override
                                 public void onClick(View v) {
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
}
