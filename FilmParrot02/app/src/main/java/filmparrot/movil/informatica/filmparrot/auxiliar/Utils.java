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

        // ---------------- Actores y directores - ID: 1X

        Persona chris = new Persona(11, "Chris Evans", "Modelo y actor de cine estadounidense. Es conocido por su papel en las" +
                " películas 'Los 4 Fantásticos', 'Push' y 'Dime con cuántos', pero ha alcanzado un máximo reconocimiento" +
                " por ser el protagonista de la película basada en el cómic 'Capitán América: El primer Vengador'.",
                "Estados Unidos", R.drawable.cover_chrisevans, "Evans",
                Calendar.getInstance().getTime(), false, true);
        chris.anadirPuntuacion(new Puntuacion(7.9, null, raul));

        Persona spielberg = new Persona(12, "Steven Spielberg", "Uno de los directores más reconocidos y populares de la " +
                "industria cinematográfica mundial. Candidato siete veces a los premios Óscar en la categoría de mejor director, " +
                "habiendo obtenido el premio en dos ocasiones, con 'La lista de Schindler' y 'Salvar al soldado Ryan'.",
                "Estados Unidos", R.drawable.cover_spielberg,
                "Spielberg", Calendar.getInstance().getTime(), true, false);
        spielberg.anadirPuntuacion(new Puntuacion(5.2, null, manu));

        Persona ryan = new Persona(13, "Ryan Reynolds", "Actor canadiense de películas y series televisivas. " +
                "El éxito y el reconocimiento llegó con su participación en la comedia titulada 'Two Guys and a Girl', " +
                "antes de que iniciara su carrera de comediante y actor dramático para la industria de cine de Hollywood. ",
                "Canadá", R.drawable.cover_ryan, "Rodney Reynolds",
                Calendar.getInstance().getTime(), false, true);
        ryan.anadirPuntuacion(new Puntuacion(7.5, null, manu));

        Persona scarlett = new Persona(14, "Scarlett Johansson", "Actriz de cine, modelo y cantante estadounidense. Hizo su debut en el" +
                " cine en 'Un muchacho llamado Norte'. Luego alcanzó la fama con su papel en la película " +
                "'El hombre que susurraba a los caballos' y posteriormente obtuvo la aclamación por parte" +
                " de los críticos de cine debido a su participación en 'Ghost World'.",
                "Estados Unidos", R.drawable.cover_scarlett, "Johansson",
                Calendar.getInstance().getTime(), false, true);
        scarlett.anadirPuntuacion(new Puntuacion(6.5, null, raul));

        Persona riddley = new Persona(15, "Riddley Scott", "Director de cine británico. " +
                "Ha sido nominado en tres ocasiones a los premios Óscar a la mejor dirección. " +
                "En enero de 2003 fue nombrado caballero por la reina Isabel II. Algunas de sus películas " +
                "son consideradas como auténticas obras de culto, especialmente Blade Runner y Alien, " +
                "el octavo pasajero.",
                "Estados Unidos", R.drawable.cover_riddley,
                "Scott Kt", Calendar.getInstance().getTime(), true, false);
        riddley.anadirPuntuacion(new Puntuacion(5.6, null, manu));

        Persona russell = new Persona(16, "Russell Crowe", " Actor de origen neozelandés que ha desarrollado su c" +
                "arrera en su propio país, en Australia y en Estados Unidos, " +
                "donde fue premiado con un Oscar por su interpretación en Gladiator (2000).Nació el 7 de abril " +
                "de 1964 en Wellington, Nueva Zelanda, aunque se crió y pasó la mayor parte de su vida en Australia. ",
                "Nueva Zelanda", R.drawable.cover_ryan, "Ira Crowe",
                Calendar.getInstance().getTime(), false, true);
        russell.anadirPuntuacion(new Puntuacion(6.5, null, dani));
        russell.anadirPuntuacion(new Puntuacion(8.9, null, manu));

        Persona paulrudd = new Persona(17, "Paul Rudd", "Actor y guionista estadounidense. " +
                "Aparece principalmente en comedias, y es conocido por sus papeles en las películas " +
                "'Clueless', 'Wet Hot American Summer', 'Virgen a los 40', 'Antman', etc.",
                "Estados Unidos", R.drawable.cover_paull, "Stephen Rudd",
                Calendar.getInstance().getTime(), false, true);
        paulrudd.anadirPuntuacion(new Puntuacion(8.1, null, raul));

        Persona leonardo = new Persona(18, "Leonardo DiCaprio", "Actor y productor de cine estadounidense. " +
                "Ha recibido numerosos premios, entre ellos: un Oscar al mejor actor por su actuación en " +
                "'El renacido' y tres Globos de Oro al mejor actor.",
                "Estados Unidos", R.drawable.cover_dicaprio, "Wilhelm DiCaprio",
                Calendar.getInstance().getTime(), false, true);
        leonardo.anadirPuntuacion(new Puntuacion(8.4, null, dani));

        Persona kate = new Persona(19, "Kate Winslet", "Actriz británica de cine y también cantante " +
                "ocasional. En 2009 ganó el premio Óscar a la mejor actriz por su papel en 'The Reader'," +
                " convirtiéndose además en la actriz más joven en conseguir más nominaciones en dicho premio. ",
                "Estados Unidos", R.drawable.cover_kate, "Kate Elizabeth Winslet",
                Calendar.getInstance().getTime(), false, true);
        kate.anadirPuntuacion(new Puntuacion(8.1, null, raul));

        Persona samneill = new Persona(111, "Sam Neill", "Sin descripción.",
                "Reino Unido", R.drawable.cover_sam, "John Dermot Neill",
                Calendar.getInstance().getTime(), false, true);
        samneill.anadirPuntuacion(new Puntuacion(4.2, null, manu));

        Persona cameron = new Persona(112, "James Cameron", "Sin descripción.",
                "Canadá", R.drawable.cover_cameron, " Francis Cameron",
                Calendar.getInstance().getTime(), true, false);
        cameron.anadirPuntuacion(new Puntuacion(4.2, null, manu));

        Persona francis = new Persona(113, "Francis Ford Coppola", "Sin descripción.",
                "Estados Unidos", R.drawable.cover_francis, "Ford Coppola",
                Calendar.getInstance().getTime(), true, false);
        francis.anadirPuntuacion(new Puntuacion(9.8, null, dani));

        fachada.anadirPersona(chris);
        fachada.anadirPersona(scarlett);
        fachada.anadirPersona(spielberg);
        fachada.anadirPersona(riddley);
        fachada.anadirPersona(ryan);

        // ---------------- Series - ID: 2X

        Serie juegotronos = new Serie(21, "Juego de Tronos", "La historia se desarrolla en un mundo ficticio de " +
                "carácter medieval donde hay Siete Reinos. Hay tres líneas argumentales " +
                "principales: la crónica de la guerra civil dinástica por el control de " +
                "Poniente entre varias familias nobles que aspiran al Trono de Hierro, la " +
                "creciente amenaza de los Otros, seres desconocidos que viven al otro lado d" +
                "e un inmenso muro de hielo que protege el Norte de Poniente, y el viaje " +
                "de Daenerys Targaryen.", "Estados Unidos", R.drawable.cover_gamethrones,
                2011, 2016, 6, 54, null, null, null);
        juegotronos.anadirPuntuacion(new Puntuacion(9.3, null, manu));

        Serie ministerio = new Serie(22, "El ministerio del tiempo", "El Ministerio del Tiempo es una institución " +
                "gubernamental, autónoma y secreta, que depende directamente de Presidencia de Gobierno. " +
                "Como en los EEUU se guardan los secretos y la llave para un posible ataque nuclear, de " +
                "presidente a presidente, lo mismo pasa con este ministerio español: " +
                "sólo reyes, presidentes y un número muy exclusivo de personas saben de él. ", "España",
                R.drawable.cover_ministerio,
                2015, 2016, 2, 42, null, null, null);
        ministerio.anadirPuntuacion(new Puntuacion(4.8, null, dani));

        Serie friends = new Serie(23, "Friends", "Narra las aventuras y desventuras de seis jóvenes de Nueva York. " +
                "Forman una unida pandilla de amigos que viven en Manhattan y que suelen reunirse en sus a" +
                "partamentos o en su bar habitual, el Central Perk. A pesar de los numerosos cambios que " +
                "se producen en sus vidas, su amistad es inquebrantable en la dura batalla por salir adelante" +
                " en sus periplos profesionales y personales", "Estados Unidos", R.drawable.cover_friends,
                2015, 2016, 2, 42, null, null, null);
        friends.anadirPuntuacion(new Puntuacion(7.8, null, dani));

        fachada.anadirSerie(ministerio);
        fachada.anadirSerie(friends);
        fachada.anadirSerie(juegotronos);

        // ---------------- Bandas sonoras - ID: 3X

        Bso starWars = new Bso(31, "Star Wars","Banda sonora de la reconocida saga de Star Wars" ,"Estados Unidos",
                R.drawable.cover_starwars,"John Williams",2015);
        starWars.anadirPuntuacion(new Puntuacion(8.6, null, raul));
        starWars.anadirPuntuacion(new Puntuacion(6.7, null, manu));
        starWars.anadirPuntuacion(new Puntuacion(5.0, null, dani));

        fachada.anadirBso(starWars);

        // ---------------- Películas - ID: 4X

        ArrayList<Persona> actores41 = new ArrayList<Persona>();
        ArrayList<Persona> directores41 = new ArrayList<Persona>();
        actores41.add(samneill);
        directores41.add(spielberg);

        Pelicula jurassic = new Pelicula(41, "Jurassic Park", "El multimillonario John Hammond consigue hacer realidad " +
                "su sueño de clonar dinosaurios del Jurásico y crear con ellos un parque temático " +
                "en una isla remota. Antes de abrirlo al público, invita a una pareja de eminentes " +
                "científicos y a un matemático para que comprueben la viabilidad del proyecto. ", "Estados Unidos",
                R.drawable.cover_jurassic, 120, "Ciencia ficción",
                Calendar.getInstance().getTime(), "La que sea", null, directores41, actores41);
        jurassic.anadirPuntuacion(new Puntuacion(9.6, null, raul));
        jurassic.anadirPuntuacion(new Puntuacion(8.7, null, manu));
        jurassic.anadirPuntuacion(new Puntuacion(5.9, null, dani));

        ArrayList<Persona> directores42 = new ArrayList<Persona>();
        directores42.add(francis);

        Pelicula padrino = new Pelicula(42, "El padrino", "América, años 40. Don Vito Corleone (Marlon Brando) es el respetado y temido jefe " +
                "de una de las cinco familias de la mafia de Nueva York. Tiene cuatro hijos: Connie (Talia Shire), " +
                "el impulsivo Sonny (James Caan), el pusilánime Fredo (John Cazale) y Michael (Al Pacino), " +
                "que no quiere saber nada de los negocios de su padre. ", "Estados Unidos",
                R.drawable.cover_padrino, 120, "Drama, Mafia",
                Calendar.getInstance().getTime(), "La que sea", null, directores42, null);
        padrino.anadirPuntuacion(new Puntuacion(7.5, null, raul));
        padrino.anadirPuntuacion(new Puntuacion(8.5, null, manu));
        padrino.anadirPuntuacion(new Puntuacion(9.1, null, dani));

        ArrayList<Persona> actores43 = new ArrayList<Persona>();
        ArrayList<Persona> directores43 = new ArrayList<Persona>();
        actores43.add(russell);
        directores43.add(riddley);

        Pelicula gladiator = new Pelicula(43, "Gladiator", "En el año 180, el Imperio Romano domina todo el mundo conocido. " +
                "Tras una gran victoria sobre los bárbaros del norte, el anciano emperador Marco Aurelio " +
                "(Richard Harris) decide transferir el poder a Máximo (Russell Crowe), bravo general de sus " +
                "ejércitos y hombre de inquebrantable lealtad al imperio. Pero su hijo Cómodo (Joaquin Phoenix), " +
                "que aspiraba al trono, no lo acepta y trata de asesinar a Máximo.", "Estados Unidos",
                R.drawable.gladiator_cover, 120, "Aventuras, drama",
                Calendar.getInstance().getTime(), "La que sea", null, directores43, actores43);
        gladiator.anadirPuntuacion(new Puntuacion(7.5, null, raul));
        gladiator.anadirPuntuacion(new Puntuacion(8.5, null, manu));
        gladiator.anadirPuntuacion(new Puntuacion(9.1, null, dani));

        ArrayList<Persona> actores44 = new ArrayList<Persona>();
        actores44.add(paulrudd);

        Pelicula cena = new Pelicula(44, "La cena de los idiotas", "A un grupo de amigos, que se reúne semanalmente para cenar, " +
                "se les ocurre una original y extravagante apuesta: cada uno debe llevar a la cena al tipo más idiota " +
                "que haya sido capaz de encontrar; el que invite al más idiota de todos será el ganador. " +
                "Remake del popular film francés de Francis Veber.", "Estados Unidos",
                R.drawable.cover_cena, 120, "Comedia",
                Calendar.getInstance().getTime(), "La que sea", null, null, actores44);
        cena.anadirPuntuacion(new Puntuacion(7.5, null, raul));
        cena.anadirPuntuacion(new Puntuacion(8.5, null, manu));

        ArrayList<Persona> actores45 = new ArrayList<Persona>();
        actores45.add(ryan);

        Pelicula deadpool = new Pelicula(45, "Deadpool", "Basado en el antihéroe menos convencional de Marvel," +
                " Deadpool narra el origen de un ex-operativo de las fuerzas especiales llamado Wade Wilson," +
                " reconvertido a mercenario, y que tras ser sometido a un cruel experimento adquiere poderes de" +
                " curación rápida.", "Estados Unidos", R.drawable.cover_deadpool, 108, "Acción, superhéroes",
                Calendar.getInstance().getTime(), "Marvel Studios", null, null, actores45);
        deadpool.anadirPuntuacion(new Puntuacion(7.4, null, raul));
        deadpool.anadirPuntuacion(new Puntuacion(9.5, null, manu));

        ArrayList<Persona> actores46 = new ArrayList<Persona>();
        ArrayList<Persona> directores46 = new ArrayList<Persona>();
        actores46.add(leonardo);
        actores46.add(kate);
        directores46.add(cameron);

        Pelicula titanic = new Pelicula(46, "Titanic", "Jack, un joven artista, gana en una partida de cartas " +
                "un pasaje para viajar a América en el Titanic, el transatlántico más grande y seguro jamás construido." +
                " A bordo conoce  Rose, una joven de una buena familia venida a menos.", "Estados Unidos",
                R.drawable.cover_titanic, 120, "Romance",
                Calendar.getInstance().getTime(), "La que sea", null, directores46, actores46);
        titanic.anadirPuntuacion(new Puntuacion(8.6, null, raul));
        titanic.anadirPuntuacion(new Puntuacion(6.7, null, manu));
        titanic.anadirPuntuacion(new Puntuacion(5.0, null, dani));

        fachada.anadirPelicula(deadpool);
        fachada.anadirPelicula(titanic);
        fachada.anadirPelicula(jurassic);
        fachada.anadirPelicula(padrino);
        fachada.anadirPelicula(gladiator);
        fachada.anadirPelicula(cena);

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
