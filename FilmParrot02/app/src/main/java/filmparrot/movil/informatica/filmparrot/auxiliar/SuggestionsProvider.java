package filmparrot.movil.informatica.filmparrot.auxiliar;

import android.content.SearchRecentSuggestionsProvider;

public class SuggestionsProvider extends SearchRecentSuggestionsProvider {

    public final static String AUTHORITY = "filmparrot.movil.informatica.filmparrot.auxiliar.SuggestionsProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SuggestionsProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
