package filmparrot.movil.informatica.filmparrot.logica;

import java.util.Date;

/**
 * Created by bubu on 21/04/2016.
 */
public class Persona extends Elemento{

    private String apellidos;
    private Date fechaNacimiento;
    private Boolean esDirector;
    private Boolean esActor;

    public Persona(String titulo, String descripcion, String pais, int imagen, String apellidos,
                   Date fechaNacimiento, Boolean esDirector,Boolean esActor) {
        super(titulo, descripcion, pais, imagen);
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.esDirector = esDirector;
        this.esActor = esActor;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() { return fechaNacimiento; }

    public Boolean getEsDirector() { return esDirector; }

    public Boolean getEsActor() { return esActor; }
}
