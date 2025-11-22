package interface_adapter.loggedInSearchPage;

import interface_adapter.ViewManagerModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import use_case.loggedInSearchPage.LoggedInSearchPageAutoCompletedOutputData;
import use_case.loggedInSearchPage.LoggedInSearchPageOutputBoundary;

import java.util.Collections;

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

    @Override
    public void showSuggestionsToUser(LoggedInSearchPageAutoCompletedOutputData data) {
        LoggedInSearchPageState state = loggedInSearchPageViewModel.getState();
        state.setErrorMessage("");
        state.setSuggestions(data.getSuggestions());
        loggedInSearchPageViewModel.setState(state);
        loggedInSearchPageViewModel.firePropertyChanged();
    }

    @Override
    public void presentError(String message) {
        LoggedInSearchPageState state = loggedInSearchPageViewModel.getState();
        state.setErrorMessage(message);
        state.setSuggestions(Collections.emptyList());
        loggedInSearchPageViewModel.setState(state);
        loggedInSearchPageViewModel.firePropertyChanged();
    }
}