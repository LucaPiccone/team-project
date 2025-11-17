package OpenWeatherAPI;

import api.OpenWeatherApi.OpenWeatherApiDataFetcher;
import api.OpenWeatherApi.WeatherDataFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import api.geocodingapi.CoordinatesFetcher;

import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;


public class OpenWeatherFetcherTest {

    @Test
    void testValidLocationReturnsResults() throws WeatherDataFetcher.CityNotFoundException{
        double v = 51.5085;
        HashMap<String, Double> coordinates = new  HashMap<>();
        coordinates.put("lat", 51.5085);
        coordinates.put("lon", -0.1257);
        WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
        JSONObject weather = fetcher.getWeather(coordinates);
        weather.toString();
        String expected = "{coord:{lon:-0.1257,lat:51.5085},weather: [{id:803,main:Clouds,description:broken clouds,icon:04d}],base:stations,main: {temp:280.85,feels_like:277.6,temp_min:280.1,temp_max:281.53,pressure:1024,humidity:69,sea_level: 1024,grnd_level:1019},visibility:10000,wind:{speed:5.66,deg:350},clouds:{all:75},dt: 1763391271,sys:{type:2,id:2075535,country:GB,sunrise:1763364130,sunset:1763395744},timezone:0, id:2643743,name:London,cod:200}";
        assertEquals(expected, weather);
    }
}
