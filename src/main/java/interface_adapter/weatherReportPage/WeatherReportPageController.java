package interface_adapter.weatherReportPage;

import use_case.currentWeather.CurrentWeatherInputBoundary;

public class WeatherReportPageController {
    private WeatherReportPageViewModel weatherReportPageViewModel;
    private currentWeatherInputBoundary currentWeatherInputBoundary;

    public WeatherReportPageController(CurrentWeatherInputBoundary currentWeatherInputBoundary) {
        this.currentWeatherInputBoundary = currentWeatherInputBoundary;
    }

    public void switchToLoggedInSearchView() {
        currentWeatherInputBoundary.switchToLoggedInSearchView();
    }
    public void switchToLoggedInHomePageView() {
        currentWeatherInputBoundary.switchToLoggedInHomePageView();
    }
}
