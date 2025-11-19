package view;

import interface_adapter.loggedInSearchPage.LoggedInSearchPageController;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoggedInSearchPageView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Logged In Search View";
    private final LoggedInSearchPageViewModel loggedInSearchPageViewModel;
    LoggedInSearchPageController loggedInSearchPageController = null;

    private final JTextField searchInputField = new JTextField(30);

    private final JButton search;
    private final JButton goBack;


    public LoggedInSearchPageView(LoggedInSearchPageViewModel loggedInSearchViewModel) {
        this.loggedInSearchPageViewModel = loggedInSearchViewModel;

        //** Build Search bar **//
        final LabelTextPanel searchInfo = new LabelTextPanel(
                new JLabel(LoggedInSearchPageViewModel.SEARCH_BAR_LABEL), searchInputField);

        //** Build buttons bar **//
        final JPanel buttons = new JPanel();
        search = new JButton(LoggedInSearchPageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        goBack = new JButton(LoggedInSearchPageViewModel.GO_BACK_LABEL);
        buttons.add(goBack);

        //** Build View **//
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(searchInfo);
        this.add(buttons);

        goBack.addActionListener(
                e -> loggedInSearchPageController.switchToLoggedInHomePageView()
        );
    }



    public void setSearchPageController(LoggedInSearchPageController controller) {
        this.loggedInSearchPageController = controller;
    }

    public String getViewName() {return viewName;}

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
