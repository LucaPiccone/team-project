package use_case.signin;

/**
 * The output boundary for the Login Use Case.
 */
public interface SignInOutputBoundary {
    void prepareSuccessView(SigninOutputData outputData);
    void prepareFailView(String errorMessage);
    void switchToHomePage();
}