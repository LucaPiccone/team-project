package interface_adapter.create_account;

import use_case.create_account.CreateAccountInputBoundary;
import use_case.create_account.CreateAccountInputData;

public class CreateAccountController {
    private final CreateAccountInputBoundary interactor;

    public CreateAccountController(CreateAccountInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username, String password, String repeatPassword) {
        CreateAccountInputData inputData = new CreateAccountInputData(username, password, repeatPassword);
        interactor.execute(inputData);
    }

    // “Go back”
    public void switchToHomePage() {
        interactor.switchToHomePage();
    }
}
