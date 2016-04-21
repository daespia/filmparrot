package filmparrot.movil.informatica.filmparrot.logica;

import android.renderscript.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 21/04/2016.
 */
public class Fachada {

    List<Usuario> usuarios;
    List<Pelicula> peliculas;
    List<Serie> series;
    List<Bso> bsos;
    List<Persona> personas;

    public Fachada(List<Usuario> usuarios, List<Pelicula> peliculas, List<Serie> series, List<Persona> personas, List<Bso> bsos) {
        this.usuarios = usuarios;
        this.peliculas = peliculas;
        this.series = series;
        this.personas = personas;
        this.bsos = bsos;
    }

    public Fachada(){
        this.usuarios = new ArrayList<Usuario>();
        this.peliculas = new ArrayList<Pelicula>();
        this.series = new ArrayList<Serie>();
        this.personas =  new ArrayList<Persona>();
        this.bsos = new ArrayList<Bso>();
    }

    public List<Pelicula> getPeliculas() {
        return peliculas;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public List<Bso> getBsos() {
        return bsos;
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void anadirUsuario(Usuario u){
        usuarios.add(u);
    }

    public void anadirPelicula(Pelicula p){
        peliculas.add(p);
    }

    public void anadirSerie(Serie s){
        series.add(s);
    }

    public void anadirPersona(Persona p){
        personas.add(p);
    }

    public void anadirBso(Bso b){ bsos.add(b); }

    public List<Elemento> getElementoPorNombre(String cadena) {

        List<Elemento> resultados = new ArrayList<Elemento>();

        for (Pelicula p : peliculas) if (p.getTitulo().contains(cadena)) resultados.add(p);
        for (Serie s : series) if (s.getTitulo().contains(cadena)) resultados.add(s);
        for (Bso b : bsos) if (b.getTitulo().contains(cadena)) resultados.add(b);
        for (Persona p : personas) if (p.getTitulo().contains(cadena)) resultados.add(p);

        return resultados;
    }

    public boolean comprobarUsuario(String usuario, String contrasena){
        boolean existe = false;
        for(Usuario u: usuarios) if(u.getNombre().equals(usuario) && u.getContrasena().equals(contrasena)) existe = true;
        return existe;
    }
}
