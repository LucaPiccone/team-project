package interface_adapter.hourly_forecast;

import interface_adapter.ViewModel;
import interface_adapter.homepage.HomePageState;

public class HourlyForecastViewModel extends ViewModel<HourlyForecastState> {
    public HourlyForecastViewModel() {
        super("Hourly Forecast View");
        setState(new HourlyForecastState());
    }
}
