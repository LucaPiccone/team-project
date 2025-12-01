package interface_adapter.logged_in_favourites_page;

import use_case.logged_in_favourites_page.LoggedInFavouritesPageInputBoundary;

public class LoggedInFavouritesPageController {
    private final LoggedInFavouritesPageInputBoundary loggedInFavouritesPageInputBoundary;

    public LoggedInFavouritesPageController(LoggedInFavouritesPageInputBoundary loggedInFavouritesPageInputBoundary) {
        this.loggedInFavouritesPageInputBoundary = loggedInFavouritesPageInputBoundary;
    }

    public void switchToLoggedInHomePageView() {
        loggedInFavouritesPageInputBoundary.switchToLoggedInHomePageView();
    }

    public void execute(String location) {
        loggedInFavouritesPageInputBoundary.execute(location);
    }
}