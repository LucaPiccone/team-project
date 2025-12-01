package use_case.sign_in;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface SignInInputBoundary {
    void execute(SigninInputData signinInputData);
    void switchToHomePage();
}