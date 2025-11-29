package interface_adapter.hourly_forecast;

import entity.hourly_forecast_report.HourlyForecastReport;

public class HourlyForecastState {
    private HourlyForecastReport report;

    public HourlyForecastReport getReport() {
        return report;
    }

    public void setReport(HourlyForecastReport report) {
        this.report = report;
    }
}
