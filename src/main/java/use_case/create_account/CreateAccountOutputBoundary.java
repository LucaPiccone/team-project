package use_case.create_account;

public interface CreateAccountOutputBoundary {
    void prepareSuccessView(CreateAccountOutputData data);
    void prepareFailView(String usernameError,
                         String passwordError,
                         String repeatPasswordError);

    //  goBack / interactor.switchToLoginView()
    void switchToHomePage();
}
