package use_case.logged_in_favourites_page;

import entity.weather_report.WeatherReport;

public interface LoggedInFavouritesPageOutputBoundary {

    void switchToLoggedInHomePageView();

    void switchToWeatherReportView(WeatherReport weatherReport);
}
