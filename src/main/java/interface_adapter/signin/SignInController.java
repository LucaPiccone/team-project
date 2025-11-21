package interface_adapter.signin;

import use_case.signIn.SignInInputBoundary;

public class SignInController {
    private SignInViewModel signInViewModel;
    private final SignInInputBoundary signinInputBoundary;

    public SignInController(SignInInputBoundary signinInputBoundary) {
        this.signinInputBoundary = signinInputBoundary;
    }

    public void execute(String username, String password, String repeatPassword) {

    }

    public void switchToHomePage() {
        signinInputBoundary.switchToHomePage();
    }
}
