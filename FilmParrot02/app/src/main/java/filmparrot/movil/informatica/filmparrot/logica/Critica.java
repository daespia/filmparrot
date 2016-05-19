package filmparrot.movil.informatica.filmparrot.logica;

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
