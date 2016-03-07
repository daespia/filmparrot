package filmparrot.movil.informatica.filmparrot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

class ProfileFragmentAdapter extends FragmentPagerAdapter {

    private final List<Fragment> fragments;

    public ProfileFragmentAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    public String getTitleFromPosition(int position){
        if(position == 0) return "Mi perfil";
        if(position == 1) return "Mi actividad";
        if(position == 2) return "Añadir elemento";
        return "Mi perfil";
    }
}
