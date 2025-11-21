package app.GUI;

import entity.user.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createAccount.CreateAccountViewModel;
import interface_adapter.homepage.HomePageController;
import interface_adapter.homepage.HomePagePresenter;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageController;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPagePresenter;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageViewModel;
import interface_adapter.loggedInHomePage.LoggedInHomePageController;
import interface_adapter.loggedInHomePage.LoggedInHomePagePresenter;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import interface_adapter.loggedInFavourites.LoggedInFavouritesViewModel;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageController;
import interface_adapter.loggedInSearchPage.LoggedInSearchPagePresenter;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageViewModel;
import interface_adapter.signin.SignInViewModel;
import use_case.homePage.HomePageInputBoundary;
import use_case.homePage.HomePageInteractor;
import use_case.homePage.HomePageOutputBoundary;
import use_case.loggedInFavouritesPage.LoggedInFavouritesPageInputBoundary;
import use_case.loggedInFavouritesPage.LoggedInFavouritesPageInteractor;
import use_case.loggedInFavouritesPage.LoggedInFavouritesPageOutputBoundary;
import use_case.loggedInHomePage.LoggedInHomePageInputBoundary;
import use_case.loggedInHomePage.LoggedInHomePageInteractor;
import use_case.loggedInHomePage.LoggedInHomePageOutputBoundary;
import use_case.loggedInSearchPage.LoggedInSearchPageInputBoundary;
import use_case.loggedInSearchPage.LoggedInSearchPageInteractor;
import use_case.loggedInSearchPage.LoggedInSearchPageOutputBoundary;
import view.*;

import javax.swing.*;
import java.awt.*;

// **  GUI  **//
public class GUI {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    final UserFactory userFactory = new UserFactory();
    final ViewManagerModel viewManagerModel = new ViewManagerModel();
    ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private HomePageView homePageView;
    private HomePageViewModel homePageViewModel;

    private CreateAccountView createAccountView;
    private CreateAccountViewModel createAccountViewModel;

    private SignInView signInView;
    private SignInViewModel signInViewModel;

    private LoggedInHomePageView loggedInHomePageView;
    private LoggedInHomePageViewModel loggedInHomePageViewModel;

    private LoggedInSearchPageView loggedInSearchPageView;
    private LoggedInSearchPageViewModel loggedInSearchPageViewModel;

    private LoggedInFavouritesPageView loggedInFavouritesPageView;
    private LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel;

    public GUI() {
        cardPanel.setLayout(cardLayout);
    }

    //** HOME PAGE **//
    public GUI addHomePageView() {
        homePageViewModel = new HomePageViewModel();
        homePageView = new HomePageView(homePageViewModel);
        cardPanel.add(homePageView, homePageView.getViewName());
        return this;
    }

    //** HOME PAGE USE CASES **//
    public GUI addHomePageUseCase() {
        final HomePageOutputBoundary homePageOutputBoundary = new HomePagePresenter(homePageViewModel,
                createAccountViewModel, signInViewModel, viewManagerModel);
        final HomePageInputBoundary homePageInputBoundary = new HomePageInteractor(homePageOutputBoundary);

        HomePageController controller = new HomePageController(homePageInputBoundary);
        homePageView.setHomePageController(controller);
        return this;
    }

    //**CREATE ACCOUNT PAGE**//
    public GUI addCreateAccountView() {
        createAccountViewModel = new CreateAccountViewModel();
        createAccountView = new CreateAccountView(createAccountViewModel);
        cardPanel.add(createAccountView, createAccountView.getViewName());
        return this;
    }

    //**LOGGED IN HOME PAGE **//
    public GUI addLoggedInHomePageView() {
        loggedInHomePageViewModel = new LoggedInHomePageViewModel();
        loggedInHomePageView = new LoggedInHomePageView(loggedInHomePageViewModel);
        cardPanel.add(loggedInHomePageView, loggedInHomePageView.getViewName());
        return this;
    }

    //**LOGGED IN HOME PAGE USE CASES **//
    public GUI addLoggedInHomePageUseCases() {
        final LoggedInHomePageOutputBoundary loggedInHomePageOutputBoundary = new LoggedInHomePagePresenter(
                loggedInHomePageViewModel,
                loggedInSearchPageViewModel,
                loggedInFavouritesPageViewModel,
                viewManagerModel);
        final LoggedInHomePageInputBoundary loggedInHomePageInputBoundary = new LoggedInHomePageInteractor(loggedInHomePageOutputBoundary);

        LoggedInHomePageController controller = new LoggedInHomePageController(loggedInHomePageInputBoundary);
        loggedInHomePageView.setHomePageController(controller);
        return this;
    }

    // **LOGGED IN SEARCH PAGE **//
    public GUI addLoggedInSearchPageView() {
        loggedInSearchPageViewModel = new LoggedInSearchPageViewModel();
        loggedInSearchPageView = new LoggedInSearchPageView(loggedInSearchPageViewModel);
        cardPanel.add(loggedInSearchPageView, loggedInSearchPageView.getViewName());
        return this;
    }

    public GUI addLoggedInSearchPageUseCases() {
        final LoggedInSearchPageOutputBoundary loggedInSearchPageOutputBoundary = new LoggedInSearchPagePresenter(
                loggedInSearchPageViewModel,
                loggedInHomePageViewModel,
                viewManagerModel
        );
        final LoggedInSearchPageInputBoundary loggedInSearchPageInputBoundary = new LoggedInSearchPageInteractor(loggedInSearchPageOutputBoundary);

        LoggedInSearchPageController controller = new LoggedInSearchPageController(loggedInSearchPageInputBoundary);
        loggedInSearchPageView.setSearchPageController(controller);
        return this;
    }

    //**LOGGED IN FAVOURITES PAGE **//
    public GUI addLoggedInFavouritePageView() {
        loggedInFavouritesPageViewModel = new LoggedInFavouritesPageViewModel();
        loggedInFavouritesPageView = new LoggedInFavouritesPageView(loggedInFavouritesPageViewModel);
        cardPanel.add(loggedInFavouritesPageView, loggedInFavouritesPageView.getViewName());
        return this;
    }

    public GUI addLoggedInFavouritePageUseCases() {
        final LoggedInFavouritesPageOutputBoundary loggedInFavouritesPageOutputBoundary = new LoggedInFavouritesPagePresenter(
                loggedInFavouritesPageViewModel,
                loggedInHomePageViewModel,
                viewManagerModel
        );
        final LoggedInFavouritesPageInputBoundary loggedInFavouritesPageInputBoundary = new LoggedInFavouritesPageInteractor(loggedInFavouritesPageOutputBoundary);

        LoggedInFavouritesPageController controller = new LoggedInFavouritesPageController(loggedInFavouritesPageInputBoundary);
        loggedInFavouritesPageView.setFavouritesPageController(controller);
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Sophisticated Weather App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);
        //** View on Start Up. **//
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
