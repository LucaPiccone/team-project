package interface_adapter.loggedInHomePage;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageViewModel;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageViewModel;
import use_case.loggedInHomePage.LoggedInHomePageOutputBoundary;

public class LoggedInHomePagePresenter implements LoggedInHomePageOutputBoundary {
    private final LoggedInHomePageViewModel loggedInHomePageViewModel;
    private final LoggedInSearchPageViewModel loggedInSearchPageViewModel;
    private final LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInHomePagePresenter(LoggedInHomePageViewModel loggedInHomePageViewModel,
                                     LoggedInSearchPageViewModel loggedInSearchPageViewModel,
                                     LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel,
                                     ViewManagerModel viewManagerModel) {
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.loggedInSearchPageViewModel = loggedInSearchPageViewModel;
        this.loggedInFavouritesPageViewModel = loggedInFavouritesPageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToLoggedInSearchView() {
        viewManagerModel.setState(loggedInSearchPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToLoggedInFavouritesView() {
        viewManagerModel.setState(loggedInFavouritesPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
