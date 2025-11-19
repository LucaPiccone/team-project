package use_case.loggedInFavouritesPage;

import use_case.loggedInSearchPage.LoggedInSearchPageOutputBoundary;

public class LoggedInFavouritesPageInteractor implements LoggedInFavouritesPageInputBoundary {
    private final LoggedInFavouritesPageOutputBoundary userPresenter;

    public LoggedInFavouritesPageInteractor(LoggedInFavouritesPageOutputBoundary userPresenter) {
        this.userPresenter = userPresenter;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }
}