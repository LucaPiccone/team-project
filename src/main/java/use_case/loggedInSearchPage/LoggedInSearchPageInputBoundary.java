package use_case.loggedInSearchPage;

import api.googlePlacesAPI.PlaceFetcher;

public interface LoggedInSearchPageInputBoundary {
    void switchToLoggedInHomePageView();

    void fetchSuggestions(String query) throws PlaceFetcher.PlaceNotFoundException;
}
