package use_case.current_weather;

public class CurrentWeatherInputData {
    private final String cityName;

    public CurrentWeatherInputData(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }
}
