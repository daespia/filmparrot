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

    public Persona(int id, String titulo, String descripcion, String pais, int imagen, String apellidos,
                   Date fechaNacimiento, Boolean esDirector, Boolean esActor) {
        super(id, titulo, descripcion, pais, imagen);
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.esDirector = esDirector;
        this.esActor = esActor;

        if(esActor && !esDirector) setTipo("ACTOR");
        else if (esDirector && !esActor) setTipo("DIRECTOR");
        else if (esActor && esDirector) setTipo("ACTOR/DIRECTOR");
    }

    public String getApellidos() {
        return apellidos;
    }

    public Date getFechaNacimiento() { return fechaNacimiento; }

    public Boolean getEsDirector() { return esDirector; }

    public Boolean getEsActor() { return esActor; }

}
