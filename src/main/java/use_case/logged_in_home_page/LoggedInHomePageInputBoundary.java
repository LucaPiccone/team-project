package use_case.logged_in_home_page;

public interface LoggedInHomePageInputBoundary {
    void switchToLoggedInSearchView();

    void switchToLoggedInFavouritesView();

    void switchToSettings(String username);
}
