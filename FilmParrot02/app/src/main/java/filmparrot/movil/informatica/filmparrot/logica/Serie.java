package filmparrot.movil.informatica.filmparrot.logica;

/**
 * Created by usuario on 21/04/2016.
 */
public class Serie extends Elemento {
    private int anioComienzo;
    private int anioFinal;
    private int temporadas;
    private int capitulos;
    private Bso bso;

    public Serie(String titulo, String descripcion, String pais, int imagen,int anioComienzo, int anioFinal, int temporadas,  int capitulos, Bso bso) {
        super(titulo, descripcion, pais, imagen);
        this.anioComienzo=anioComienzo;
        this.anioFinal= anioFinal;
        this.temporadas = temporadas;
        this.capitulos= capitulos;
        this.bso=bso;
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
}
