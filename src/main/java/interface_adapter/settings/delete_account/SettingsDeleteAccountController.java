package interface_adapter.settings.delete_account;

import use_case.delete_account.DeleteAccountInputBoundary;
import use_case.delete_account.DeleteAccountInputData;

public class SettingsDeleteAccountController {
    private final DeleteAccountInputBoundary deleteAccountInputBoundary;

    public SettingsDeleteAccountController(DeleteAccountInputBoundary deleteAccountInputBoundary) {
        this.deleteAccountInputBoundary = deleteAccountInputBoundary;
    }


    public void deleteAccount(String currentUserName) {
        final DeleteAccountInputData deleteAccountInputData = new DeleteAccountInputData(currentUserName);
        deleteAccountInputBoundary.execute(deleteAccountInputData);
    }
}
