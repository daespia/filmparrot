package filmparrot.movil.informatica.filmparrot.logica;

/**
 * Created by Raul on 21/04/2016.
 */
public abstract class Elemento {

    private String titulo;
    private String descripcion;
    private String pais;
    private int imagen;

    public Elemento(String titulo, String descripcion, String pais, int imagen){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pais = pais;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPais() {
        return pais;
    }

    public int getImagen() {
        return imagen;
    }
}
