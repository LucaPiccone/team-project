package view;

import interface_adapter.homepage.HomePageViewModel;
import interface_adapter.weatherReport.WeatherReportViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class WeatherReportView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Weather report view";

    public WeatherReportView(WeatherReportViewModel weatherReportViewModel) {

        final JLabel title = new JLabel(HomePageViewModel.WELCOME_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
