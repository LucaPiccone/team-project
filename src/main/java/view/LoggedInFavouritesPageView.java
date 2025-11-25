package view;

import entity.weatherReport.WeatherReport;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageController;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageState;
import interface_adapter.loggedInFavouritesPage.LoggedInFavouritesPageViewModel;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageController;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

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

        loggedInFavouritesPageViewModel.addPropertyChangeListener(e -> {
            this.removeAll();
            this.add(Box.createVerticalStrut(20));
            this.add(title);
            this.add(Box.createVerticalStrut(20));

            List<WeatherReport> weatherReports = loggedInFavouritesPageViewModel.getState().getWeatherReports();
            if (weatherReports.isEmpty()) {
                JLabel emptyLabel = new JLabel("You have no favourites.");
                emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                this.add(Box.createVerticalGlue());
                this.add(emptyLabel);
                this.add(Box.createVerticalGlue());
                this.add(buttons);
            } else {
                for (WeatherReport report : weatherReports) {
                    JPanel reportPanel = new JPanel();
                    reportPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // <-- Center content
                    JLabel reportLabel = new JLabel(report.getLocation() + ": " + report.getTemperature() + "°C");
                    JButton viewButton = new JButton("View Details");
                    viewButton.addActionListener(ev ->
                            loggedInFavouritesPageController.execute(report.getLocation())
                    );
                    reportPanel.add(reportLabel);
                    reportPanel.add(viewButton);
                    this.add(reportPanel);
                    this.add(buttons);
                }
            }

            this.revalidate();  // <-- Refresh the layout
            this.repaint();
        });

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
