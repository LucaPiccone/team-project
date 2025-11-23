package use_case.signIn;

/**
 * The output boundary for the Login Use Case.
 */
public interface SignInOutputBoundary {
    void prepareSuccessView(SigninOutputData outputData);
    void prepareFailView(String errorMessage);
    void switchToHomePage();
}