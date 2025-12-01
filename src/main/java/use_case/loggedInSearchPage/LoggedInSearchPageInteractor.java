package use_case.loggedInSearchPage;

import api.geocodingapi.CoordinatesFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import api.openWeatherApi.OpenWeatherApiDataFetcher;
import api.openWeatherApi.WeatherDataFetcher;
import entity.placeSuggestions.PlaceSuggestion;
import entity.weatherReport.WeatherReport;
import entity.weatherReport.WeatherReportFactory;

import java.util.List;

public class LoggedInSearchPageInteractor implements LoggedInSearchPageInputBoundary {
    private final LoggedInSearchPageOutputBoundary userPresenter;
    private final GooglePlacesFetcher fetcher;

    public LoggedInSearchPageInteractor(LoggedInSearchPageOutputBoundary loggedInSearchPageOutputBoundary,
                                        GooglePlacesFetcher fetcher) {
        userPresenter = loggedInSearchPageOutputBoundary;
        this.fetcher = fetcher;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }

    @Override
    public void fetchSuggestions(String query) throws PlaceFetcher.PlaceNotFoundException {
        final List<PlaceSuggestion> suggestions = fetcher.getPlace(query);
        userPresenter.showSuggestionsToUser(suggestions);
    }

    @Override
    public void execute(String query) {
        final WeatherReport weatherReport;
        final CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
        final WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
        final WeatherReportFactory factory = new WeatherReportFactory(fetcher, coordinatesFetcher);
        try {
            weatherReport = factory.create(query);
        }
        catch (WeatherDataFetcher.CityNotFoundException | CoordinatesFetcher.CityNotFoundException e) {
            userPresenter.prepareFailToExecute(query);
            throw new RuntimeException(e);
        }
        userPresenter.switchToWeatherReportView(weatherReport);
    }

    public void resetPopUpMessage() {
        userPresenter.resetPopUpMessage();
    }
}