package filmparrot.movil.informatica.filmparrot;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
<<<<<<< Updated upstream
import android.widget.RadioGroup;
=======
>>>>>>> Stashed changes
import android.widget.Spinner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ElementFormFragment extends Fragment {

    Spinner spinner;
    private Connection conexionMySQL;

    public ElementFormFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_element_form, container, false);

        spinner = (Spinner) view.findViewById(R.id.elementType);
        ArrayAdapter<CharSequence> array_adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.elementType_array, R.layout.spinner_item);
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(array_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = spinner.getSelectedItem().toString();
                switch (selectedItem) {
                    case "Película":
                        getChildFragmentManager().beginTransaction().replace(R.id.spec_element_content,
                                new FilmFormFragment()).commit();
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


       /* conectarBDMySQL("root","fp3b*","193.146.250.87","3306","filmparrot");
        Statement stmt = null;
        try {
            stmt = conexionMySQL.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM elemento");

            if(rs.next()){
                EditText text = (EditText) view.findViewById(R.id.descriptionText);
                text.setText((String) rs.getString(1));
            }

<<<<<<< Updated upstream
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
=======
        conectarBDMySQL("invitado","fp3b*","filmparrot.unirioja.es","3306","filmparrot");
        Statement stmt = null;
        try {
            stmt = conexionMySQL.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM elemento");

            if(rs.next()){
                EditText text = (EditText) view.findViewById(R.id.descriptionText);
                text.setText(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

>>>>>>> Stashed changes
        return view;
    }



    public void conectarBDMySQL (String usuario, String contrasena,
                                 String ip, String puerto, String catalogo)
    {
        if (conexionMySQL == null)
        {
            String urlConexionMySQL = "";
            if (catalogo != "")
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" +	puerto + "/" + catalogo;
            else
                urlConexionMySQL = "jdbc:mysql://" + ip + ":" + puerto;
            if (usuario != "" & contrasena != "" & ip != "" & puerto != "")
            {
                try
                {
                    Class.forName("com.mysql.jdbc.Driver");
                    conexionMySQL =	DriverManager.getConnection(urlConexionMySQL,
                            usuario, contrasena);
                }
                catch (ClassNotFoundException e)
                {
                    e.getMessage();
                }
                catch (SQLException e)
                {
                    e.getMessage();
                }
            }
        }
    }


}
