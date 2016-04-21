package filmparrot.movil.informatica.filmparrot.logica;

import java.util.List;

/**
 * Created by Manu on 21/04/2016.
 */
public class Serie extends Elemento {

    private int anioComienzo;
    private int anioFinal;
    private int temporadas;
    private int capitulos;
    private Bso bso;
    private List<Persona> directores;
    private List<Persona> actores;

    public Serie(String titulo, String descripcion, String pais, int imagen,int anioComienzo,
                 int anioFinal, int temporadas,  int capitulos, Bso bso, List<Persona> directores, List<Persona> actores) {
        super(titulo, descripcion, pais, imagen);
        this.anioComienzo=anioComienzo;
        this.anioFinal= anioFinal;
        this.temporadas = temporadas;
        this.capitulos= capitulos;
        this.bso=bso;
        this.directores = directores;
        this.actores = actores;
    }

    public int getAnioComienzo() {
        return anioComienzo;
    }

    public int getAnioFinal() {
        return anioFinal;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public int getCapitulos() {
        return capitulos;
    }

    public Bso getBso() {
        return bso;
    }

    public List<Persona> getDirectores() { return directores; }

    public List<Persona> getActores() { return actores; }
}
