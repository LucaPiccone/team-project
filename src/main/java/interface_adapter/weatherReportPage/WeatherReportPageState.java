package interface_adapter.weatherReportPage;

public class WeatherReportPageState {
    private String cityName = "";

    public WeatherReportPageState(WeatherReportPageState copy) {
        cityName = copy.cityName;
    }
    public WeatherReportPageState() {

    }
    public String getCityName() { return cityName;}
}
