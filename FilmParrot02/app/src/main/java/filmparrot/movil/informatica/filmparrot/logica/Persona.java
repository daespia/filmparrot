package filmparrot.movil.informatica.filmparrot.logica;

import java.util.Date;
import java.util.List;

/**
 * Created by bubu on 21/04/2016.
 */
public class Persona extends Elemento{

    private String apellidos;
    private Date fechaNacimiento;
    private Boolean esDirector;
    private Boolean esActor;
    private List<Elemento> elementos;



    public Persona(int id, String titulo, String descripcion, String pais, int imagen, String apellidos,
                   Date fechaNacimiento, Boolean esDirector, Boolean esActor, List<Elemento>elementos) {
        super(id, titulo, descripcion, pais, imagen);
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.esDirector = esDirector;
        this.esActor = esActor;
        this.elementos=elementos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() { return fechaNacimiento; }

    public Boolean getEsDirector() { return esDirector; }

    public Boolean getEsActor() { return esActor; }

    public List<Elemento> getElementos() { return elementos; }
}
