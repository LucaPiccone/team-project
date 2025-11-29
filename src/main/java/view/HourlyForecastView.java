package view;

import interface_adapter.homepage.HomePageController;
import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.hourly_forecast.HourlyForecastController;
import interface_adapter.hourly_forecast.HourlyForecastViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HourlyForecastView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Hourly Forecast View";

    HourlyForecastViewModel hourlyForecastViewModel;
    private HourlyForecastController hourlyForecastController = null;

    JButton goBack;

    public HourlyForecastView(HourlyForecastViewModel hourlyForecastViewModel) {
        this.hourlyForecastViewModel = hourlyForecastViewModel;

        //** Title**//
        final JLabel title = new JLabel("TITLE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        //** BUTTONS **//
        final JPanel buttons = new JPanel();
        goBack = new JButton("Go Back");
        buttons.add(goBack);



        //** View **//
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(20)); // spacing under title
        this.add(buttons);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setHourlyForecastController(HourlyForecastController hourlyForecastController) {
        this.hourlyForecastController = hourlyForecastController;
    }
}
