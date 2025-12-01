package interface_adapter.settings.delete_account;

import use_case.delete_account.DeleteAccountOutputBoundary;
import use_case.delete_account.DeleteAccountOutputData;

import javax.swing.*;

public class SettingsDeleteAccountPresenter implements DeleteAccountOutputBoundary {

    public SettingsDeleteAccountPresenter() {}

    @Override
    public void prepareSuccessView(DeleteAccountOutputData outputData) {
        // popup
        JOptionPane.showMessageDialog(null, "Account deleted: " + outputData.getUsername());

    }
}
