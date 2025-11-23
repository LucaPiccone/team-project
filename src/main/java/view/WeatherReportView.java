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
    private LoggedInHomePageController loggedInHomePageController = null;
    private LoggedInSearchPageController loggedInSearchPageController = null;

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
        backToSearchButton = new JButton("Back to Search");
        backToHomeButton = new JButton("Home");

        // -- BUTTON EVENT LISTENER.
        backToSearchButton.addActionListener(this);
        backToHomeButton.addActionListener(this);

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
    }

    public String getViewName() {
        return viewName;
    }

    public void setWeatherReportController(WeatherReportPageController weatherReportController) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == backToSearchButton) {
            if (weatherReportController != null) {
                weatherReportController.switchToLoggedInSearchView();
            }
        } else if (source == backToHomeButton) {
            if (weatherReportController != null) {
                // you implement this in controller (change view to homepage)
                weatherReportController.switchToLoggedInHomePageView();
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("state")) {
            final WeatherReportPageState state = (WeatherReportPageState) evt.getNewValue();
            cityName.setText("Location: " + state.getCityName());
            CoordinatesFetcher coordinatesFetcher = new GeocodingApiCoordinatesFetcher();
            WeatherDataFetcher fetcher = new OpenWeatherApiDataFetcher();
            WeatherReportFactory factory = new WeatherReportFactory(fetcher, coordinatesFetcher);
            WeatherReport report = null;
            try {
                report = factory.create(state.getCityName());
            } catch (CityNotFoundException e) {
                throw new RuntimeException(e);
            } catch (CoordinatesFetcher.CityNotFoundException e) {
                throw new RuntimeException(e);
            }
            weather.setText("Weather: " + report.getWeather());
            temperature.setText("Temperature: " + report.getTemperature());
            feelsLike.setText("Feels like: " + report.getFeelsLike());
            humidity.setText("Humidity: " + report.getHumidity());
        }

    }
}
