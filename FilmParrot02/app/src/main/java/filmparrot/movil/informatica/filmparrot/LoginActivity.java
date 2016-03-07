package filmparrot.movil.informatica.filmparrot;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Fragment {

    private EditText userText;
    private EditText passwordText;
    private TextView userLabel;
    private TextView passwordLabel;

    private OnLoginInteractionListener mListener;

    // Constructor público vacío. NO BORRAR. Da error.
    public LoginActivity() {
    }

    // Constructor personalizado por si necesitamos pasar parámetros desde la actividad al fragmento
    // en el momento de crearlo.
    public static LoginActivity newInstance() {
        return new LoginActivity();
    }

    @Override
    // Método con el que se carga la interfaz gráfica.
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Button entrar = (Button) view.findViewById(R.id.loginButton);
        userText = (EditText) view.findViewById(R.id.usernameText);
        passwordText = (EditText) view.findViewById(R.id.passwordText);
        userLabel = (TextView) view.findViewById(R.id.userLabel);
        passwordLabel = (TextView) view.findViewById(R.id.passwordLabel);

        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = userText.getText().toString();
                String password = passwordText.getText().toString();

                if (username.equals("admin") && password.equals("adminadmin")) {
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putBoolean("sessionActive", true);
                    editor.apply();
                    mListener.onLoginSuccess();

                } else {
                    userText.setText("");
                    passwordText.setText("");
                    userLabel.setTextColor(Color.RED);
                    passwordLabel.setTextColor(Color.RED);
                }
            }
        });

        return view;
    }


    @Override
    // Método mediante el cual el fragmento se adjunta a su actividad.
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
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

    // Interfaz para comunicarnos con la actividad. La actividad es la que implementa los métodos
    // que se especifiquen aquí. Podemos llamar a esa implementación desde el fragmento porque el
    // objeto mListener (mirar onAttach) guarda una referencia a la actividad.
    public interface OnLoginInteractionListener {
        // TODO: Update argument type and name
        void onLoginSuccess();
    }
}
