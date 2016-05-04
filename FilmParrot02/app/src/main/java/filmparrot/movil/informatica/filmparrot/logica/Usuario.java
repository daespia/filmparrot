package filmparrot.movil.informatica.filmparrot.logica;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Raul on 21/04/2016.
 */
public class Usuario {

    private String nombre;
    private String contrasena;
    private boolean esAdministrador;
    private List<Puntuacion> puntuaciones;
    private HashMap<String, List<Elemento>> listas;
    private List<Double> historiaMedias;

    public Usuario(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        esAdministrador = false;
        puntuaciones = new ArrayList<>();
        listas = new HashMap<>();
        historiaMedias = new ArrayList<>();
        historiaMedias.add(0.0);
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEsAdministrador(boolean esAdministrador){
        this.esAdministrador = esAdministrador;
    }

    public boolean getEsAdministrador(){
        return esAdministrador;
    }

    public void anadirPuntuacion(Puntuacion p){
        puntuaciones.add(p);
    }

    public List<Puntuacion> getPuntuaciones(){
        return puntuaciones;
    }

    public HashMap<String, List<Elemento>> getListas() {
        return listas;
    }

    public void anadirLista(String nombre, List<Elemento> elementos) {
        listas.put(nombre, elementos);
    }

    public void anadirElementoALista(String nombre, Elemento elemento){
        listas.get(nombre).add(elemento);
    }

    public double getMediaPuntuaciones(){
        double media = 0.0;
        for (Puntuacion p : puntuaciones) media = media + p.getValor();
        media = Math.round(media / puntuaciones.size() * 10d) / 10d;
        return media;
    }

    public int getNumCriticas(){
        int num = 0;
        for (Puntuacion p : puntuaciones) if(p.getCritica() != null) num++;
        return num;
    }

    public HashMap<String, Integer> getVotacionesPorTipo(){

        HashMap<String, Integer> votosPorTipo = new HashMap<>();
        int pel = 0, ser = 0, bso = 0, dir = 0, act = 0;

        for(Puntuacion p: puntuaciones){
            Elemento e = p.getElemento();

            if(e instanceof Pelicula){
                pel++; votosPorTipo.put("Películas", pel);
            }
            if(e instanceof Serie){
                ser++; votosPorTipo.put("Series", ser);
            }
            if(e instanceof Bso){
                bso++; votosPorTipo.put("BSOs", bso);
            }
            if(e instanceof Persona && ((Persona)e).getEsActor()){
                act++; votosPorTipo.put("Actores", act);
            }
            if(e instanceof Persona && ((Persona)e).getEsDirector()){  votosPorTipo.put("Directores", dir++);}
        }

        return votosPorTipo;
    }

    public HashMap<String, Integer> getVotacionesPorPaises(){

        HashMap<String, Integer> votosPorPaises = new HashMap<>();

        for(Puntuacion p: puntuaciones){
            Elemento e = p.getElemento();

            if(!votosPorPaises.containsKey(e.getPais())){
                votosPorPaises.put(e.getPais(), 1);

            } else{
                votosPorPaises.put(e.getPais(), votosPorPaises.get(e.getPais()) + 1);
            }
        }

        return votosPorPaises;
    }

    public void addMedia(double media){
        historiaMedias.add(media);
    }

    public List<Double> getHistoriaMedias(){
        return historiaMedias;
    }
}
