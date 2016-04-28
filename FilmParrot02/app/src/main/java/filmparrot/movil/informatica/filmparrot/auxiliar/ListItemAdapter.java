package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
}
