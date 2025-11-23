package use_case.loggedInSearchPage;

import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import entity.placeSuggestions.PlaceSuggestion;
import entity.weatherReport.WeatherReport;
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

    @Override
    public void execute(String query) {
        WeatherReport weatherReport;
        //1. Call the api to change the location (query) to longitude and latitude.

        //2. Call the api to change the get a weather report using the longitude and latitude

        //3. send the weather report to the loggedinSearchPagePresenter with this below.
            // userPresenter.switchToWeatherReportView(weatherReport)
    }
}