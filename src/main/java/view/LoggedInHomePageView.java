package view;

import interface_adapter.loggedInHomePage.LoggedInHomePageController;
import interface_adapter.loggedInHomePage.LoggedInHomePageState;
import interface_adapter.loggedInHomePage.LoggedInHomePageViewModel;

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

    private final JButton toSearch;
    private final JButton toFavourites;
    private final JButton logout;

    //** CONSTRUCTOR **//
    public LoggedInHomePageView(LoggedInHomePageViewModel loggedInHomePageViewModel) {
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;

        //** TITLE **//
        final JLabel title = new JLabel(LoggedInHomePageViewModel.WELCOME_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        final JPanel buttons = new JPanel();
        //** Go to search page button **//
        toSearch = new JButton(LoggedInHomePageViewModel.TO_SEARCH_LABEL);
        buttons.add(toSearch);
        //** Go to Favourites page button **//
        toFavourites = new JButton(LoggedInHomePageViewModel.TO_FAVOURITES_LABEL);
        buttons.add(toFavourites);
        //** Log out
        logout =  new JButton(LoggedInHomePageViewModel.LOGOUT_LABEL);
        buttons.add(logout);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons);


        toSearch.addActionListener(
                e -> loggedInHomePageController.switchToLoggedInSearchView()
        );

        toFavourites.addActionListener(
                e -> loggedInHomePageController.switchToLoggedInFavouritesView()
        );

        logout.addActionListener(
                e -> loggedInHomePageController.logout()
        );

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

    //** RETURNS NAME OF THIS VIEW **//
    public String getViewName() {return viewName;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
