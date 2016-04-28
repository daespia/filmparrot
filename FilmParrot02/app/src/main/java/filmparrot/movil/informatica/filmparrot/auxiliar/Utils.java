package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.logica.Elemento;
import filmparrot.movil.informatica.filmparrot.logica.Fachada;
import filmparrot.movil.informatica.filmparrot.logica.Pelicula;
import filmparrot.movil.informatica.filmparrot.logica.Persona;
import filmparrot.movil.informatica.filmparrot.logica.Puntuacion;
import filmparrot.movil.informatica.filmparrot.logica.Serie;
import filmparrot.movil.informatica.filmparrot.logica.Usuario;

public class Utils {

    public static Fachada fachada;

    static{
        fachada = new Fachada();
        inicializarFachada();
    }

    public static int getProgressiveColor(double points, Context context){
        if(points < 5.0) return ContextCompat.getColor(context, R.color.colorLess5);
        else if (points >= 5.0 && points <= 7.5) return ContextCompat.getColor(context, R.color.colorBetween5);
        else if (points > 7.5) return ContextCompat.getColor(context, R.color.colorMore7_5);
        return 0;
    }

    private static void inicializarFachada(){

        Usuario raul = new Usuario("raulher", "1234");
        Usuario manu = new Usuario("manugar", "1234");
        manu.setEsAdministrador(true);
        Usuario dani = new Usuario("daniesp", "1234");

        fachada.anadirUsuario(raul);
        fachada.anadirUsuario(manu);
        fachada.anadirUsuario(dani);

        Pelicula deadpool = new Pelicula(1, "Deadpool", "Basado en el antihéroe menos convencional de Marvel," +
                " Deadpool narra el origen de un ex-operativo de las fuerzas especiales llamado Wade Wilson," +
                " reconvertido a mercenario, y que tras ser sometido a un cruel experimento adquiere poderes de" +
                " curación rápida.", "Estados Unidos", R.drawable.cover_deadpool, 108, "Acción",
                Calendar.getInstance().getTime(), "Marvel Studios", null, null, null);
        deadpool.anadirPuntuacion(new Puntuacion(7.4, null, raul));
        deadpool.anadirPuntuacion(new Puntuacion(9.5, null, manu));

        Pelicula titanic = new Pelicula(2, "Titanic", "Jack, un joven artista, gana en una partida de cartas " +
                "un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido." +
                " A bordo conoce  Rose, una joven de una buena familia venida a menos.", "Estados Unidos",
                R.drawable.cover_titanic, 120, "Romance",
                Calendar.getInstance().getTime(), "La que sea", null, null, null);
        titanic.anadirPuntuacion(new Puntuacion(8.6, null, raul));
        titanic.anadirPuntuacion(new Puntuacion(6.7, null, manu));
        titanic.anadirPuntuacion(new Puntuacion(5.0, null, dani));

        Persona chris = new Persona(3, "Chris Evans", "Modelo y actor de cine estadounidense. Es conocido por su papel en las" +
                " películas 'Los 4 Fantásticos', 'Push' y 'Dime con cuántos', pero ha alcanzado un máximo reconocimiento" +
                " por ser el protagonista de la película basada en el cómic 'Capitán América: El primer Vengador'.",
                "Estados Unidos", R.drawable.cover_chrisevans, "Evans",
                Calendar.getInstance().getTime(), false, true, null);
        chris.anadirPuntuacion(new Puntuacion(7.9, null, raul));

        Persona spielberg = new Persona(4, "Steven Spielberg", "Uno de los directores más reconocidos y populares de la " +
                "industria cinematográfica mundial. Candidato siete veces a los premios Óscar en la categoría de mejor director, " +
                "habiendo obtenido el premio en dos ocasiones, con 'La lista de Schindler' y 'Salvar al soldado Ryan'.",
                "Estados Unidos", R.drawable.cover_spielberg,
                "Spielberg", Calendar.getInstance().getTime(), true, false, null);
        spielberg.anadirPuntuacion(new Puntuacion(5.2, null, manu));

        Serie juegotronos = new Serie(5, "Juego de Tronos", "Juego de Tronos.", "Estados Unidos", R.drawable.cover_gamethrones,
                2011, 2016, 6, 54, null, null, null);
        juegotronos.anadirPuntuacion(new Puntuacion(9.3, null, manu));

        Serie ministerio = new Serie(6, "El ministerio del tiempo", "El ministerio del tiempo.", "España", R.drawable.cover_ministerio,
                2015, 2016, 2, 42, null, null, null);
        ministerio.anadirPuntuacion(new Puntuacion(4.8, null, dani));

        fachada.anadirPelicula(deadpool);
        fachada.anadirPelicula(titanic);
        fachada.anadirSerie(ministerio);
        fachada.anadirSerie(juegotronos);
        fachada.anadirPersona(chris);
        fachada.anadirPersona(spielberg);

        List<Elemento> lista1 = new ArrayList<>();
        lista1.add(deadpool);
        lista1.add(ministerio);
        raul.anadirLista("Mis series y películas preferidas", lista1);

        List<Elemento> lista2 = new ArrayList<>();
        lista2.add(chris);
        raul.anadirLista("Actores que me gustan", lista2);

        List<Elemento> lista3 = new ArrayList<>();
        lista3.add(juegotronos);
        raul.anadirLista("Series que tengo que ver", lista3);
    }
}
