package filmparrot.movil.informatica.filmparrot.profile;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.auxiliar.NewElementFragmentAdapter;

public class NewElementActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    private NewElementFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_element);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager = (ViewPager) this.findViewById(R.id.new_element_viewpager);
        adapter = new NewElementFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new FilmFormFragment());
        adapter.addFragment(new PersonFormFragment());
        adapter.addFragment(new BsoFormFragment());
        adapter.addFragment(new SerieFragment());
        pager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.new_element_tabs);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                setTitle(adapter.getTitleFromPosition(tab.getPosition()));
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}
            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                setTitle(adapter.getTitleFromPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.film);
        tabLayout.getTabAt(1).setIcon(R.drawable.person);
        tabLayout.getTabAt(2).setIcon(R.drawable.bso);
        tabLayout.getTabAt(3).setIcon(R.drawable.serie);
    }

    @Override
    public void onBackPressed() {
        if (this.pager.getCurrentItem() == 0)
            super.onBackPressed();
        else
            this.pager.setCurrentItem(this.pager.getCurrentItem() - 1);
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
