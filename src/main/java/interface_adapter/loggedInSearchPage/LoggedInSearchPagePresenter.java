package interface_adapter.loggedInSearchPage;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import use_case.loggedInSearchPage.LoggedInSearchPageOutputBoundary;

public class LoggedInSearchPagePresenter implements LoggedInSearchPageOutputBoundary {
    private final LoggedInSearchPageViewModel loggedInSearchPageViewModel;
    private final LoggedInHomePageViewModel loggedInHomePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoggedInSearchPagePresenter(LoggedInSearchPageViewModel loggedInSearchPageViewModel,
                                   LoggedInHomePageViewModel loggedInHomePageViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.loggedInSearchPageViewModel = loggedInSearchPageViewModel;
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        viewManagerModel.setState(loggedInHomePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}