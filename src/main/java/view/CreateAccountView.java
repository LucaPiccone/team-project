package view;

import interface_adapter.createAccount.CreateAccountController;
import interface_adapter.createAccount.CreateAccountState;
import interface_adapter.createAccount.CreateAccountViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CreateAccountView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "create account";

    private final CreateAccountViewModel createAccountViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private CreateAccountController createAccountController = null;

    private final JButton goBack;

    public CreateAccountView(CreateAccountViewModel createAccountViewModel) {
        this.createAccountViewModel = createAccountViewModel;
        createAccountViewModel.addPropertyChangeListener(this);

        final JPanel buttons = new JPanel();
        goBack = new JButton(createAccountViewModel.GO_BACK_BUTTON_LABEL);
        buttons.add(goBack);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);
    }


    public void setCreateController(CreateAccountController controller) {
        this.createAccountController = controller;
    }

    public String getViewName() {return viewName;}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
