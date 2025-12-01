package use_case.current_weather;

import entity.hourly_forecast_report.HourlyForecastReport;
import entity.weather_report.WeatherReport;
import interface_adapter.weather_report_page.WeatherReportPageState;

import java.util.List;

public interface CurrentWeatherOutputBoundary {
    void switchToLoggedInSearchView();

    void switchToLoggedInHomePageView();

    void switchToCheckOutfitView(WeatherReportPageState state);

    void addToFavouriteSuccess(CurrentWeatherOutputData outputData);

    void addToFavouriteFail(CurrentWeatherOutputData outputData);

    void resetPopUpMessage();

     void switchToFavouritesPageView(List<WeatherReport> weatherReports);

     void switchToHourlyForecast(HourlyForecastReport report, String cityName);
}
