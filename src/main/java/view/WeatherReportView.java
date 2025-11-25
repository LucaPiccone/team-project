package view;

import api.OpenWeatherApi.OpenWeatherApiDataFetcher;
import api.OpenWeatherApi.WeatherDataFetcher;
import api.geocodingapi.CoordinatesFetcher;
import api.geocodingapi.GeocodingApiCoordinatesFetcher;
import entity.weatherReport.WeatherReport;
import entity.weatherReport.WeatherReportFactory;
import interface_adapter.loggedInHomePage.LoggedInHomePageController;
import interface_adapter.loggedInSearchPage.LoggedInSearchPageController;
import interface_adapter.weatherReportPage.WeatherReportPageController;
import interface_adapter.weatherReportPage.WeatherReportPageViewModel;
import interface_adapter.weatherReportPage.WeatherReportPageState;
import api.OpenWeatherApi.WeatherDataFetcher.CityNotFoundException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class WeatherReportView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "Weather Report View";
    private final WeatherReportPageViewModel weatherReportViewModel;
    private WeatherReportPageController weatherReportController = null;

    //JLabels
    private final JLabel cityName;
    private final JLabel weather;
    private final JLabel temperature;
    private final JLabel feelsLike;
    private final JLabel humidity;

    // Buttons
    private final JButton backToHomeButton;
    private final JButton backToSearchButton;

    public WeatherReportView(WeatherReportPageViewModel weatherReportViewModel) {
        this.weatherReportViewModel = weatherReportViewModel;
        this.weatherReportViewModel.addPropertyChangeListener(this);

        // ----- Title -----
        final JLabel title = new JLabel("Weather Report");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        // Labels
        cityName = new JLabel();
        weather = new  JLabel();
        temperature = new JLabel();
        feelsLike = new JLabel();
        humidity = new JLabel();

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(cityName);
        mainPanel.add(weather);
        mainPanel.add(temperature);
        mainPanel.add(feelsLike);
        mainPanel.add(humidity);

        // ----- Buttons -----
        backToSearchButton = new JButton(WeatherReportPageViewModel.TO_SEARCH_LABEL);
        backToHomeButton = new JButton(WeatherReportPageViewModel.TO_HOME_LABEL);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(backToSearchButton);
        buttonsPanel.add(backToHomeButton);

        // ----- Layout -----
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(15));

        this.add(mainPanel);
        this.add(buttonsPanel);
        this.add(Box.createVerticalStrut(10));

        this.add(Box.createVerticalStrut(10));

        this.add(Box.createVerticalGlue());

        backToHomeButton.addActionListener(
                e -> weatherReportController.switchToLoggedInHomePageView()
        );
        backToSearchButton.addActionListener(
                e -> weatherReportController.switchToLoggedInSearchView()
        );

        weatherReportViewModel.addPropertyChangeListener(evt -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            cityName.setText("City Name: " + state.getCityName());
            weather.setText("Weather: " + state.getWeather());
            temperature.setText("Temperature: " + state.getTemperature());
            feelsLike.setText("Feels Like: " + state.getFeelsLike());
            humidity.setText("Humidity: " +state.getHumidity());
        });
    }

    public void setWeatherReportController(WeatherReportPageController weatherReportController) {
        this.weatherReportController = weatherReportController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
