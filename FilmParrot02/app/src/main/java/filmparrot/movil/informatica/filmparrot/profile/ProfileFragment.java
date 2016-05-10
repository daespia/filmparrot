package filmparrot.movil.informatica.filmparrot.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;


public class ProfileFragment extends Fragment {
    private static int i =0;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());



        final TextView username  = (TextView) view.findViewById(R.id.usernameText);
        username.setText(Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).getNombre());

        final TextView password  = (TextView) view.findViewById(R.id.passwordText);
        password.setText(Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null)).getContrasena());

        Button verCont  = (Button) view.findViewById(R.id.seePassword);
        verCont.setOnClickListener(
                new View.OnClickListener() {
                    int i=0;
                    @Override
                    public void onClick(View v) {



                        if(i==0){
                            i++;
                            password.setInputType(InputType.TYPE_CLASS_TEXT);
                        }
                        else if(i==1){
                            i--;
                            password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        }
                    }
                }
        );

        final EditText changepassword  = (EditText) view.findViewById(R.id.changeText);


        final Button cambiar  = (Button) view.findViewById(R.id.acceptButton);
        cambiar.setOnClickListener(
                new View.OnClickListener() {
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                    @Override
                    public void onClick(View v) {
                        if(changepassword.getText().toString().isEmpty()){
                            changepassword.setError("La contraseña no puede ser vacía");
                        }else{
                            Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null))
                                    .setContrasena(changepassword.getText().toString());

                            Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                    "Has cambiado la contraseña con éxtio", Toast.LENGTH_SHORT);
                            toast.show();
                        }


                    }
                }
        );


        return view;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

}
