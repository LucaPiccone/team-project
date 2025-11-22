package interface_adapter.loggedInSearchPage;

import api.googlePlacesAPI.PlaceFetcher;
import use_case.loggedInSearchPage.LoggedInSearchPageAutoCompletedInputData;
import use_case.loggedInSearchPage.LoggedInSearchPageInputBoundary;

public class LoggedInSearchPageController {
    private final LoggedInSearchPageInputBoundary loggedInSearchPageInputBoundary;

    public LoggedInSearchPageController(LoggedInSearchPageInputBoundary loggedInSearchPageInputBoundary) {
        this.loggedInSearchPageInputBoundary = loggedInSearchPageInputBoundary;
    }

    public void switchToLoggedInHomePageView() {
        loggedInSearchPageInputBoundary.switchToLoggedInHomePageView();
    }

    public void fetchSuggestions(String query) throws PlaceFetcher.PlaceNotFoundException {
        LoggedInSearchPageAutoCompletedInputData inputData =
                new LoggedInSearchPageAutoCompletedInputData(query);
        loggedInSearchPageInputBoundary.fetchSuggestions(inputData);
    }

    public void clearSuggestions() {

    }
}