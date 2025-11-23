package view;

import api.googlePlacesAPI.PlaceFetcher;
import interface_adapter.loggedInHomePage.LoggedInHomePageState;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageController;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageState;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageViewModel;
import jdk.jshell.SourceCodeAnalysis;
import use_case.loggedInSearchPage.LoggedInSearchPageAutoCompletedOutputData;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class LoggedInSearchPageView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Logged In Search View";
    private final LoggedInSearchPageViewModel loggedInSearchPageViewModel;
    LoggedInSearchPageController loggedInSearchPageController = null;

    private final JTextField searchInputField = new JTextField(30);
    private final JPopupMenu suggestionsPopup = new JPopupMenu();


    private final JButton search;
    private final JButton goBack;


    public LoggedInSearchPageView(LoggedInSearchPageViewModel loggedInSearchViewModel) {
        this.loggedInSearchPageViewModel = loggedInSearchViewModel;

        //** Build Search bar **//
        final LabelTextPanel searchInput = new LabelTextPanel(
                new JLabel(LoggedInSearchPageViewModel.SEARCH_BAR_LABEL), searchInputField);

        //** Build buttons bar **//
        final JPanel buttons = new JPanel();
        search = new JButton(LoggedInSearchPageViewModel.SEARCH_BUTTON_LABEL);
        buttons.add(search);
        goBack = new JButton(LoggedInSearchPageViewModel.GO_BACK_LABEL);
        buttons.add(goBack);

        //** Build View **//
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(searchInput);
        this.add(buttons);

        goBack.addActionListener(
                e -> loggedInSearchPageController.switchToLoggedInHomePageView()
        );

        searchInputField.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
                String query = searchInputField.getText().trim();
                if (!query.isEmpty()) {
                    try {
                        loggedInSearchPageController.fetchSuggestions(query);
                    } catch (PlaceFetcher.PlaceNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    loggedInSearchPageController.clearSuggestions();
                }
            }
        });

        this.loggedInSearchPageViewModel.addPropertyChangeListener(this);

    }

    private void showSuggestions(
            List<LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO> suggestions) {

        suggestionsPopup.removeAll();

        for (LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO s : suggestions) {
            JMenuItem item = new JMenuItem(s.getMainText());
            item.addActionListener(ae -> {
                searchInputField.setText(s.getMainText());
                suggestionsPopup.setVisible(false);
            });
            suggestionsPopup.add(item);
        }

        if (!suggestions.isEmpty()) {
            suggestionsPopup.show(searchInputField, 0, searchInputField.getHeight());
        } else {
            suggestionsPopup.setVisible(false);
        }
    }

    public void setSearchPageController(LoggedInSearchPageController controller) {
        this.loggedInSearchPageController = controller;
    }

    public String getViewName() {return viewName;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (!"state".equals(evt.getPropertyName())) return;

        LoggedInSearchPageState state = loggedInSearchPageViewModel.getState();
        List<LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO> suggestions = state.getSuggestions();
        SwingUtilities.invokeLater(() -> showSuggestions(suggestions));
    }
}
