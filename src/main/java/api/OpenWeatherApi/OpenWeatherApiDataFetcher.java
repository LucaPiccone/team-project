package api.OpenWeatherApi;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class OpenWeatherApiDataFetcher implements WeatherDataFetcher {
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public JSONObject getWeather(HashMap<String, Double> coordinates) throws CityNotFoundException{
        String apiKey = "fb1fab6af13a72caf8c7e77866dd632e";
        final Request request = new Request.Builder()
                .url("https://pro.openweathermap.org/data/2.5/forecast/hourly?lat={"
                        + coordinates.get("lat") + "}&lon={" + coordinates.get("lon") + "}&appid={" + apiKey + "}").build();
        final JSONObject responseBody;
        try (Response response = client.newCall(request).execute()) {
            responseBody = new JSONObject(response.body().string());
            if (responseBody.length() > 2) {
                return responseBody;
            }
            else {
                throw new CityNotFoundException(coordinates);
            }
        } catch (IOException e) {
            throw new CityNotFoundException(coordinates);
        }

    }
}
