package use_case.logged_in_home_page;

import api.geocodingapi.CoordinatesFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import api.openWeatherApi.OpenWeatherApiDataFetcher;
import api.openWeatherApi.WeatherDataFetcher;
import data_access.UserDataAccessInterface;
import entity.weather_report.WeatherReport;
import entity.weather_report.WeatherReportFactory;

import java.util.ArrayList;
import java.util.List;

public class LoggedInHomePageInteractor implements LoggedInHomePageInputBoundary {
    private final UserDataAccessInterface userDataAccessInterface;
    private final LoggedInHomePageOutputBoundary userPresenter;

    public LoggedInHomePageInteractor(UserDataAccessInterface userDataAccessInterface,
                                      LoggedInHomePageOutputBoundary loggedInHomePageOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = loggedInHomePageOutputBoundary;
    }

    @Override
    public void switchToLoggedInSearchView() {
        userPresenter.switchToLoggedInSearchView();
    }

    @Override
    public void switchToLoggedInFavouritesView() {
        // All favourite locations of the user
        final List<String> locations = userDataAccessInterface.getLocations();
        final List<WeatherReport> weatherReports = new ArrayList<>();
        final CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
        final WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
        final WeatherReportFactory factory = new WeatherReportFactory(fetcher, coordinatesFetcher);
        for (String location : locations) {
            try {
                final WeatherReport weatherReport = factory.create(location);
                weatherReports.add(weatherReport);
            }
            catch (WeatherDataFetcher.CityNotFoundException |
                     CoordinatesFetcher.CityNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        userPresenter.switchToLoggedInFavouritesView(weatherReports);
    }

    @Override
    public void switchToSettings(String username) {
        userPresenter.switchToSettings(username);
    }
}
