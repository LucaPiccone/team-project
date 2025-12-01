package use_case.logged_in_favourites_page;

import api.geocodingapi.CoordinatesFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import api.openWeatherApi.OpenWeatherApiDataFetcher;
import api.openWeatherApi.WeatherDataFetcher;
import entity.weather_report.WeatherReport;
import entity.weather_report.WeatherReportFactory;

public class LoggedInFavouritesPageInteractor implements LoggedInFavouritesPageInputBoundary {
    private final LoggedInFavouritesPageOutputBoundary userPresenter;

    public LoggedInFavouritesPageInteractor(LoggedInFavouritesPageOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }

    @Override
    public void execute(String location) {
        final WeatherReport weatherReport;
        final CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
        final WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
        final WeatherReportFactory factory = new WeatherReportFactory(fetcher, coordinatesFetcher);
        try {
            weatherReport = factory.create(location);
        }
        catch (WeatherDataFetcher.CityNotFoundException | CoordinatesFetcher.CityNotFoundException e) {
            throw new RuntimeException(e);
        }
        userPresenter.switchToWeatherReportView(weatherReport);
    }
}
