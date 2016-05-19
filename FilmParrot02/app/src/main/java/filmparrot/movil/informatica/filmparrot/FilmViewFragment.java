package filmparrot.movil.informatica.filmparrot;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Pelicula;
import filmparrot.movil.informatica.filmparrot.logica.Persona;

public class FilmViewFragment extends Fragment {

    public FilmViewFragment() {}

    public static FilmViewFragment newInstance(int e) {
        FilmViewFragment f = new FilmViewFragment();
        Bundle args = new Bundle();
        args.putInt("idPelicula",e);
        f.setArguments(args);
        return f;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_film_view, container, false);

        int idpel = getArguments().getInt("idPelicula");
        Pelicula e = (Pelicula) Utils.fachada.getElementoPorId(idpel);

        TextView duracion  = (TextView) view.findViewById(R.id.durationText);
        duracion.setText("" + e.getDuracion());

        TextView genero  = (TextView) view.findViewById(R.id.genreText);
        genero.setText(e.getGenero());

        TextView anio  = (TextView) view.findViewById(R.id.yearText);
        Calendar c = Calendar.getInstance();
        c.setTime(e.getFechaEstreno());
        anio.setText("" + c.get(Calendar.YEAR));

        TextView bso  = (TextView) view.findViewById(R.id.BsoText);
        bso.setText("Desconocida");

        TextView director  = (TextView) view.findViewById(R.id.directorText);
        String directores = "Desconocido";

        if(e.getDirectores() != null){
            directores = "";
            for(Persona p: e.getDirectores()) directores+=p.getTitulo() +", ";
        }
        director.setText(directores);


        TextView actor  = (TextView) view.findViewById(R.id.actorText);
        String actores = "Desconocidos";

        if(e.getActores() != null){
            actores = "";
            for(Persona p: e.getActores()) actores+=p.getTitulo() +", ";
        }
        actor.setText(actores);

        return view;
    }
}
