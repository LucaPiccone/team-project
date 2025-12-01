package interface_adapter.logged_in_favourites_page;

import entity.weather_report.WeatherReport;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in_home_page.LoggedInHomePageViewModel;
import interface_adapter.weather_report_page.WeatherReportPageState;
import interface_adapter.weather_report_page.WeatherReportPageViewModel;
import use_case.logged_in_favourites_page.LoggedInFavouritesPageOutputBoundary;

public class LoggedInFavouritesPagePresenter implements LoggedInFavouritesPageOutputBoundary {
    private final LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel;
    private final LoggedInHomePageViewModel loggedInHomePageViewModel;
    private final WeatherReportPageViewModel weatherReportPageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInFavouritesPagePresenter(LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel, LoggedInHomePageViewModel loggedInHomePageViewModel, WeatherReportPageViewModel weatherReportPageViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInFavouritesPageViewModel = loggedInFavouritesPageViewModel;
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.weatherReportPageViewModel = weatherReportPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        viewManagerModel.setState(loggedInHomePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToWeatherReportView(WeatherReport weatherReport) {
        final WeatherReportPageState state = weatherReportPageViewModel.getState();
        state.setCityName(weatherReport.getLocation());
        state.setWeather(weatherReport.getWeather());
        state.setTemperature(weatherReport.getTemperature());
        state.setHumidity(weatherReport.getHumidity());
        state.setFeelsLike(weatherReport.getFeelsLike());
        weatherReportPageViewModel.setState(state);
        weatherReportPageViewModel.firePropertyChanged();
        viewManagerModel.setState(weatherReportPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}