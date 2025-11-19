package interface_adapter.loggedInHomePage;

import interface_adapter.ViewModel;

public class LoggedInHomePageViewModel extends ViewModel<LoggedInHomePageState> {
    public static final String TO_SEARCH_LABEL = "Go to Search Page";
    public static final String TO_FAVOURITES_LABEL = "Go to Favourites Page";
    public static final String WELCOME_LABEL = "Welcome!";

    public LoggedInHomePageViewModel() {
        super("logged in home page");
        setState(new LoggedInHomePageState());
    }
}
