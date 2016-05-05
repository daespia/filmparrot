package filmparrot.movil.informatica.filmparrot;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Persona;
import filmparrot.movil.informatica.filmparrot.logica.Serie;


public class SerieViewFragment extends Fragment {


    public SerieViewFragment() {
        // Required empty public constructor
    }

    public static SerieViewFragment newInstance(int e) {
        SerieViewFragment fragment = new SerieViewFragment();
        Bundle args = new Bundle();
        args.putInt("IdSerie", e);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_serie_view, container, false);

        int idpel = getArguments().getInt("IdSerie");
        Serie e = (Serie) Utils.fachada.getElementoPorId(idpel);

        TextView aniocom  = (TextView) view.findViewById(R.id.startText);
        aniocom.setText("" + e.getAnioComienzo());

        TextView aniofin  = (TextView) view.findViewById(R.id.endText);
        aniofin.setText("" + e.getAnioFinal());

        TextView tempo  = (TextView) view.findViewById(R.id.seasonText);
        tempo.setText("" + e.getTemporadas());

        TextView capt  = (TextView) view.findViewById(R.id.chapterText);
        capt.setText("" + e.getCapitulos());

        TextView bso  = (TextView) view.findViewById(R.id.BsoText);
        //bso.setText(e.getBso().getTitulo());

        TextView director  = (TextView) view.findViewById(R.id.directorText);
        String directores ="";
        for(Persona p: e.getDirectores()){
            directores+= ", " +p.getTitulo();
        }
        director.setText(directores);

        TextView actor  = (TextView) view.findViewById(R.id.actorText);
        String actores ="";
        for(Persona p: e.getActores()){
            actores+= ", " +p.getTitulo();
        }
        actor.setText(actores);

        return view;
    }

}
