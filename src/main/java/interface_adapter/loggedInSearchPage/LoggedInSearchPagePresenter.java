package interface_adapter.loggedInSearchPage;

import entity.placeSuggestions.PlaceSuggestion;
import interface_adapter.ViewManagerModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import use_case.loggedInSearchPage.LoggedInSearchPageOutputBoundary;

import javax.swing.*;
import java.util.List;

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
    public void showSuggestionsToUser(List<PlaceSuggestion> suggestions) {
        System.out.println(suggestions);
        final LoggedInSearchPageState state = loggedInSearchPageViewModel.getState();
        state.setSuggestions(suggestions);
        loggedInSearchPageViewModel.setState(state);
        loggedInSearchPageViewModel.firePropertyChanged();
    }
}