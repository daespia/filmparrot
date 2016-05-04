package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import filmparrot.movil.informatica.filmparrot.R;
import filmparrot.movil.informatica.filmparrot.logica.Bso;
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

    public static boolean hasPermission(String permission, Activity activity){
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1){
            return activity.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        } else return true;
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

        Pelicula jurassic = new Pelicula(7, "Jurassic Park", "El multimillonario John Hammond consigue hacer realidad " +
                "su sueño de clonar dinosaurios del Jurásico y crear con ellos un parque temático " +
                "en una isla remota. Antes de abrirlo al público, invita a una pareja de eminentes " +
                "científicos y a un matemático para que comprueben la viabilidad del proyecto. ", "Estados Unidos",
                R.drawable.cover_jurassic, 120, "Ciencia ficción",
                Calendar.getInstance().getTime(), "La que sea", null, null, null);
        jurassic.anadirPuntuacion(new Puntuacion(9.6, null, raul));
        jurassic.anadirPuntuacion(new Puntuacion(8.7, null, manu));
        jurassic.anadirPuntuacion(new Puntuacion(5.9, null, dani));

        Pelicula padrino = new Pelicula(8, "El padrino", "América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe " +
                "de una de las cinco familias de la mafia de Nueva York. Tiene cuatro hijos: Connie (Talia Shire), " +
                "el impulsivo Sonny (James Caan), el pusilánime Fredo (John Cazale) y Michael (Al Pacino), " +
                "que no quiere saber nada de los negocios de su padre. ", "Estados Unidos",
                R.drawable.cover_padrino, 120, "Drama | Mafia",
                Calendar.getInstance().getTime(), "La que sea", null, null, null);
        padrino.anadirPuntuacion(new Puntuacion(7.5, null, raul));
        padrino.anadirPuntuacion(new Puntuacion(8.5, null, manu));
        padrino.anadirPuntuacion(new Puntuacion(9.1, null, dani));

        Pelicula gladiator = new Pelicula(9, "Gladiator", "En el año 180, el Imperio Romano domina todo el mundo conocido. " +
                "Tras una gran victoria sobre los bárbaros del norte, el anciano emperador Marco Aurelio " +
                "(Richard Harris) decide transferir el poder a Máximo (Russell Crowe), bravo general de sus " +
                "ejércitos y hombre de inquebrantable lealtad al imperio. Pero su hijo Cómodo (Joaquin Phoenix), " +
                "que aspiraba al trono, no lo acepta y trata de asesinar a Máximo.", "Estados Unidos",
                R.drawable.gladiator_cover, 120, "Drama | Mafia",
                Calendar.getInstance().getTime(), "La que sea", null, null, null);
        gladiator.anadirPuntuacion(new Puntuacion(7.5, null, raul));
        gladiator.anadirPuntuacion(new Puntuacion(8.5, null, manu));
        gladiator.anadirPuntuacion(new Puntuacion(9.1, null, dani));

        Pelicula cena = new Pelicula(10, "La cena de los idiotas", "A un grupo de amigos, que se reúne semanalmente para cenar, " +
                "se les ocurre una original y extravagante apuesta: cada uno debe llevar a la cena al tipo más idiota " +
                "que haya sido capaz de encontrar; el que invite al más idiota de todos será el ganador. " +
                "Remake del popular film francés de Francis Veber.", "Estados Unidos",
                R.drawable.cover_cena, 120, "Humor",
                Calendar.getInstance().getTime(), "La que sea", null, null, null);
        cena.anadirPuntuacion(new Puntuacion(7.5, null, raul));
        cena.anadirPuntuacion(new Puntuacion(8.5, null, manu));

        Serie friends = new Serie(11, "Friends", "Narra las aventuras y desventuras de seis jóvenes de Nueva York. " +
                "Forman una unida pandilla de amigos que viven en Manhattan y que suelen reunirse en sus a" +
                "partamentos o en su bar habitual, el Central Perk. A pesar de los numerosos cambios que " +
                "se producen en sus vidas, su amistad es inquebrantable en la dura batalla por salir adelante" +
                " en sus periplos profesionales y personales", "Estados Unidos", R.drawable.cover_friends,
                2015, 2016, 2, 42, null, null, null);
        friends.anadirPuntuacion(new Puntuacion(7.8, null, dani));

        Persona scarlett = new Persona(12, "Scarlett Johansson", "Scarlett Johansson.",
                "Estados Unidos", R.drawable.cover_scarlett, "Johansson",
                Calendar.getInstance().getTime(), false, true, null);
        scarlett.anadirPuntuacion(new Puntuacion(6.5, null, raul));

        Persona riddley = new Persona(13, "Riddley Scott", "Riddley Scott.",
                "Estados Unidos", R.drawable.cover_riddley,
                "Scott", Calendar.getInstance().getTime(), true, false, null);
        riddley.anadirPuntuacion(new Puntuacion(5.6, null, manu));

        Bso starWars = new Bso(14, "Star Wars","Banda sonora de la reconocida saga de Star Wars" ,"Estados Unidos",
                R.drawable.cover_starwars,"John Williams",2015);
        starWars.anadirPuntuacion(new Puntuacion(8.6, null, raul));
        starWars.anadirPuntuacion(new Puntuacion(6.7, null, manu));
        starWars.anadirPuntuacion(new Puntuacion(5.0, null, dani));

        fachada.anadirPelicula(deadpool);
        fachada.anadirPelicula(titanic);
        fachada.anadirPelicula(jurassic);
        fachada.anadirPelicula(padrino);
        fachada.anadirPelicula(gladiator);
        fachada.anadirPelicula(cena);

        fachada.anadirSerie(ministerio);
        fachada.anadirSerie(friends);
        fachada.anadirSerie(juegotronos);

        fachada.anadirPersona(chris);
        fachada.anadirPersona(scarlett);
        fachada.anadirPersona(spielberg);
        fachada.anadirPersona(riddley);

        fachada.anadirBso(starWars);

        List<Elemento> lista1 = new ArrayList<>();
        lista1.add(deadpool);
        lista1.add(ministerio);
        raul.anadirLista("Mis series y películas preferidas", lista1);
        List<Elemento> lista2 = new ArrayList<>();
        lista2.add(chris);
        manu.anadirLista("Actores que me gustan", lista2);
        List<Elemento> lista3 = new ArrayList<>();
        lista3.add(juegotronos);
        dani.anadirLista("Series que tengo que ver", lista3);

        List<Elemento> lista4 = new ArrayList<>();
        lista4.add(jurassic);
        lista4.add(padrino);
        lista4.add(gladiator);
        lista4.add(titanic);
        fachada.anadirLista("Megaclásicos", lista4);

        List<Elemento> lista5 = new ArrayList<>();
        lista5.add(cena);
        lista5.add(friends);
        fachada.anadirLista("Para reír sin parar", lista5);

        List<Elemento> lista6 = new ArrayList<>();
        lista6.add(riddley);
        lista6.add(spielberg);
        fachada.anadirLista("Directores famosos", lista6);

    }
}
