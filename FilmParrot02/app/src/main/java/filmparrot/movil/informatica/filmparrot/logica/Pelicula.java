package filmparrot.movil.informatica.filmparrot.logica;

import java.util.List;

/**
 * Created by Manu on 21/04/2016.
 */
public class Pelicula extends Elemento {

    private int duracion;
    private String genero;
    private int anio;
    private String productora;
    private Bso bso;
    private List<Persona> directores;
    private List<Persona> actores;

    public Pelicula(String titulo, String descripcion, String pais, int imagen, int duracion,
                    String genero, int anio, String productora, Bso bso, List<Persona> directores, List<Persona> actores) {
        super(titulo, descripcion, pais, imagen);
        this.duracion = duracion;
        this.genero=genero;
        this.anio=anio;
        this.productora = productora;
        this.bso = bso;
        this.directores = directores;
        this.actores = actores;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getAnio() {
        return anio;
    }

    public String getProductora() {
        return productora;
    }

    public Bso getBso() {
        return bso;
    }

    public List<Persona> getDirectores() { return directores; }

    public List<Persona> getActores() { return actores; }
}
