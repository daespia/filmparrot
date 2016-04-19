package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.ClipData;
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
import filmparrot.movil.informatica.filmparrot.logica.ListItem;


public class ListItemAdapter extends BaseAdapter {

    private Context context;
    private List<ListItem> items;

    public ListItemAdapter(Context context, List<ListItem> items) {
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

        ListItem item = this.items.get(position);

        if(item.getPoints() < 5.0){
            points.setBackgroundColor(ContextCompat.getColor(rowView.getContext(), R.color.colorLess5));
        } else if (item.getPoints() > 5.0 && item.getPoints() < 7.5){
            points.setBackgroundColor(ContextCompat.getColor(rowView.getContext(),R.color.colorBetween5));
        } else if (item.getPoints() > 7.5) points.setBackgroundColor(ContextCompat.getColor(rowView.getContext(), R.color.colorMore7_5));

        points.setText("" + item.getPoints());

        tvTitle.setText(item.getTitle());
        ivItem.setImageResource(item.getImage());

        return rowView;
    }
}
