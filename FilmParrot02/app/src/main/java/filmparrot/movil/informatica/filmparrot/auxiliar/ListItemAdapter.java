package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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

        View rowView = convertView;

        if (convertView == null) {
            // Create a new view into the list.
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.list_item, parent, false);
        }

        // Set data into the view.
        ImageView ivItem = (ImageView) rowView.findViewById(R.id.listImage);
        TextView tvTitle = (TextView) rowView.findViewById(R.id.listTitle);
        TextView points = (TextView) rowView.findViewById(R.id.listPoints);

        Elemento item = this.items.get(position);

        if(item.getMedia() < 5.0){
            points.setBackgroundColor(ContextCompat.getColor(rowView.getContext(), R.color.colorLess5));
        } else if (item.getMedia() > 5.0 && item.getMedia() < 7.5){
            points.setBackgroundColor(ContextCompat.getColor(rowView.getContext(),R.color.colorBetween5));
        } else if (item.getMedia() > 7.5) points.setBackgroundColor(ContextCompat.getColor(rowView.getContext(), R.color.colorMore7_5));

        points.setText("" + item.getMedia());

        tvTitle.setText(item.getTitulo());
        ivItem.setImageResource(item.getImagen());

        return rowView;
    }
}
