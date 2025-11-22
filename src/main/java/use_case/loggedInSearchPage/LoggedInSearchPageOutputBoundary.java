package use_case.loggedInSearchPage;

import entity.placeSuggestions.PlaceSuggestion;

import java.util.List;

public interface LoggedInSearchPageOutputBoundary {
    void switchToLoggedInHomePageView();

    void showSuggestionsToUser(List<PlaceSuggestion> suggestions);
}
