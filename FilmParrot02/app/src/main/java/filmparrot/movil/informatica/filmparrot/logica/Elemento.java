package filmparrot.movil.informatica.filmparrot.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raul on 21/04/2016.
 */
public abstract class Elemento {

    private String titulo;
    private String descripcion;
    private String pais;
    private int imagen;
    private List<Puntuacion> puntuaciones;

    public Elemento(String titulo, String descripcion, String pais, int imagen){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.pais = pais;
        this.imagen = imagen;
        this.puntuaciones = new ArrayList<Puntuacion>();
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
    }

    public boolean eliminarPuntuacion(Puntuacion p){
        return puntuaciones.remove(p);
    }

    public double getMedia(){

        double media = 0.0;

        if (puntuaciones.isEmpty()){
            for(Puntuacion p: puntuaciones){
                media = media + p.getValor();
            }
        }

        return media;
    }
}
