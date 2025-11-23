package use_case.loggedInSearchPage;

public interface LoggedInSearchPageOutputBoundary {
    void switchToLoggedInHomePageView();

    void showSuggestionsToUser(LoggedInSearchPageAutoCompletedOutputData outputData);

    void presentError(String message);
}
