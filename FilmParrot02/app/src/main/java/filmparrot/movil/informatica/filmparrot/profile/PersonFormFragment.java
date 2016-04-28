package filmparrot.movil.informatica.filmparrot.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import filmparrot.movil.informatica.filmparrot.R;

public class PersonFormFragment extends Fragment {

    public PersonFormFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_form, container, false);
    }


}
