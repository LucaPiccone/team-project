package interface_adapter.hourly_forecast;

import entity.weatherReport.WeatherReport;
import interface_adapter.ViewManagerModel;
import interface_adapter.weatherReportPage.WeatherReportPageState;
import interface_adapter.weatherReportPage.WeatherReportPageViewModel;
import use_case.hourly_forecast.HourlyForecastOutputBoundary;

public class HourlyForecastPresenter implements HourlyForecastOutputBoundary {
    HourlyForecastViewModel hourlyForecastViewModel;
    WeatherReportPageViewModel weatherReportPageViewModel;
    ViewManagerModel viewManagerModel;

    public HourlyForecastPresenter(HourlyForecastViewModel hourlyForecastViewModel,
                                   WeatherReportPageViewModel weatherReportPageViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.hourlyForecastViewModel = hourlyForecastViewModel;
        this.weatherReportPageViewModel = weatherReportPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToWeatherReportView() {
        viewManagerModel.setState(weatherReportPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailGoBack(String cityName) {
        HourlyForecastState state = hourlyForecastViewModel.getState();
        state.setSetPopUpMessage(cityName + " City not found. Go back Home.");
        hourlyForecastViewModel.firePropertyChanged();
    }
}
