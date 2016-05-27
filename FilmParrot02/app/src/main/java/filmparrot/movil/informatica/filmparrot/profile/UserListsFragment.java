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
import android.util.Log;
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
import filmparrot.movil.informatica.filmparrot.auxiliar.SwipeDismissList;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;

public class UserListsFragment extends Fragment {

    private ExpandableUserListAdapter expandableAdapter;
    private ExpandableListView expandableList;
    private EditText addList;
    private HashMap<String, List<Elemento>> listasUsuario;
    private AlertDialog alertDialog;

    public UserListsFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_lists, container, false);

        expandableList = (ExpandableListView) view.findViewById(R.id.UserListsList);
        FloatingActionButton floatButton = (FloatingActionButton) view.findViewById(R.id.addListButton);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());

        listasUsuario = Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).getListas();
        List<String> nombres = new ArrayList<>();
        nombres.addAll(listasUsuario.keySet());

        expandableAdapter = new ExpandableUserListAdapter(getContext(), nombres, listasUsuario);
        expandableList.setAdapter(expandableAdapter);

        enableDismissSwipe();
        createNewListAlertDialog();

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
            }
        });

        expandableList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String group = expandableAdapter.getGroup(groupPosition).toString();

                Intent intent = new Intent(getActivity(), ElementViewActivity.class);
                intent.putExtra("id", listasUsuario.get(group).get(childPosition).getId());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.left_toright, R.anim.right_toleft);
                return true;
            }
        });

        return view;
    }

    private void createNewListAlertDialog(){
        alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Nueva lista");
        addList = new EditText(getContext());

        alertDialog.setView(addList, 80, 0, 80, 0);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newText = addList.getText().toString();
                List<Elemento> elementos = new ArrayList<>();
                listasUsuario.put(newText, elementos);
                expandableAdapter.addList(newText, elementos);
            }
        });

        addList.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(addList.getText().toString().isEmpty()) {
                    addList.setError("No puedes dejar el nombre vacío");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
                else if(listasUsuario.get(addList.getText().toString()) != null) {
                    addList.setError("Ya existe una lista con ese nombre");
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                } else {
                    alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void enableDismissSwipe(){
        SwipeDismissList.UndoMode mode = SwipeDismissList.UndoMode.SINGLE_UNDO;

        SwipeDismissList.OnDismissCallback callback = new SwipeDismissList.OnDismissCallback() {

            Elemento itemToDelete;
            String groupString;
            int childPos;

            public SwipeDismissList.Undoable onDismiss(ExpandableListView listView, int groupPosition, int childPosition) {

                groupString = expandableAdapter.getGroup(groupPosition).toString();

                if(childPosition >= 0) {
                    itemToDelete = expandableAdapter.getChild(groupPosition, childPosition);
                    childPos = childPosition;
                    listasUsuario.get(groupString).remove(childPosition);
                    expandableAdapter.notifyDataSetChanged();

                    return new SwipeDismissList.Undoable() {
                        public void undo() {
                            listasUsuario.get(groupString).add(childPos, itemToDelete);
                            expandableAdapter.notifyDataSetChanged();
                        }
                    };
                }

                return null;
            }
        };

        new SwipeDismissList(expandableList, callback, mode);
    }
}
