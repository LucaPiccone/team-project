package use_case.loggedInSearchPage;

import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import entity.placeSuggestions.PlaceSuggestion;
import use_case.loggedInHomePage.LoggedInHomePageInputBoundary;
import use_case.loggedInHomePage.LoggedInHomePageOutputBoundary;

import java.util.List;

public class LoggedInSearchPageInteractor implements LoggedInSearchPageInputBoundary {
    private final LoggedInSearchPageOutputBoundary userPresenter;
    private final GooglePlacesFetcher fetcher;

    public LoggedInSearchPageInteractor(LoggedInSearchPageOutputBoundary loggedInSearchPageOutputBoundary, GooglePlacesFetcher fetcher) {
        userPresenter = loggedInSearchPageOutputBoundary;
        this.fetcher = fetcher;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }

    @Override
    public void fetchSuggestions(String query) throws PlaceFetcher.PlaceNotFoundException {
        List<PlaceSuggestion> suggestions = fetcher.getPlace(query);
        userPresenter.showSuggestionsToUser(suggestions);
    }
}