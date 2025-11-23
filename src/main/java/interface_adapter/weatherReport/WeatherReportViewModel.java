package interface_adapter.weatherReport;

import interface_adapter.ViewModel;

public class WeatherReportViewModel extends ViewModel<WeatherReportState> {
    public WeatherReportViewModel() {
        super("Weather report view");
        setState(new WeatherReportState());
    }
}
