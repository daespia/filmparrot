package filmparrot.movil.informatica.filmparrot;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;


public class ElementFormFragment extends Fragment {

    Spinner spinner;
    private FragmentManager fragmentManager;
    private ElementSpinnerListener e;

    public ElementFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_element_form, container, false);

        spinner = (Spinner) view.findViewById(R.id.elementType);
        ArrayAdapter<CharSequence> array_adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.elementType_array, R.layout.spinner_item);
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array_adapter);

        spinner.setOnItemSelectedListener(new ElementSpinnerListener());
        return view;
    }



    private class ElementSpinnerListener implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            switch (spinner.getSelectedItem()){
                case 'Película':
                    getChildFragmentManager().beginTransaction().replace(R.id.spec_element_content,new FilmFormFragment()).commit();
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }


    }
}
