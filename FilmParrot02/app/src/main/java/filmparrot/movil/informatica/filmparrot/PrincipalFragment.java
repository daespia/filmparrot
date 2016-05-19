package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Pelicula;
import filmparrot.movil.informatica.filmparrot.logica.Persona;


public class PrincipalFragment extends Fragment {
    private Pelicula elemento;


    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        Random ran = new Random();
        int k = (int)(ran.nextDouble() * Utils.fachada.getPeliculas().size());
        elemento = (Pelicula)Utils.fachada.getElementoPorId(Utils.fachada.getPeliculas().get(k).getId());

        ImageView imageCover = (ImageView) view.findViewById(R.id.coverImage);
        imageCover.setImageResource(elemento.getImagen());

        TextView pointAverage = (TextView) view.findViewById(R.id.pointAverage);
        pointAverage.setBackgroundColor(Utils.getProgressiveColor(elemento.getMedia(), getActivity().getApplicationContext()));
        pointAverage.setText(String.valueOf(elemento.getMedia()));

        TextView pointsLabel = (TextView) view.findViewById(R.id.pointsLabel);
        pointsLabel.setText(elemento.getPuntuaciones().size() + " votos");

        TextView reviewsLabel = (TextView) view.findViewById(R.id.reviewsLabel);
        reviewsLabel.setText(elemento.getNumCriticas() + " críticas");
        reviewsLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AllReviewsActivity.class);
                intent.putExtra("id", elemento.getId());
                startActivity(intent);
            }
        });

        ((TextView) view.findViewById(R.id.descriptionText)).setText(elemento.getDescripcion());
        ((TextView) view.findViewById(R.id.countryText)).setText(elemento.getPais());
        ((TextView) view.findViewById(R.id.elementLabel)).setText("Película del día: "+elemento.getTitulo());

        TextView duracion  = (TextView) view.findViewById(R.id.durationText);
        duracion.setText("" + elemento.getDuracion());

        TextView genero  = (TextView) view.findViewById(R.id.genreText);
        genero.setText(elemento.getGenero());

        TextView anio  = (TextView) view.findViewById(R.id.yearText);
        Calendar c = Calendar.getInstance();
        c.setTime(elemento.getFechaEstreno());
        anio.setText("" + c.get(Calendar.YEAR));

        TextView bso  = (TextView) view.findViewById(R.id.BsoText);
        //bso.setText(e.getBso().getTitulo());

        TextView director  = (TextView) view.findViewById(R.id.directorText);
        String directores = "";

        if(elemento.getDirectores()==null){
            director.setText("Desconocido");
        }else{
            for(Persona p: elemento.getDirectores()){
                directores+=p.getTitulo() +", ";
            }
            director.setText(directores);
        }

        TextView actor  = (TextView) view.findViewById(R.id.actorText);
        String actores = "";

        if(elemento.getActores()==null){
            actor.setText("Desconocido");
        }else{
            for(Persona p: elemento.getActores()){
                actores+= p.getTitulo() + ", ";
            }
            actor.setText(actores);
        }

        return view;

    }
}
