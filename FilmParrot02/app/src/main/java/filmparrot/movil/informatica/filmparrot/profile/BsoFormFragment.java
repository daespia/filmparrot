package filmparrot.movil.informatica.filmparrot.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import filmparrot.movil.informatica.filmparrot.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BsoFormFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BsoFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BsoFormFragment extends Fragment {
    public BsoFormFragment() {}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bso_form, container, false);
    }

}
