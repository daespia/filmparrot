package filmparrot.movil.informatica.filmparrot.logica;

import java.util.Date;
import java.util.List;

public class Pelicula extends Elemento {

    private int duracion;
    private String genero;
    private Date fechaEstreno;
    private String productora;
    private Bso bso;
    private List<Persona> directores;
    private List<Persona> actores;

    public Pelicula(int id, String titulo, String descripcion, String pais, int imagen, int duracion, String genero,
                    Date fechaEstreno, String productora, Bso bso, List<Persona> directores, List<Persona> actores) {
        super(id, titulo, descripcion, pais, imagen);
        this.duracion = duracion;
        this.genero=genero;
        this.fechaEstreno=fechaEstreno;
        this.productora = productora;
        this.bso = bso;
        this.directores = directores;
        this.actores = actores;
        setTipo("PELÍCULA");
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public Date getFechaEstreno() {
        return fechaEstreno;
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
