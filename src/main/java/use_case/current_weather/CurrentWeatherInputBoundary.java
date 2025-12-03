package use_case.current_weather;

import api.openWeatherApi.WeatherDataFetcher;
import api.geocodingapi.CoordinatesFetcher;
import interface_adapter.weather_report_page.WeatherReportPageState;

public interface CurrentWeatherInputBoundary {
    void switchToLoggedInSearchView();
    void switchToLoggedInHomePageView();
    void switchToCheckOutfitView(WeatherReportPageState state);

    void addToFavourites(CurrentWeatherInputData inputData);

    void resetPopUpMessage();

    void switchToFavouritesPageView();

    void switchToHourlyForecast(String cityName) throws CoordinatesFetcher.CityNotFoundException;
}
