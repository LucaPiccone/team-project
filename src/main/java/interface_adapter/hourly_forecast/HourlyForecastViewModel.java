package interface_adapter.hourly_forecast;

import interface_adapter.ViewModel;
import interface_adapter.weatherReportPage.WeatherReportPageState;

public class HourlyForecastViewModel extends ViewModel<HourlyForecastState> {
    public static final String GO_BACK_LABEL = "Go Back";
    public static final String TITLE_LABEL = "Hourly Forecast";

    public HourlyForecastViewModel() {
        super("Hourly Forecast View");
        setState(new HourlyForecastState());
    }
}
