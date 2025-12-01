package use_case.current_weather;

public class CurrentWeatherOutputData {
    private final String cityName;

    public CurrentWeatherOutputData(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {return cityName;}
}
