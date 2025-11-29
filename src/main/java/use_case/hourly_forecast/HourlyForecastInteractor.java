package use_case.hourly_forecast;

import api.geocodingapi.CoordinatesFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import api.openWeatherApi.OpenWeatherApiDataFetcher;
import api.openWeatherApi.WeatherDataFetcher;
import entity.weatherReport.WeatherReport;
import entity.weatherReport.WeatherReportFactory;

public class HourlyForecastInteractor implements HourlyForecastInputBoundary {
    HourlyForecastOutputBoundary presenter;

    public HourlyForecastInteractor(HourlyForecastOutputBoundary hourlyForecastOutputBoundary) {
        presenter = hourlyForecastOutputBoundary;
    }

    @Override
    public void switchToWeatherReportView(String cityName) {
        WeatherReport weatherReport;
        CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
        WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
        WeatherReportFactory factory = new WeatherReportFactory(fetcher, coordinatesFetcher);
        try {
            weatherReport = factory.create(cityName);
        } catch (WeatherDataFetcher.CityNotFoundException | CoordinatesFetcher.CityNotFoundException e) {
            presenter.prepareFailGoBack(cityName);
            throw new RuntimeException(e);
        }
        presenter.switchToWeatherReportView(weatherReport);
    }
}
