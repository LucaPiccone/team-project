package interface_adapter.logged_in_favourites_page;

import entity.weather_report.WeatherReport;

import java.util.ArrayList;
import java.util.List;

public class LoggedInFavouritesPageState {
    private List<WeatherReport> weatherReports = new ArrayList<>();


    public List<WeatherReport> getWeatherReports() {
        return weatherReports;
    }

    public void setWeatherReports(List<WeatherReport> weatherReports) { this.weatherReports = weatherReports;}

    public void clearState() {
        this.weatherReports = new ArrayList<>();
    }
}
