package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class ReleasesAdapter extends BaseAdapter {

    private Context context;
    private List<Elemento> items;

    public ReleasesAdapter(Context context, List<Elemento> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.grid_releases_item, parent, false);
        }

        Elemento item = this.items.get(position);

        ((ImageView)convertView.findViewById(R.id.gridImage)).setImageResource(item.getImagen());
        ((TextView)convertView.findViewById(R.id.gridTitle)).setText(item.getTitulo());
        ((TextView)convertView.findViewById(R.id.dayText)).setText("Desde el viernes");
        return convertView;
    }
}
