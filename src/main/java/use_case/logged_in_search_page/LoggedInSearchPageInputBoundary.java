package use_case.logged_in_search_page;

import api.googlePlacesAPI.PlaceFetcher;

public interface LoggedInSearchPageInputBoundary {
    void switchToLoggedInHomePageView();

    void fetchSuggestions(String query) throws PlaceFetcher.PlaceNotFoundException;

    void execute(String query);

    void resetPopUpMessage();
}
