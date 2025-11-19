package interface_adapter.loggedInFavouritesPage;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import use_case.loggedInFavouritesPage.LoggedInFavouritesPageOutputBoundary;

public class LoggedInFavouritesPagePresenter implements LoggedInFavouritesPageOutputBoundary {
    private final LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel;
    private final LoggedInHomePageViewModel loggedInHomePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInFavouritesPagePresenter(LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel, LoggedInHomePageViewModel loggedInHomePageViewModel, ViewManagerModel viewManagerModel) {
        this.loggedInFavouritesPageViewModel = loggedInFavouritesPageViewModel;
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        viewManagerModel.setState(loggedInHomePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}