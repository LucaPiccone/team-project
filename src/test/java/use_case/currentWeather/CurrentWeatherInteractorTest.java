package use_case.currentWeather;

import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import api.openWeatherApi.WeatherDataFetcher;
import data_access.InMemoryUserDataAccessObject;
import data_access.UserDataAccessInterface;
import entity.hourly_forecast_report.HourlyForecastReport;
import entity.placeSuggestions.PlaceSuggestion;
import entity.user.User;
import entity.weatherReport.WeatherReport;
import interface_adapter.ViewManagerModel;
import interface_adapter.checkOutfit.CheckOutfitViewModel;
import interface_adapter.hourly_forecast.HourlyForecastViewModel;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageViewModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageViewModel;
import interface_adapter.weatherReportPage.WeatherReportPagePresenter;
import interface_adapter.weatherReportPage.WeatherReportPageState;
import interface_adapter.weatherReportPage.WeatherReportPageViewModel;
import org.junit.jupiter.api.Test;
import use_case.loggedInSearchPage.*;
import api.geocodingapi.CoordinatesFetcher.CityNotFoundException;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CurrentWeatherInteractorTest {
    UserDataAccessInterface dataAccessInterface = new InMemoryUserDataAccessObject();
    WeatherReportPageViewModel  weatherReportPageViewModel = new WeatherReportPageViewModel();
    LoggedInSearchPageViewModel loggedInSearchPageViewModel = new LoggedInSearchPageViewModel();
    LoggedInHomePageViewModel loggedInHomePageViewModel = new LoggedInHomePageViewModel();
    CheckOutfitViewModel checkOutfitViewModel = new  CheckOutfitViewModel();
    LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel = new LoggedInFavouritesPageViewModel();
    HourlyForecastViewModel hourlyForecastViewModel = new  HourlyForecastViewModel();
    ViewManagerModel viewManagerModel = new ViewManagerModel();

    WeatherReportPagePresenter presenter = new WeatherReportPagePresenter(weatherReportPageViewModel,
            loggedInSearchPageViewModel, loggedInHomePageViewModel, checkOutfitViewModel,
            loggedInFavouritesPageViewModel, hourlyForecastViewModel, viewManagerModel);

    CurrentWeatherOutputBoundary outputBoundary = new  CurrentWeatherOutputBoundary() {
        @Override
        public void switchToLoggedInSearchView() {}
        @Override
        public void switchToLoggedInHomePageView() {}
        @Override
        public void switchToCheckOutfitView(WeatherReportPageState state) {}
        @Override
        public void addToFavouriteSuccess(CurrentWeatherOutputData outputData) {}
        @Override
        public void addToFavouriteFail(CurrentWeatherOutputData outputData) {}
        @Override
        public void resetPopUpMessage() {}
        @Override
        public void switchToFavouritesPageView(List<WeatherReport> weatherReports) {}
        @Override
        public void switchToHourlyForecast(HourlyForecastReport report, String cityName) {}
    };

    @Test
    void WeatherReportTestSuccess() throws PlaceFetcher.PlaceNotFoundException {
        String cityName = "Tor";


        GooglePlacesFetcher  googlePlacesFetcher = new GooglePlacesFetcher();
        /**
        List<PlaceSuggestion> suggestions = googlePlacesFetcher.getPlace(cityName);
         **/
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
    @Test
    void WeatherReportTestFail() throws PlaceFetcher.PlaceNotFoundException {
        String cityName = "paospdoncdji";

        //CurrentWeatherInputData currentWeatherInputData = new CurrentWeatherInputData("Toronto");
        //CurrentWeatherDataAccessInterface dataAccessInterface = new CurrentWeatherDataAccessInterface();

        GooglePlacesFetcher  googlePlacesFetcher = new GooglePlacesFetcher();
        /**
         List<PlaceSuggestion> suggestions = googlePlacesFetcher.getPlace(cityName);
         **/
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

    @Test
    void switchToCheckOutfitViewTest(){
        WeatherReportPageState weatherReportPageState = new WeatherReportPageState();
        //CurrentWeatherInputBoundary inputBoundary = new CurrentWeatherInteractor(dataAccessInterface, outputBoundary);
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(dataAccessInterface, outputBoundary);
        interactor.switchToCheckOutfitView(weatherReportPageState);
        //inputBoundary.switchToCheckOutfitView(weatherReportPageState);
    }
    @Test
    void switchToLoggedInSearchViewTest(){
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(dataAccessInterface, outputBoundary);
        interactor.switchToLoggedInSearchView();
    }

    @Test
    void switchToLoggedInHomePageViewTest(){
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(dataAccessInterface, outputBoundary);
        interactor.switchToLoggedInHomePageView();
    }
    @Test
    void addToFavouritesTest() throws CityNotFoundException, WeatherDataFetcher.CityNotFoundException {
        User user = new User("username", "password");
        String cityName = "Toronto";
        CurrentWeatherInputData inputData  = new CurrentWeatherInputData(cityName);
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(dataAccessInterface, outputBoundary);
        interactor.addToFavourites(inputData);
    }
    @Test
    void resetPopUpMessageTest(){
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(dataAccessInterface, outputBoundary);
        interactor.resetPopUpMessage();
    }

    @Test
    void switchToFavouritesPageViewSuccessTest(){
        UserDataAccessInterface userDataAccessInterface = new UserDataAccessInterface() {
            @Override
            public void save(User user) {}
            @Override
            public User get(String username) {return null;}
            @Override
            public String getCurrentUsername() {return "";}
            @Override
            public void setCurrentUsername(String username) {}
            @Override
            public void addLocation(String location) {}
            @Override
            public List<String> getLocations() {
                List<String> locations = new ArrayList<>();
                locations.add("location1");
                locations.add("location2");
                return locations;
            }
        };
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(userDataAccessInterface, outputBoundary);
        interactor.switchToFavouritesPageView();
    }
    @Test
    void switchToFavouritesPageViewFailTest(){
        UserDataAccessInterface userDataAccessInterface = new UserDataAccessInterface() {
            @Override
            public void save(User user) {}
            @Override
            public User get(String username) {return null;}
            @Override
            public String getCurrentUsername() {return "";}
            @Override
            public void setCurrentUsername(String username) {}
            @Override
            public void addLocation(String location) {}
            @Override
            public List<String> getLocations() {
                List<String> locations = new ArrayList<>();
                return locations;
            }
        };
        CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(userDataAccessInterface, outputBoundary);
        interactor.switchToFavouritesPageView();
    }
    @Test
    void switchToHourlyForecastTest() throws CityNotFoundException {
        UserDataAccessInterface userDataAccessInterface = new UserDataAccessInterface() {
            @Override
            public void save(User user) {}
            @Override
            public User get(String username) {return null;}
            @Override
            public String getCurrentUsername() {return "";}
            @Override
            public void setCurrentUsername(String username) {}
            @Override
            public void addLocation(String location) {}
            @Override
            public List<String> getLocations() { return List.of();}
        };
        String cityName = "Toronto";
        CurrentWeatherInputBoundary interactor  = new CurrentWeatherInteractor(userDataAccessInterface, outputBoundary);
        //CurrentWeatherInteractor interactor = new CurrentWeatherInteractor(userDataAccessInterface, outputBoundary);
        interactor.switchToHourlyForecast(cityName);
    }
}

