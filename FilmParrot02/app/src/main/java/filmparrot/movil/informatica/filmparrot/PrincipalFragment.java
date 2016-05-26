package filmparrot.movil.informatica.filmparrot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.pm.ActivityInfoCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Random;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Pelicula;
import filmparrot.movil.informatica.filmparrot.logica.Persona;
import filmparrot.movil.informatica.filmparrot.logica.Serie;


public class PrincipalFragment extends Fragment {
    private Pelicula elementoPelicula;
    private Serie elementoSerie;
    private CardView card_gotit, card_film, card_serie;


    public PrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        TextView got_it = (TextView) view.findViewById(R.id.gotit_text);
        card_gotit = (CardView) view.findViewById(R.id.welcome_card);
        card_film = (CardView) view.findViewById(R.id.film_card);
        card_serie = (CardView) view.findViewById(R.id.serie_card);

        elementoPelicula = Utils.fachada.getPeliculas().get(new Random().nextInt(5));
        elementoSerie = Utils.fachada.getSeries().get(new Random().nextInt(3));

        // Película.
        ImageView imageCover = (ImageView) view.findViewById(R.id.coverImage);
        imageCover.setImageResource(elementoPelicula.getImagen());

        TextView pointAverage = (TextView) view.findViewById(R.id.pointAverage);
        TextView pointsLabel = (TextView) view.findViewById(R.id.pointsLabel);
        TextView reviewsLabel = (TextView) view.findViewById(R.id.reviewsLabel);

        pointAverage.setBackgroundColor(Utils.getProgressiveColor(elementoPelicula.getMedia(), getActivity()));
        pointAverage.setText(String.valueOf(elementoPelicula.getMedia()));
        pointsLabel.setText(elementoPelicula.getPuntuaciones().size() + " votos");
        reviewsLabel.setText(elementoPelicula.getNumCriticas() + " críticas");
        ((TextView) view.findViewById(R.id.elementLabel)).setText(elementoPelicula.getTitulo());
        ((TextView) view.findViewById(R.id.descriptionText)).setText(elementoPelicula.getDescripcion());

        //Serie.
        ImageView imageSerieCover = (ImageView) view.findViewById(R.id.coverSerieImage);
        imageSerieCover.setImageResource(elementoSerie.getImagen());

        TextView pointSerieAverage = (TextView) view.findViewById(R.id.pointSerieAverage);
        TextView pointsSerieLabel = (TextView) view.findViewById(R.id.pointsSerieLabel);
        TextView reviewsSerieLabel = (TextView) view.findViewById(R.id.reviewsSerieLabel);

        pointSerieAverage.setBackgroundColor(Utils.getProgressiveColor(elementoSerie.getMedia(), getActivity()));
        pointSerieAverage.setText(String.valueOf(elementoPelicula.getMedia()));
        pointsSerieLabel.setText(elementoSerie.getPuntuaciones().size() + " votos");
        reviewsSerieLabel.setText(elementoSerie.getNumCriticas() + " críticas");
        ((TextView) view.findViewById(R.id.elementSerieLabel)).setText(elementoSerie.getTitulo());
        ((TextView) view.findViewById(R.id.descriptionSerieText)).setText(elementoSerie.getDescripcion());

        got_it.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_gotit.setVisibility(View.GONE);
            }
        });

        card_serie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ElementViewActivity.class);
                intent.putExtra("id", elementoSerie.getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
            }
        });

        card_film.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ElementViewActivity.class);
                intent.putExtra("id", elementoPelicula.getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
            }
        });

        return view;

    }
}
