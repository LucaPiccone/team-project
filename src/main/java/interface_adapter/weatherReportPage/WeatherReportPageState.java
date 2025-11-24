package interface_adapter.weatherReportPage;

public class WeatherReportPageState {
    private String cityName = "";
    private String weather = "";
    private String temperature = "";
    private String humidity = "";
    private String feelsLike = "";

    public WeatherReportPageState(WeatherReportPageState copy) {
        cityName = copy.cityName;
        weather = copy.weather;
        temperature = copy.temperature;
        humidity = copy.humidity;
        feelsLike = copy.feelsLike;
    }
    public WeatherReportPageState() {

    }
    public String getCityName() { return cityName;}
    public String getWeather() { return weather;}
    public String getTemperature() { return temperature;}
    public String getHumidity() { return humidity;}
    public String getFeelsLike() { return feelsLike;}

    public void setCityName(String cityName) { this.cityName = cityName;}
    public void setWeather(String weather) { this.weather = weather;}
    public void setTemperature(String temperature) { this.temperature = temperature;}
    public void setHumidity(String humidity) { this.humidity = humidity;}
    public void setFeelsLike(String feelsLike) {this.feelsLike = feelsLike;}
}
