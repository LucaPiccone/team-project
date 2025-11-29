package use_case.currentWeather;

import api.openWeatherApi.WeatherDataFetcher;
import api.geocodingapi.CoordinatesFetcher;

public interface CurrentWeatherInputBoundary {
    void switchToLoggedInSearchView();
    void switchToLoggedInHomePageView();

    void addToFavourites(CurrentWeatherInputData inputData) throws CoordinatesFetcher.CityNotFoundException, WeatherDataFetcher.CityNotFoundException;

    void resetPopUpMessage();

    void switchToCheckOutfitView();

    void switchToHourlyForecastView(String cityName);
}
