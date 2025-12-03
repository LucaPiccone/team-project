package use_case.sign_in;

/**
 * The output boundary for the Signin Use Case.
 */
public interface SignInOutputBoundary {
    void prepareSuccessView(SignInOutputData outputData);
    void prepareFailView(String errorMessage);
    void switchToHomePage();
}