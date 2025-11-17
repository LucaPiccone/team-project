package view;


import interface_adapter.homepage.HomePageController;
import interface_adapter.homepage.HomePageViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomePageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "home page";
    private final HomePageViewModel homePageViewModel;
    HomePageController homePageController = null;

    private final JButton createAccount;

    public HomePageView(HomePageViewModel homePageViewModel) {
        this.homePageViewModel = homePageViewModel;

        final JPanel buttons = new JPanel();
        createAccount = new JButton(HomePageViewModel.TO_CREATE_ACCOUNT_LABEL);
        buttons.add(createAccount);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(buttons);

        createAccount.addActionListener(
                e -> homePageController.switchToCreateAccountView()
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setHomePageController(HomePageController controller) {
        this.homePageController = controller;
    }

    public String getViewName() {return viewName;}
}
