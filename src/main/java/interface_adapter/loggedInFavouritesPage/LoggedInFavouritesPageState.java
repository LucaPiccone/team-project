package interface_adapter.loggedInFavouritesPage;

import entity.weatherReport.WeatherReport;

import java.util.List;

public class LoggedInFavouritesPageState {
    private List<WeatherReport> weatherReports;

    public List<WeatherReport> getWeatherReports() {
        return weatherReports;
    }

    public void setWeatherReports(List<WeatherReport> weatherReports) { this.weatherReports = weatherReports;}
}
