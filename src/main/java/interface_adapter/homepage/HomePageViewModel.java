package interface_adapter.homepage;

import interface_adapter.ViewModel;

public class HomePageViewModel extends ViewModel<HomePageState> {
    public static final String TO_CREATE_ACCOUNT_LABEL = "Go to Create Account";

    public HomePageViewModel() {
        super("Home Page");
        setState(new HomePageState());
    }
}
