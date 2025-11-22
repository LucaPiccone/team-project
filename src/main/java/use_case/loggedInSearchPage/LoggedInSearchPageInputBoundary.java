package use_case.loggedInSearchPage;

import api.googlePlacesAPI.PlaceFetcher;

public interface LoggedInSearchPageInputBoundary {
    void switchToLoggedInHomePageView();

    void fetchSuggestions(LoggedInSearchPageAutoCompletedInputData inputData) throws PlaceFetcher.PlaceNotFoundException;
}
