package interface_adapter.loggedInSearchPage;

import entity.placeSuggestions.PlaceSuggestion;
import entity.weatherReport.WeatherReport;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import interface_adapter.weatherReport.WeatherReportState;
import interface_adapter.weatherReport.WeatherReportViewModel;
import use_case.loggedInSearchPage.LoggedInSearchPageOutputBoundary;

import javax.swing.*;
import java.util.List;

public class LoggedInSearchPagePresenter implements LoggedInSearchPageOutputBoundary {
    private final LoggedInSearchPageViewModel loggedInSearchPageViewModel;
    private final LoggedInHomePageViewModel loggedInHomePageViewModel;
    private final WeatherReportViewModel weatherReportViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInSearchPagePresenter(LoggedInSearchPageViewModel loggedInSearchPageViewModel,
                                       LoggedInHomePageViewModel loggedInHomePageViewModel, WeatherReportViewModel weatherReportViewModel,
                                       ViewManagerModel viewManagerModel) {
        this.loggedInSearchPageViewModel = loggedInSearchPageViewModel;
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.weatherReportViewModel = weatherReportViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        viewManagerModel.setState(loggedInHomePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void showSuggestionsToUser(List<PlaceSuggestion> suggestions) {
        System.out.println(suggestions);
        final LoggedInSearchPageState state = loggedInSearchPageViewModel.getState();
        state.setSuggestions(suggestions);
        loggedInSearchPageViewModel.setState(state);
        loggedInSearchPageViewModel.firePropertyChanged();
    }

    @Override
    public void switchToWeatherReportView(WeatherReport weatherReport) {
        // This function should be complete. The WeatherReportState will have all the information in weather report.
        final WeatherReportState state = weatherReportViewModel.getState();
        state.setWeatherReport(weatherReport);
        weatherReportViewModel.setState(state);
        weatherReportViewModel.firePropertyChanged();
        viewManagerModel.setState(weatherReportViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}