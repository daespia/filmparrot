package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;


public class ListItemAdapter extends BaseAdapter {

    private Context context;
    private List<Elemento> items;

    public ListItemAdapter(Context context, List<Elemento> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public Object getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_list_item, parent, false);
        }

        // Set data into the view.
        ImageView ivItem = (ImageView) convertView.findViewById(R.id.listImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.listTitle);
        TextView points = (TextView) convertView.findViewById(R.id.listPoints);
        TextView type = (TextView) convertView.findViewById(R.id.elementType);
        TextView country = (TextView) convertView.findViewById(R.id.countryText);

        Elemento item = this.items.get(position);
        points.setBackgroundColor(Utils.getProgressiveColor(item.getMedia(), context));
        points.setText(String.valueOf(item.getMedia()));

        country.setText(item.getPais());
        type.setText(item.getTipo());
        tvTitle.setText(item.getTitulo());
        ivItem.setImageResource(item.getImagen());

        return convertView;
    }

    public void sortListByAlfa(){
        Collections.sort(items, new AlfaComparator());
        notifyDataSetChanged();
    }

    public void sortListByPopularity(){
        Collections.sort(items, new PopularityComparator());
        notifyDataSetChanged();
    }

    public void sortListByRating(){
        Collections.sort(items, new RatingComparator());
        notifyDataSetChanged();
    }

    private class AlfaComparator implements Comparator<Elemento> {
        @Override
        public int compare(Elemento object1, Elemento object2) {
            return object1.getTitulo().compareToIgnoreCase(object2.getTitulo());
        }
    }

    private class RatingComparator implements Comparator<Elemento> {
        @Override
        public int compare(Elemento object1, Elemento object2) {
            return Double.compare(object2.getMedia(), object1.getMedia());
        }
    }

    private class PopularityComparator implements Comparator<Elemento> {
        @Override
        public int compare(Elemento object1, Elemento object2) {
            return object2.getPuntuaciones().size() - object1.getPuntuaciones().size();
        }
    }
}
