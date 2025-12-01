package use_case.currentWeather;

import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import entity.placeSuggestions.PlaceSuggestion;
import entity.weatherReport.WeatherReport;
import org.junit.jupiter.api.Test;
import use_case.loggedInSearchPage.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherInteractorTest {

    @Test
    void WeatherReportTest() throws PlaceFetcher.PlaceNotFoundException {
        String cityName = "Toronto";
        CurrentWeatherInputData currentWeatherInputData = new CurrentWeatherInputData("Toronto");
        CurrentWeatherDataAccessInterface dataAccessInterface = new CurrentWeatherDataAccessInterface();

        GooglePlacesFetcher  googlePlacesFetcher = new GooglePlacesFetcher();
        List<PlaceSuggestion> suggestions = googlePlacesFetcher.getPlace(cityName);

        LoggedInSearchPageOutputBoundary outputBoundary = new LoggedInSearchPageOutputBoundary() {
            @Override
            public void switchToLoggedInHomePageView() {}
            @Override
            public void showSuggestionsToUser(List<PlaceSuggestion> suggestions) {}
            @Override
            public void switchToWeatherReportView(WeatherReport weatherReport) {
                assertEquals(cityName, weatherReport.getLocation());
            }
            @Override
            public void prepareFailToExecute(String query) {}
            @Override
            public void resetPopUpMessage() {}
        };
        LoggedInSearchPageInputBoundary inputBoundary = new LoggedInSearchPageInteractor(outputBoundary, googlePlacesFetcher);
        inputBoundary.execute(cityName);
    }
}
