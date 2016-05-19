package filmparrot.movil.informatica.filmparrot.logica;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Fachada {

    private List<Usuario> usuarios;
    private List<Pelicula> peliculas;
    private List<Serie> series;
    private List<Bso> bsos;
    private List<Persona> personas;
    private HashMap<String, List<Elemento>> listas;

    public Fachada(List<Usuario> usuarios, List<Pelicula> peliculas, List<Serie> series, List<Persona> personas, List<Bso> bsos) {
        this.usuarios = usuarios;
        this.peliculas = peliculas;
        this.series = series;
        this.personas = personas;
        this.bsos = bsos;
        listas = new HashMap<>();
    }

    public Fachada(){
        this.usuarios = new ArrayList<>();
        this.peliculas = new ArrayList<>();
        this.series = new ArrayList<>();
        this.personas =  new ArrayList<>();
        this.bsos = new ArrayList<>();
        listas = new HashMap<>();
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

        List<Elemento> resultados = new ArrayList<>();
        cadena = cadena.toLowerCase();

        for (Pelicula p : peliculas) if (p.getTitulo().toLowerCase().contains(cadena)) resultados.add(p);
        for (Serie s : series) if (s.getTitulo().toLowerCase().contains(cadena)) resultados.add(s);
        for (Bso b : bsos) if (b.getTitulo().toLowerCase().contains(cadena)) resultados.add(b);
        for (Persona p : personas) if (p.getTitulo().toLowerCase().contains(cadena)) resultados.add(p);

        return resultados;
    }

    public Elemento getElementoPorId(int id) {

        for (Pelicula p : peliculas) if (p.getId()==id) return p;
        for (Serie s : series) if (s.getId()==id) return s;
        for (Bso b : bsos) if (b.getId()==id) return b;
        for (Persona p : personas) if (p.getId()==id) return p;

        return null;
    }

    public boolean comprobarUsuario(String usuario, String contrasena){
        boolean existe = false;
        for(Usuario u: usuarios) if(u.getNombre().equalsIgnoreCase(usuario) && u.getContrasena().equals(contrasena)) existe = true;
        return existe;
    }

    public Usuario getUsuario(String usuario){
        for(Usuario u: usuarios) if(u.getNombre().equalsIgnoreCase(usuario)) return u;
        return null;
    }

    public HashMap<String, List<Elemento>> getListas() {
        return listas;
    }

    public void anadirLista(String nombre, List<Elemento> elementos) {
        listas.put(nombre, elementos);
    }

    public List<Elemento> getEstrenos(){

        List<Elemento> estrenos = new ArrayList<>();

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_MONTH, -1);
        Date lastWeek = cal.getTime();

        for(Pelicula p: peliculas) if(p.getFechaEstreno().after(lastWeek)) estrenos.add(p);
       ///for(Serie s: series) if(s.get).after(lastWeek)) estrenos.add(p);

        return estrenos;
    }
}
