package filmparrot.movil.informatica.filmparrot.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.auxiliar.Utils;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;


public class ProfileFragment extends Fragment {

    private EditText currentPassword, newPassword, confirmNewPassword;
    private Usuario usuario;
    private Button changePassword;

    public ProfileFragment() { }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getContext());
        usuario = Utils.fachada.getUsuario(sharedPref.getString("sessionActive", null));

        TextView username = (TextView) view.findViewById(R.id.usernameText);
        username.setText(usuario.getNombre());

        currentPassword  = (EditText) view.findViewById(R.id.current_password);
        newPassword  = (EditText) view.findViewById(R.id.new_password);
        confirmNewPassword  = (EditText) view.findViewById(R.id.confirm_new_password);

        changePassword = (Button) view.findViewById(R.id.acceptButton);
        changePassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if(!currentPassword.getText().toString().equals(usuario.getContrasena()) ||
                                ! newPassword.getText().toString().equals(confirmNewPassword.getText().toString())){
                            currentPassword.setError("Las contraseñas deben coincidir.");

                        }else if (newPassword.getText().toString().isEmpty() || newPassword.toString().isEmpty()) {
                            newPassword.setError("La nueva contraseña no puede ser vacía.");

                        } else {
                            usuario.setContrasena(newPassword.getText().toString());
                            Toast.makeText(getActivity().getApplicationContext(), "Has cambiado la contraseña con éxito",
                                    Toast.LENGTH_SHORT).show();

                            newPassword.getText().clear();
                            currentPassword.getText().clear();
                            confirmNewPassword.getText().clear();
                        }
                    }
                }
        );

        return view;

    }


}
