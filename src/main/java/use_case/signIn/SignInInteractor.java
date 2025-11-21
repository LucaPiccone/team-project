package use_case.signIn;

import entity.user.User;

/**
 * The Login Interactor.
 */
public class SignInInteractor implements SignInInputBoundary {
    // private final LoginUserDataAccessInterface userDataAccessObject;
    private final SignInOutputBoundary signInPresenter;

    public SignInInteractor(SignInOutputBoundary signInPresenter) {
        // this.userDataAccessObject = userDataAccessInterface;
        this.signInPresenter = signInPresenter;
    }

    @Override
    public void switchToHomePage() {
        signInPresenter.switchToHomePage();
    }

//    @Override
//    public void execute(LoginInputData loginInputData) {
//        final String username = loginInputData.getUsername();
//        final String password = loginInputData.getPassword();
//        if (!userDataAccessObject.existsByName(username)) {
//            loginPresenter.prepareFailView(username + ": Account does not exist.");
//        }
//        else {
//            final String pwd = userDataAccessObject.get(username).getPassword();
//            if (!password.equals(pwd)) {
//                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
//            }
//            else {
//
//                final User user = userDataAccessObject.get(loginInputData.getUsername());
//
//                userDataAccessObject.setCurrentUsername(username);
//
//                final LoginOutputData loginOutputData = new LoginOutputData(user.getName());
//                loginPresenter.prepareSuccessView(loginOutputData);
//            }
//        }
//    }
}