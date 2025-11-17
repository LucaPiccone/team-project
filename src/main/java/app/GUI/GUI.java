package app.GUI;

import entity.user.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.createAccount.CreateAccountViewModel;
import interface_adapter.homepage.HomePageController;
import interface_adapter.homepage.HomePagePresenter;
import interface_adapter.homepage.HomePageViewModel;
import use_case.homePage.HomePageInputBoundary;
import use_case.homePage.HomePageInteractor;
import use_case.homePage.HomePageOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import view.CreateAccountView;
import view.HomePageView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

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

    public GUI() {
        cardPanel.setLayout(cardLayout);
    }

    public GUI addHomePageView() {
        homePageViewModel = new HomePageViewModel();
        homePageView = new HomePageView(homePageViewModel);
        cardPanel.add(homePageView, homePageView.getViewName());
        return this;
    }

    public GUI addHomePageUseCase() {
        final HomePageOutputBoundary homePageOutputBoundary = new HomePagePresenter(homePageViewModel,
                createAccountViewModel, viewManagerModel);
        final HomePageInputBoundary homePageInputBoundary = new HomePageInteractor(homePageOutputBoundary);

        HomePageController controller = new HomePageController(homePageInputBoundary);
        homePageView.setHomePageController(controller);
        return this;
    }

    public GUI addCreateAccountView() {
        createAccountViewModel = new CreateAccountViewModel();
        createAccountView = new CreateAccountView(createAccountViewModel);
        cardPanel.add(createAccountView, createAccountView.getViewName());
        return this;
    }

    public JFrame build() {
        final JFrame application = new JFrame("Sophisticated Weather App");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        application.add(cardPanel);

        viewManagerModel.setState(homePageView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}
