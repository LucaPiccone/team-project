package interface_adapter.weatherReport;

import entity.weatherReport.WeatherReport;

public class WeatherReportState {
    private WeatherReport weatherReport;

    public void setWeatherReport(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    public WeatherReport getWeatherReport(WeatherReport weatherReport) {
        return weatherReport;
    }
}
