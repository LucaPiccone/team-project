package entity.weatherReport;

import api.OpenWeatherApi.*;
import api.geocodingapi.CoordinatesFetcher;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;

public class WeatherReportFactory {

    private final WeatherDataFetcher fetcher;
    private final CoordinatesFetcher fetcher1;
    public WeatherReportFactory(WeatherDataFetcher fetcher, CoordinatesFetcher fetcher1) {
        this.fetcher = fetcher;
        this.fetcher1 = fetcher1;
    }
    /**
     * Create a Weather report object given a location.
     */
    public WeatherReport create(String name) throws CoordinatesFetcher.CityNotFoundException, WeatherDataFetcher.CityNotFoundException {
        HashMap<String, Double> coordinates = fetcher1.getCoordinates(name);
        coordinates.put("lat", coordinates.get("lat"));
        coordinates.put("lon", coordinates.get("lon"));

        JSONObject weatherData = fetcher.getWeather(coordinates);

        String temperature = weatherData.getString("temperature");
        String humidity = weatherData.getString("humidity");
        String feelsLike = weatherData.getString("feelsLike");
        JSONArray weatherArray = weatherData.getJSONArray("weather");
        JSONObject currentWeather = weatherArray.getJSONObject(0);
        String weather = currentWeather.getString("main");

        return new WeatherReport(name, weather, temperature, feelsLike, humidity);
    }
}
