package filmparrot.movil.informatica.filmparrot;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;

public class SigninFragment extends Fragment {

    private EditText userText;
    private EditText passwordText;
    private EditText confirmpasswordText;
    private OnSignInInteractionListener mListener;

    // Constructor público vacío. NO BORRAR. Da error.
    public SigninFragment() {
    }

    // Constructor personalizado por si necesitamos pasar parámetros desde la actividad al fragmento
    // en el momento de crearlo.
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    // Método con el que se carga la interfaz gráfica.
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_signin, container, false);

        Button entrar = (Button) view.findViewById(R.id.loginButton);
        userText = (EditText) view.findViewById(R.id.usernameText);
        passwordText = (EditText) view.findViewById(R.id.passwordText);
        confirmpasswordText = (EditText) view.findViewById(R.id.confirmpasswordText);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userText.getText().toString();
                String password = passwordText.getText().toString();
                String confirm_password = confirmpasswordText.getText().toString();

                if (Utils.fachada.getUsuario(username) == null) {
                    if (password.equals(confirm_password) && !password.isEmpty()) {
                        Usuario u = new Usuario(username, password);
                        Utils.fachada.anadirUsuario(u);

                        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("sessionActive", username);
                        if(Utils.fachada.getUsuario(username).getEsAdministrador()){
                            editor.putBoolean("adminUser", true);
                        }
                        editor.apply();
                        mListener.onSignInSuccess(username);

                    } else {
                        passwordText.setText("");
                        confirmpasswordText.setText("");
                        userText.setError("Las contraseñas no coinciden");
                    }
                } else {
                    userText.setError("Ya existe un usuario con ese nombre");
                }

            }
        });

        return view;
    }

    @Override
    // Método mediante el cual el fragmento se adjunta a su actividad.
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSignInInteractionListener) {
            mListener = (OnSignInInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginInteractionListener");
        }
    }

    @Override
    // El opuesto a onAttach. El fragmento se desvincula de su actividad.
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSignInInteractionListener {
        // TODO: Update argument type and name
        void onSignInSuccess(String username);
    }
}
