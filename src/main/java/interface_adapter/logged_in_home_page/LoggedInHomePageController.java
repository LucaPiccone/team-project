package interface_adapter.logged_in_home_page;

import use_case.change_password.ChangePasswordInputBoundary;
import use_case.change_password.ChangePasswordInputData;
import use_case.delete_account.DeleteAccountInputBoundary;
import use_case.delete_account.DeleteAccountInputData;
import use_case.logged_in_home_page.LoggedInHomePageInputBoundary;

/**
 * Controller for the Change Password Use Case.
 */
public class LoggedInHomePageController {
    private final LoggedInHomePageInputBoundary loggedInHomePageInputBoundary;
    private final ChangePasswordInputBoundary userChangePasswordUseCaseInteractor;
    private final DeleteAccountInputBoundary deleteAccountInputBoundary;

    public LoggedInHomePageController(LoggedInHomePageInputBoundary loggedInHomePageInputBoundary,
                                      ChangePasswordInputBoundary userChangePasswordUseCaseInteractor,
                                      DeleteAccountInputBoundary deleteAccountInputBoundary) {
        this.loggedInHomePageInputBoundary = loggedInHomePageInputBoundary;
        this.userChangePasswordUseCaseInteractor = userChangePasswordUseCaseInteractor;
        this.deleteAccountInputBoundary = deleteAccountInputBoundary;
    }

    public void switchToLoggedInSearchView() {
        loggedInHomePageInputBoundary.switchToLoggedInSearchView();
    }

    public void switchToLoggedInFavouritesView() {
        loggedInHomePageInputBoundary.switchToLoggedInFavouritesView();
    }

    /**
     * Executes the Change Password Use Case.
     * @param password the new password
     * @param repeatPassword the repeated password
     */
    public void changePassword(String password, String repeatPassword) {
        final ChangePasswordInputData changePasswordInputData = new ChangePasswordInputData(password,  repeatPassword);
        userChangePasswordUseCaseInteractor.execute(changePasswordInputData);
    }

    public void deleteAccount(String currentUserName) {
        final DeleteAccountInputData deleteAccountInputData = new DeleteAccountInputData(currentUserName);
        deleteAccountInputBoundary.execute(deleteAccountInputData);
    }

    public void switchToSettings(String username) {
        loggedInHomePageInputBoundary.switchToSettings(username);
    }
}
