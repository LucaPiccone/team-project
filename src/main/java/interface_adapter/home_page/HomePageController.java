package interface_adapter.home_page;

import use_case.home_page.HomePageInputBoundary;

//** This is the HomePageController. Input from the homePageView passes through here.
public class HomePageController {
    //** From the controller(here) we will go to the interactor (use case folder) **//
    private final HomePageInputBoundary homePageInputBoundary;

    public HomePageController(HomePageInputBoundary homePageInputBoundary) {
        this.homePageInputBoundary = homePageInputBoundary;
    }

    //** Switch to SignInView use case **//
    public void switchToCreateAccountView() {
        homePageInputBoundary.switchToCreateAccountView();
    }

    public void switchToSigninView() {
        homePageInputBoundary.switchToSigninView();
    }
}
