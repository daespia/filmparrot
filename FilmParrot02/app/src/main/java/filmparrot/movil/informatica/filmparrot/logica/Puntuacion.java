package filmparrot.movil.informatica.filmparrot.logica;

/**
 * Created by Raul on 21/04/2016.
 */
public class Puntuacion {

    private double valor;
    private Critica critica;
    private Usuario usuario;
    private Elemento elemento;

    public Puntuacion(double valor, Critica critica, Usuario usuario){
        this.valor = valor;
        this.critica = critica;
        this.usuario = usuario;
    }

    public void setElemento(Elemento elemento){
        this.elemento = elemento;
    }

    public Elemento getElemento(){
        return elemento;
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
