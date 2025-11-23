package interface_adapter.weatherReportPage;

import interface_adapter.ViewModel;

public class WeatherReportPageViewModel extends ViewModel<WeatherReportPageState>{
    final String GO_BACK_LABEL = "Go Back";
    final String HOME_LABEL = "Go to Home";

    public WeatherReportPageViewModel() {
        super("Weather Report View");
        setState(new WeatherReportPageState());
    }
}
