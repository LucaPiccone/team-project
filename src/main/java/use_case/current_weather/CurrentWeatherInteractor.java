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
    private final CoordinatesFetcher coordinatesFetcher;
    private final WeatherDataFetcher weatherDataFetcher;
    private final HourlyForecastFetcher hourlyForecastFetcher;
    private final CurrentWeatherOutputBoundary userPresenter;

    public CurrentWeatherInteractor(UserDataAccessInterface userDataAccessInterface, CoordinatesFetcher coordinatesFetcher, WeatherDataFetcher weatherDataFetcher, HourlyForecastFetcher hourlyForecastFetcher,
                                    CurrentWeatherOutputBoundary currentWeatherOutputBoundary) {
        this.userDataAccessInterface = userDataAccessInterface;
        this.coordinatesFetcher = coordinatesFetcher;
        this.weatherDataFetcher = weatherDataFetcher;
        this.hourlyForecastFetcher = hourlyForecastFetcher;
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
        final CurrentWeatherOutputData outputData = new CurrentWeatherOutputData(inputData.getCityName());
        final List<String> locations = userDataAccessInterface.getLocations();
        if (locations.contains(inputData.getCityName())) {
            userPresenter.addToFavouriteFail(outputData);
        }
        else {
            userDataAccessInterface.addLocation(inputData.getCityName());
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
        final List<WeatherReport> weatherReports = new ArrayList<>();
        final WeatherReportFactory factory = new WeatherReportFactory(weatherDataFetcher, coordinatesFetcher);
        final List<String> locations = userDataAccessInterface.getLocations();
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
        final HashMap<String, Double> coordinates = coordinatesFetcher.getCoordinates(cityName);
        final HourlyForecastReport report = hourlyForecastFetcher.getHourlyForecast(coordinates);
        userPresenter.switchToHourlyForecast(report, cityName);
        // add alternative use case.
    }

    @Override
    public void switchToCheckOutfitView(WeatherReportPageState state) {
        userPresenter.switchToCheckOutfitView(state);
    }
}
