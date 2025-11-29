package use_case.hourly_forecast;

import entity.weatherReport.WeatherReport;
import view.WeatherReportView;

public interface HourlyForecastOutputBoundary {

    void switchToWeatherReportView();

    void prepareFailGoBack(String cityName);
}
