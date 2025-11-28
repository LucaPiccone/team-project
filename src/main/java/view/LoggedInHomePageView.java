package view;

import data_access.FileUserDataAccessObjectWithLocations;
import entity.user.User;
import interface_adapter.loggedInHomePage.LoggedInHomePageController;
import interface_adapter.loggedInHomePage.LoggedInHomePageState;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInHomePageView extends JPanel implements PropertyChangeListener {
    //** DEFINING VARIABLES **//
    private final String viewName = "logged in home page";
    private final LoggedInHomePageViewModel loggedInHomePageViewModel;
    LoggedInHomePageController loggedInHomePageController = null;
    private LogoutController logoutController = null;
    private FileUserDataAccessObjectWithLocations fileUserDataAccessObjectWithLocations;

    private final JPasswordField passwordInputField = new JPasswordField(15);

    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);

    private final JButton toSearch;
    private final JButton toFavourites;
    private final JButton logout;
    private final JButton changePassword;
    private final JButton deleteAccount;
    private final JButton settings;

    //** CONSTRUCTOR **//
    public LoggedInHomePageView(LoggedInHomePageViewModel loggedInHomePageViewModel,
                                FileUserDataAccessObjectWithLocations fileUserDataAccessObjectWithLocations) {
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.fileUserDataAccessObjectWithLocations = fileUserDataAccessObjectWithLocations;

        loggedInHomePageViewModel.addPropertyChangeListener(this);
        // ===== TITLE =====
        final JLabel title = new JLabel(LoggedInHomePageViewModel.WELCOME_LABEL, SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 22f));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

// ===== Top buttons row =====
        toSearch = new JButton(LoggedInHomePageViewModel.TO_SEARCH_LABEL);
        toFavourites = new JButton(LoggedInHomePageViewModel.TO_FAVOURITES_LABEL);
        logout = new JButton(LoggedInHomePageViewModel.LOGOUT_LABEL);
        settings = new JButton(LoggedInHomePageViewModel.SETTINGS_LABEL);

        final JPanel buttonsRow = new JPanel(new GridLayout(1, 4, 12, 0));
        buttonsRow.add(toSearch);
        buttonsRow.add(toFavourites);
        buttonsRow.add(logout);
        buttonsRow.add(settings);
        buttonsRow.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonsRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

// ===== Change password section =====
        changePassword = new JButton(LoggedInHomePageViewModel.CHANGEPASSWORD_LABEL);

        final LabelTextPanel changePasswordInfo = new LabelTextPanel(
                new JLabel(LoggedInHomePageViewModel.CHANGEPASSWORD_LABEL), passwordInputField);

        final LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(LoggedInHomePageViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);

// Make inputs not look tiny under BoxLayout
        passwordInputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, passwordInputField.getPreferredSize().height));
        repeatPasswordInputField.setMaximumSize(new Dimension(Integer.MAX_VALUE, repeatPasswordInputField.getPreferredSize().height));

// Submit button on its own centered row
        final JPanel changePasswordRow = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        changePasswordRow.add(changePassword);

// Center form rows + error labels under BoxLayout
        changePasswordInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        repeatPasswordInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        changePasswordRow.setAlignmentX(Component.CENTER_ALIGNMENT);

// Prevent stretching full width (keeps visual centering)
        changePasswordInfo.setMaximumSize(changePasswordInfo.getPreferredSize());
        repeatPasswordInfo.setMaximumSize(repeatPasswordInfo.getPreferredSize());
        changePasswordRow.setMaximumSize(changePasswordRow.getPreferredSize());

// ===== Outer centering + spacing =====
        this.removeAll();
        this.setLayout(new GridBagLayout());

        final JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));
        content.setPreferredSize(new Dimension(560, 420));

// ===== Delete account section =====
        JLabel deleteHintLabel = new JLabel("don't want ur account anymore, then delete.");
        deleteAccount = new JButton("Delete Account");

// ===== Make deleting section centred =====
        deleteHintLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        deleteAccount.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(new BoxLayout(deletePanel, BoxLayout.Y_AXIS));
        deletePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        deletePanel.add(Box.createVerticalStrut(8));
        deletePanel.add(deleteHintLabel);
        deletePanel.add(Box.createVerticalStrut(8));
        deletePanel.add(deleteAccount);
        deletePanel.add(Box.createVerticalStrut(8));

// ===== Add components (title -> last add) =====
        content.add(title);
        content.add(Box.createVerticalStrut(16));

        content.add(buttonsRow);

        content.add(Box.createVerticalStrut(18));
        content.add(new JSeparator());
        content.add(Box.createVerticalStrut(18));

        content.add(changePasswordInfo);
        content.add(Box.createVerticalStrut(6));

        content.add(Box.createVerticalStrut(12));
        content.add(repeatPasswordInfo);
        content.add(Box.createVerticalStrut(6));

        content.add(Box.createVerticalStrut(14));
        content.add(changePasswordRow);
        content.add(Box.createVerticalStrut(14));
        content.add(deletePanel);
        this.add(content);


        toSearch.addActionListener(
                e -> loggedInHomePageController.switchToLoggedInSearchView()
        );

        toFavourites.addActionListener(
                e -> loggedInHomePageController.switchToLoggedInFavouritesView()
        );

        logout.addActionListener(e -> {
            Object[] options = {"Yes", "No"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (result == JOptionPane.YES_OPTION) {
                logoutController.execute();
            }
        });

        changePassword.addActionListener(
                e ->  {
                    Object[] options = {"Yes", "No"};
                    int result = JOptionPane.showOptionDialog(
                            this,
                            "Are you sure you want to change the password? This cannot be undone.",
                            "Confirm Change",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[1]
                    );

                    if (result != JOptionPane.YES_OPTION) {
                        return;
                    }

                    String password = passwordInputField.getText();
                    String repeatPassword = repeatPasswordInputField.getText();
                    loggedInHomePageController.changePassword(password, repeatPassword);

                }
        );

        deleteAccount.addActionListener(e -> {
            Object[] options = {"Yes", "No"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    "Are you sure you want to delete this account? This cannot be undone.",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[1]
            );

            if (result == JOptionPane.YES_OPTION) {
                String currentUserName = fileUserDataAccessObjectWithLocations.getCurrentUsername();
                loggedInHomePageController.deleteAccount(currentUserName);
                logoutController.execute();
            }
        });

        settings.addActionListener(e -> {
            LoggedInHomePageState state = loggedInHomePageViewModel.getState();
            loggedInHomePageController.switchToSettings(state.getUsername());
        });


        loggedInHomePageViewModel.addPropertyChangeListener(evt -> {
            LoggedInHomePageState state = loggedInHomePageViewModel.getState();
            // Update the text of the JLabel directly
            title.setText(LoggedInHomePageViewModel.WELCOME_LABEL + " " + state.getUsername());
        });

    }

    //** CREATE AN INSTANCE OF THE CONTROLLER **//
    public void setHomePageController(LoggedInHomePageController controller) {
        this.loggedInHomePageController = controller;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    //** RETURNS NAME OF THIS VIEW **//
    public String getViewName() {return viewName;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"passwordError".equals(evt.getPropertyName()) && !"state".equals(evt.getPropertyName())) {
            return;
        }

        LoggedInHomePageState state = loggedInHomePageViewModel.getState();

        String err = state.getPasswordError();

        if (err != null && !err.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    err,
                    "Change Password Failed",
                    JOptionPane.ERROR_MESSAGE
            );

            state.setPasswordError("");
            loggedInHomePageViewModel.setState(state);
        }

        revalidate();
        repaint();

    }
}
