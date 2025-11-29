package view;

import interface_adapter.hourly_forecast.HourlyForecastController;
import interface_adapter.hourly_forecast.HourlyForecastState;
import interface_adapter.hourly_forecast.HourlyForecastViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HourlyForecastView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Hourly Forecast View";

    private final JLabel title;
    private final JButton goBack;

    HourlyForecastViewModel hourlyForecastViewModel;
    HourlyForecastController hourlyForecastController = null;

    public HourlyForecastView(HourlyForecastViewModel hourlyForecastViewModel) {
        this.hourlyForecastViewModel = hourlyForecastViewModel;

        //** Title **//
        title = new JLabel(HourlyForecastViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        //** BUTTONS **//
        final JPanel buttons = new JPanel();
        goBack = new JButton(HourlyForecastViewModel.GO_BACK_LABEL);
        buttons.add(goBack);


        //** Build View **//
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(20)); // spacing under title
        this.add(buttons);

        goBack.addActionListener(e -> {
            HourlyForecastState state = hourlyForecastViewModel.getState();
            hourlyForecastController.switchToWeatherReportView(state.getCityName());
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public void setHourlyForecastController(HourlyForecastController hourlyForecastController) {
        this.hourlyForecastController = hourlyForecastController;
    }

    public String getViewName() {return viewName;}

}
