package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;
import filmparrot.movil.informatica.filmparrot.logica.Puntuacion;

public class ListReviewsAdapter extends BaseAdapter {
    private Context context;
    private List<Puntuacion> puntuaciones;

    public ListReviewsAdapter(Context context, List<Puntuacion> puntuaciones) {
        this.context = context;
        this.puntuaciones = puntuaciones;
    }

    @Override
    public int getCount() { return puntuaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return puntuaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Puntuacion p = puntuaciones.get(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.review_list_item, parent, false);
        }

        ((RatingBar) convertView.findViewById(R.id.smallRatingBar)).setRating(new Float(p.getValor())/2f);
        ((TextView) convertView.findViewById(R.id.reviewTitle)).setText(p.getCritica().getTitulo());
        ((TextView) convertView.findViewById(R.id.reviewBodyText)).setText(p.getCritica().getCuerpo());
        ((TextView) convertView.findViewById(R.id.smallPointsText)).setText("(" + p.getValor() + ")");
        ((TextView) convertView.findViewById(R.id.userReviewTitle)).setText("por " + p.getUsuario().getNombre());

        return convertView;
    }
}
