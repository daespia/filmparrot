package filmparrot.movil.informatica.filmparrot.logica;

public class Bso extends Elemento {

    private String compositor;
    private int anio;

    public Bso(int id, String titulo, String descripcion, String pais, int imagen, String compositor, int anio){
        super(id, titulo, descripcion, pais, imagen);
        this.compositor = compositor;
        this.anio = anio;
        setTipo("BSO");
    }

    public String getCompositor() {
        return compositor;
    }

    public int getAnio() { return anio; }
}
