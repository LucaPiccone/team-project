package use_case.logged_in_home_page;

import entity.weather_report.WeatherReport;

import java.util.List;

public interface LoggedInHomePageOutputBoundary {
    void switchToLoggedInSearchView();

    void switchToLoggedInFavouritesView(List<WeatherReport> weatherReports);

    void switchToSettings(String username);
}
