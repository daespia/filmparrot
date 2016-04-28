package filmparrot.movil.informatica.filmparrot.logica;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Raul on 21/04/2016.
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private boolean esAdministrador;
    private List<Puntuacion> puntuaciones;
    private HashMap<String, List<Elemento>> listas;

    public Usuario(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        esAdministrador = false;
        puntuaciones = new ArrayList<>();
        listas = new HashMap<>();
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

    public boolean getEsAdministrador(){
        return esAdministrador;
    }

    public void anadirPuntuacion(Puntuacion p){
        puntuaciones.add(p);
    }


    public HashMap<String, List<Elemento>> getListas() {
        return listas;
    }

    public void anadirLista(String nombre, List<Elemento> elementos) {
        listas.put(nombre, elementos);
    }

    public void anadirElementoALista(String nombre, Elemento elemento){
        listas.get(nombre).add(elemento);
    }
}
