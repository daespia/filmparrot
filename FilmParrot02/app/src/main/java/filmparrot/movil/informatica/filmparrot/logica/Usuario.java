package filmparrot.movil.informatica.filmparrot.logica;

/**
 * Created by Raul on 21/04/2016.
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private boolean esAdministrador;

    public Usuario(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        esAdministrador = false;
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
}
