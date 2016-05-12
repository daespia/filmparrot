package filmparrot.movil.informatica.filmparrot.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import filmparrot.movil.informatica.filmparrot.ElementViewActivity;
import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.auxiliar.ExpandableUserListAdapter;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class UserListsFragment extends Fragment {

    private ExpandableUserListAdapter exp;
    private EditText addList;
    private HashMap<String, List<Elemento>> listas;
    private AlertDialog alertDialog;

    public UserListsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_lists, container, false);

        ExpandableListView listasUsuario = (ExpandableListView) view.findViewById(R.id.UserListsList);
        FloatingActionButton floatButton = (FloatingActionButton) view.findViewById(R.id.addListButton);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

        listas = Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).getListas();
        List<String> nombres = new ArrayList<>();
        nombres.addAll(listas.keySet());

        exp = new ExpandableUserListAdapter(getContext(), nombres, listas);
        listasUsuario.setAdapter(exp);

        alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Nueva lista");
        addList = new EditText(getContext());

        alertDialog.setView(addList, 80, 0, 80, 0);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newText = addList.getText().toString();
                List<Elemento> elementos = new ArrayList<>();
                listas.put(newText, elementos);
                exp.addList(newText, elementos);
            }
        });

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        });

        addList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(addList.getText().toString().isEmpty()) {
                    addList.setError("No puedes dejar el nombre vacío");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
                else if(listas.get(addList.getText().toString()) != null) {
                    addList.setError("Ya existe una lista con ese nombre");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });



        listasUsuario.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
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

        return view;
    }

}
