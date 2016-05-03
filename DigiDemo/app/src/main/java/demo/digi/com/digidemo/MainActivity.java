package demo.digi.com.digidemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Button> botones;
    ViewPager pager;
    int i = 0;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button)findViewById(R.id.b1);
        Button b2 = (Button)findViewById(R.id.b2);
        Button b3 = (Button)findViewById(R.id.b3);
        Button b4 = (Button)findViewById(R.id.b4);
        Button b5 = (Button)findViewById(R.id.b5);

        b1.setPressed(true);

        botones = new ArrayList<Button>();
        botones.add(b1);
        botones.add(b2);
        botones.add(b3);
        botones.add(b4);
        botones.add(b5);

        pager = (ViewPager) this.findViewById(R.id.pager);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new PlayerFragment());
        adapter.addFragment(new PlayerFragment());
        adapter.addFragment(new PlayerFragment());
        adapter.addFragment(new PlayerFragment());
        adapter.addFragment(new PlayerFragment());

        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                botones.get(position).setPressed(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // pager.setCurrentItem(0);
               b1.setPressed(true);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1);
               // v.setPressed(true);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2);
                //v.setPressed(true);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(3);
                //v.setPressed(true);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(4);
               // v.setPressed(true);
            }
        });
    }

    private void changeButtonPressed(int position){
        for(Button b: botones){ b.setPressed(false);}
        botones.get(position).setPressed(true);
    }
}
