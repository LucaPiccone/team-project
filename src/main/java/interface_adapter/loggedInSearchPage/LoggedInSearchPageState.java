package interface_adapter.loggedInSearchPage;

import entity.placeSuggestions.PlaceSuggestion;

import java.util.ArrayList;
import java.util.List;

public class LoggedInSearchPageState {
    // Current list of autocomplete suggestions
    private List<PlaceSuggestion> suggestions = new ArrayList<>();

    public List<PlaceSuggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<PlaceSuggestion> suggestions) {
        this.suggestions = suggestions;
    }

    public void clearSuggestions() {
        this.suggestions.clear();
    }
}
