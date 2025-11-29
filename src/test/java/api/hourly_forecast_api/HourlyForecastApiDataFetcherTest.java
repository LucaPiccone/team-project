package api.hourly_forecast_api;

import api.geocodingapi.CoordinatesFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import entity.hourly_forecast_report.HourlyForecastReport;
import entity.placeSuggestions.PlaceSuggestion;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HourlyForecastApiDataFetcherTest {

    @Test
    void testGetPlace_validPlace() throws PlaceFetcher.PlaceNotFoundException, CoordinatesFetcher.CityNotFoundException {
        HashMap<String, Double> coordinates = new  HashMap<>();
        coordinates.put("lat", 0.0);
        coordinates.put("lon", 0.0);
        HourlyForecastApiDataFetcher forecastFetcher = new HourlyForecastApiDataFetcher();
        HourlyForecastReport report = forecastFetcher.getHourlyForecast(coordinates);

        System.out.println(report.getDateTimes());
    }
}