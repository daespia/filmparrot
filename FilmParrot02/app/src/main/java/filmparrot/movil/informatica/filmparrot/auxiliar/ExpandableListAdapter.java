package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listGroupTitles;
    private HashMap<String, List<Elemento>> listChilds;

    public ExpandableListAdapter(Context context, List<String> listGroupTitles,
                                 HashMap<String, List<Elemento>> listChilds) {
        this.context = context;
        this.listGroupTitles = listGroupTitles;
        this.listChilds = listChilds;
    }

    @Override
    public Elemento getChild(int groupPosition, int childPosition) {
        return listChilds.get(this.listGroupTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_list_item, parent, false);
        }

        ImageView ivItem = (ImageView) convertView.findViewById(R.id.listImage);
        TextView tvTitle = (TextView) convertView.findViewById(R.id.listTitle);
        TextView points = (TextView) convertView.findViewById(R.id.listPoints);
        ImageView deleteImage = (ImageView) convertView.findViewById(R.id.deleteItem);
        TextView type = (TextView) convertView.findViewById(R.id.elementType);
        TextView country = (TextView) convertView.findViewById(R.id.countryText);

        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listChilds.get(listGroupTitles.get(groupPosition)).remove(childPosition);
                notifyDataSetChanged();
            }
        });

        Elemento item = getChild(groupPosition, childPosition);
        points.setBackgroundColor(Utils.getProgressiveColor(item.getMedia(), context));
        country.setText(item.getPais());
        type.setText(item.getTipo());
        points.setText(String.valueOf(item.getMedia()));
        tvTitle.setText(item.getTitulo());
        ivItem.setImageResource(item.getImagen());

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listChilds.get(this.listGroupTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listGroupTitles.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listGroupTitles.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final String headerTitle = (String) getGroup(groupPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.user_list_group, parent, false);
        }

        TextView groupTitle = (TextView) convertView.findViewById(R.id.groupTitle);
        groupTitle.setText(headerTitle);

        final EditText changeTitle = new EditText(context);
        changeTitle.setText(headerTitle);

        final AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Cambiar nombre");

        alertDialog.setView(changeTitle, 80, 0, 80, 0);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newText = changeTitle.getText().toString();
                if(!newText.equals(headerTitle)) {
                    listGroupTitles.set(groupPosition, newText);
                    listChilds.put(newText, listChilds.get(headerTitle));
                    listChilds.remove(headerTitle);
                    notifyDataSetChanged();
                }
            }
        });

        groupTitle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alertDialog.show();
                return true;
            }
        });

        changeTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(changeTitle.getText().toString().isEmpty()) {
                    changeTitle.setError("No puedes dejar el nombre vacío");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
                else if(listChilds.get(changeTitle.getText().toString()) != null &&
                        !changeTitle.getText().toString().equals(headerTitle)) {
                    changeTitle.setError("Ya existe una lista con ese nombre");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        ImageView deleteGroup = (ImageView) convertView.findViewById(R.id.deleteGroup);
        deleteGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listGroupTitles.remove(groupPosition);
                listChilds.remove(headerTitle);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public boolean hasStableIds() { return false; }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) { return true; }

    public void addList(String titulo, List<Elemento> elementos){
        listGroupTitles.add(titulo);
        listChilds.put(titulo, elementos);
        notifyDataSetChanged();
    }



}
