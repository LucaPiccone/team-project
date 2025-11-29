package interface_adapter.hourly_forecast;

import use_case.hourly_forecast.HourlyForecastInputBoundary;

public class HourlyForecastController {
    private final HourlyForecastInputBoundary hourlyForecastInputBoundary;

    public HourlyForecastController(HourlyForecastInputBoundary hourlyForecastInputBoundary) {
        this.hourlyForecastInputBoundary = hourlyForecastInputBoundary;
    }

    public void switchToWeatherReportView() {
        hourlyForecastInputBoundary.switchToWeatherReportView();

    }
}
