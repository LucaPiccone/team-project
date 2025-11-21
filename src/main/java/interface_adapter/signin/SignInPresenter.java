package interface_adapter.signin;

import interface_adapter.ViewManagerModel;
import interface_adapter.homepage.HomePageViewModel;
import use_case.signIn.SignInOutputBoundary;
//import use_case.signIn.LoginOutputData;

public class SignInPresenter implements SignInOutputBoundary {
    private final SignInViewModel signInViewModel;
    private final HomePageViewModel homePageViewModel;
    private final ViewManagerModel viewManagerModel;

    public SignInPresenter(SignInViewModel signInViewModel, HomePageViewModel homePageViewModel, ViewManagerModel viewManagerModel) {
        this.signInViewModel = signInViewModel;
        this.homePageViewModel = homePageViewModel;
        this.viewManagerModel = viewManagerModel;
    }

//    @Override
//    public void prepareSuccessView(LoginOutputData outputData) {
//
//    }
//
//    @Override
//    public void prepareFailView(String errorMessage) {
//
//    }

    @Override
    public void switchToHomePage() {
        viewManagerModel.setState(homePageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
