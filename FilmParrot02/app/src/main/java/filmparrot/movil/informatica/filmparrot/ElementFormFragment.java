package filmparrot.movil.informatica.filmparrot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

public class ElementFormFragment extends Fragment {

    public ElementFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_element_form, container, false);

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.elementType);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                FragmentManager fragmentManager  = getChildFragmentManager();
                if(checkedId == R.id.radio_serie) fragmentManager.beginTransaction().
                        replace(R.id.spec_element_content, new ElementFormFragment()).commit();
            }
        });

        return view;
    }
}
