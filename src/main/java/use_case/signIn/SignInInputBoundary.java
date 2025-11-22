package use_case.signin;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface SignInInputBoundary {
    void execute(SigninInputData signinInputData);
    void switchToHomePage();
}