package interface_adapter.loggedInSearchPage;

import use_case.loggedInSearchPage.LoggedInSearchPageInputBoundary;

public class LoggedInSearchPageController {
    private final LoggedInSearchPageInputBoundary loggedInSearchPageInputBoundary;

    public LoggedInSearchPageController(LoggedInSearchPageInputBoundary loggedInSearchPageInputBoundary) {
        this.loggedInSearchPageInputBoundary = loggedInSearchPageInputBoundary;
    }

    public void switchToLoggedInHomePageView() {
        loggedInSearchPageInputBoundary.switchToLoggedInHomePageView();
    }
}