package filmparrot.movil.informatica.filmparrot.logica;

/**
 * Created by Raul on 21/04/2016.
 */
public class Puntuacion {

    private double valor;
    private Critica critica;
    Usuario usuario;

    public Puntuacion(double valor, Critica critica, Usuario usuario){
        this.valor = valor;
        this.critica = critica;
        this.usuario = usuario;
    }

    public double getValor() {
        return valor;
    }
    public Critica getCritica() {
        return critica;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
