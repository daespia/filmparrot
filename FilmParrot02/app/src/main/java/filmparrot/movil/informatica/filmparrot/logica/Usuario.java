package filmparrot.movil.informatica.filmparrot.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 21/04/2016.
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private boolean esAdministrador;
    private List<Puntuacion> puntuaciones;

    public Usuario(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        esAdministrador = false;
        puntuaciones = new ArrayList<Puntuacion>();
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEsAdministrador(boolean esAdministrador){
        this.esAdministrador = esAdministrador;
    }

    public void anadirPuntuacion(Puntuacion p){
        puntuaciones.add(p);
    }
}
