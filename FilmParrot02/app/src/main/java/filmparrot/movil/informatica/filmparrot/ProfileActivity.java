package filmparrot.movil.informatica.filmparrot;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileActivity extends AppCompatActivity {

    private ViewPager pager;
    private TabLayout tabLayout;
    private ProfileFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pager = (ViewPager) this.findViewById(R.id.profile_viewpager);
        adapter = new ProfileFragmentAdapter(getSupportFragmentManager());
        adapter.addFragment(new FilmFormFragment());
        adapter.addFragment(new PersonFormFragment());
        adapter.addFragment(new BsoFormFragment());
        pager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.profile_tabs);
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
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                setTitle(adapter.getTitleFromPosition(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        tabLayout.setupWithViewPager(pager);
        tabLayout.getTabAt(0).setIcon(R.drawable.eye);
        tabLayout.getTabAt(1).setIcon(R.drawable.pencil);
        tabLayout.getTabAt(2).setIcon(R.drawable.file_add);
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
