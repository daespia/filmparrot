package filmparrot.movil.informatica.filmparrot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Persona;


public class PersonViewFragment extends Fragment {

    public PersonViewFragment() {
        // Required empty public constructor
    }

    public static PersonViewFragment newInstance(int e) {
        PersonViewFragment fragment = new PersonViewFragment();
        Bundle args = new Bundle();
        args.putInt("idPersona",e);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person_view, container, false);

        int idpel = getArguments().getInt("idPersona");
        Persona e = (Persona) Utils.fachada.getElementoPorId(idpel);

        TextView apellidos  = (TextView) view.findViewById(R.id.surnameText);
        apellidos.setText("" + e.getApellidos());


        TextView anio  = (TextView) view.findViewById(R.id.birthdateText);
        SimpleDateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
        anio.setText("" + dt.format(e.getFechaNacimiento()));

        TextView director  = (TextView) view.findViewById(R.id.directorText);
        if(e.getEsDirector()){director.setText("Sí");
        }else{director.setText("No");}

        TextView actor  = (TextView) view.findViewById(R.id.actorText);
        if(e.getEsActor()){actor.setText("Sí");
        }else{actor.setText("No");}

        return view;
    }

}
