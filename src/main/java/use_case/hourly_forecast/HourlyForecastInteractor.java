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
    public void switchToWeatherReportView() {
        presenter.switchToWeatherReportView();
    }
}
