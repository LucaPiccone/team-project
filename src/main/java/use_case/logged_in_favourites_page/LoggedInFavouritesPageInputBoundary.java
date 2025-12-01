package use_case.logged_in_favourites_page;

public interface LoggedInFavouritesPageInputBoundary {

    void switchToLoggedInHomePageView();

    void execute(String location);
}
