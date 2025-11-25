package view;

import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageController;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInFavouritesPageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Logged In Favourites View";
    private final LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel;
    LoggedInFavouritesPageController loggedInFavouritesPageController  = null;

    private final JButton goBack;


    public LoggedInFavouritesPageView(LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel) {
        this.loggedInFavouritesPageViewModel = loggedInFavouritesPageViewModel;

        final JLabel title = new JLabel(LoggedInFavouritesPageViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        //** Build buttons bar **//
        final JPanel buttons = new JPanel();
        goBack = new JButton(LoggedInFavouritesPageViewModel.GO_BACK_LABEL);
        buttons.add(goBack);

        //** Build View **//
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(20));
        this.add(buttons);

        goBack.addActionListener(
                e -> loggedInFavouritesPageController.switchToLoggedInHomePageView()
        );

    }

    public void setFavouritesPageController(LoggedInFavouritesPageController controller) {
        this.loggedInFavouritesPageController = controller;
    }

    public String getViewName() {return viewName;}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
