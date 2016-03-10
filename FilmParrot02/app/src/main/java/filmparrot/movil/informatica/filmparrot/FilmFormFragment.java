package filmparrot.movil.informatica.filmparrot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class FilmFormFragment extends Fragment {

    Spinner genre;

    public FilmFormFragment() { }

    public static FilmFormFragment newInstance(String param1, String param2) {
        FilmFormFragment fragment = new FilmFormFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_film_form, container, false);

        genre = (Spinner) view.findViewById(R.id.genre);
        ArrayAdapter<CharSequence> array_adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.film_genre_array, R.layout.spinner_item);
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(array_adapter);

        return view;
    }
}
