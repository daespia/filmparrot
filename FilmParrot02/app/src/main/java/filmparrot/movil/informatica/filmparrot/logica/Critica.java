package filmparrot.movil.informatica.filmparrot.logica;

/**
 * Created by Raul on 21/04/2016.
 */
public class Critica {

    private String titulo;
    private String cuerpo;

    public Critica(String titulo, String cuerpo) {
        this.titulo = titulo;
        this.cuerpo = cuerpo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

}
