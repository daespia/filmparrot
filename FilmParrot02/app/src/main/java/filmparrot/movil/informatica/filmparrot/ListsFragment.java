package filmparrot.movil.informatica.filmparrot;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import filmparrot.movil.informatica.filmparrot.auxiliar.ExpandableListAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.ExpandableUserListAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class ListsFragment extends Fragment {

    private ExpandableListAdapter exp;
    private HashMap<String, List<Elemento>> listas;
    SharedPreferences sharedPref;

    public ListsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_lists, container, false);

        ExpandableListView listasFachada = (ExpandableListView) v.findViewById(R.id.FilmParrotLists);

        listas = Utils.fachada.getListas();
        List<String> nombres = new ArrayList<>();
        nombres.addAll(listas.keySet());
        exp = new ExpandableListAdapter(getContext(), nombres, listas);
        listasFachada.setAdapter(exp);
        registerForContextMenu(listasFachada);
        listasFachada.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String group = exp.getGroup(groupPosition).toString();

                Intent intent = new Intent(getActivity(), ElementViewActivity.class);
                intent.putExtra("id", listas.get(group).get(childPosition).getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
                return true;
            }
        });

        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        if (sharedPref.contains("sessionActive")) menu.add("Copiar a mis listas");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        ExpandableListView.ExpandableListContextMenuInfo info = (ExpandableListView.ExpandableListContextMenuInfo) item.getMenuInfo();
        String group = exp.getGroup(ExpandableListView.getPackedPositionGroup(info.packedPosition)).toString();

        List<Elemento> nuevaLista = new ArrayList<>();
        for (Elemento e : exp.getChilds(group)) nuevaLista.add(e);

        Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).getListas().put(group, nuevaLista);
        Toast.makeText(getContext(), "Has añadido '" + group + "' a tus listas", Toast.LENGTH_SHORT).show();
        return true;
    }



}
