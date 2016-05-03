package filmparrot.movil.informatica.filmparrot.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 21/04/2016.
 */
public abstract class Elemento {

    private int id;
    private String titulo;
    private String descripcion;
    private String pais;
    private int imagen;
    private List<Puntuacion> puntuaciones;
    private String tipo;

    Elemento(int id, String titulo, String descripcion, String pais, int imagen){
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pais = pais;
        this.imagen = imagen;
        this.puntuaciones = new ArrayList<>();
    }

    public int getId() {
        return id;
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

    public List<Puntuacion> getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(List<Puntuacion> puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public void anadirPuntuacion(Puntuacion p){
        puntuaciones.add(p);
        p.getUsuario().anadirPuntuacion(p);
        p.setElemento(this);
    }

    public boolean eliminarPuntuacion(Puntuacion p){
        return puntuaciones.remove(p);
    }

    public double getMedia() {
        double media = 0.0;
        for (Puntuacion p : puntuaciones) media = media + p.getValor();
        media = Math.round(media / puntuaciones.size() * 10d) / 10d;
        return media;
    }

    public int getNumCriticas(){
        int criticas = 0;
        for(Puntuacion p: puntuaciones){
            if(p.getCritica() != null) criticas++;
        }
        return criticas;
    }

    public String getTipo(){
        return tipo;
    }

    void setTipo(String tipo){
        this.tipo = tipo;
    }
}
