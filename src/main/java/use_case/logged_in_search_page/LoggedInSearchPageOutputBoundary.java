package use_case.logged_in_search_page;

import entity.place_suggestions.PlaceSuggestion;
import entity.weather_report.WeatherReport;

import java.util.List;

public interface LoggedInSearchPageOutputBoundary {
    void switchToLoggedInHomePageView();

    void showSuggestionsToUser(List<PlaceSuggestion> suggestions);

    void switchToWeatherReportView(WeatherReport weatherReport);

    void prepareFailToExecute(String query);

    void resetPopUpMessage();
}
