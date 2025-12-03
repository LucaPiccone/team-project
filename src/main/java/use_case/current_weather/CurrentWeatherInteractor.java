package use_case.current_weather;

import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import api.hourly_forecast_api.HourlyForecastApiDataFetcher;
import api.hourly_forecast_api.HourlyForecastFetcher;
import api.openWeatherApi.OpenWeatherApiDataFetcher;
import api.geocodingapi.CoordinatesFetcher;
import api.openWeatherApi.WeatherDataFetcher;
import data_access.UserDataAccessInterface;
import entity.hourly_forecast_report.HourlyForecastReport;
import entity.weather_report.WeatherReport;
import entity.weather_report.WeatherReportFactory;
import interface_adapter.weather_report_page.WeatherReportPageState;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrentWeatherInteractor implements CurrentWeatherInputBoundary {
    private final UserDataAccessInterface userDataAccessInterface;
    private final CurrentWeatherOutputBoundary userPresenter;

    public CurrentWeatherInteractor(UserDataAccessInterface userDataAccessInterface,
                                    CurrentWeatherOutputBoundary currentWeatherOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.userPresenter = currentWeatherOutputBoundary;
    }

    @Override
    public void switchToLoggedInSearchView() {
        userPresenter.switchToLoggedInSearchView();
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }

    @Override
    public void addToFavourites(CurrentWeatherInputData inputData) {
        final String cityName = inputData.getCityName();
        final List<String> locations = userDataAccessInterface.getLocations();
        final boolean alreadyExists = locations.contains(cityName);

        final CurrentWeatherOutputData outputData = new CurrentWeatherOutputData(cityName);

        if (alreadyExists) {
            userPresenter.addToFavouriteFail(outputData);
        }
        else {
            userDataAccessInterface.addLocation(cityName);
            userPresenter.addToFavouriteSuccess(outputData);
        }
    }

    @Override
    public void resetPopUpMessage() {
        userPresenter.resetPopUpMessage();
    }

    @Override
    public void switchToFavouritesPageView() {
        // All favourite locations of the user
        final List<String> locations = userDataAccessInterface.getLocations();
        final List<WeatherReport> weatherReports = new ArrayList<>();
        final CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
        final WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
        final WeatherReportFactory factory = new WeatherReportFactory(fetcher, coordinatesFetcher);
        for (String location : locations) {
            try {
                WeatherReport weatherReport = factory.create(location);
                weatherReports.add(weatherReport);
            }
            catch (WeatherDataFetcher.CityNotFoundException |
                     CoordinatesFetcher.CityNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        userPresenter.switchToFavouritesPageView(weatherReports);
    }

    @Override
    public void switchToHourlyForecast(String cityName) throws CoordinatesFetcher.CityNotFoundException {
        final CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
        final HashMap<String, Double> coordinates = coordinatesFetcher.getCoordinates(cityName);
        final HourlyForecastFetcher forecastFetcher = new HourlyForecastApiDataFetcher();
        final HourlyForecastReport report = forecastFetcher.getHourlyForecast(coordinates);
        userPresenter.switchToHourlyForecast(report, cityName);
    }

    @Override
    public void switchToCheckOutfitView(WeatherReportPageState state) {
        userPresenter.switchToCheckOutfitView(state);
    }
}
