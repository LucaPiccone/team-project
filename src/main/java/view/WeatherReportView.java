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
    private WeatherReportPageController weatherReportController;

    // Service
    private final ExportService exportService;
    private final NotificationService notificationService;
    private final ShareService shareService;
    private final WeatherDataService weatherDataService;

    // JLabels
    private final JLabel cityName;
    private final JLabel weather;
    private final JLabel temperature;
    private final JLabel feelsLike;
    private final JLabel humidity;

    // Buttons
    private final JButton backToHomeButton;
    private final JButton backToSearchButton;
    private final JButton addToFavouritesButton;

    public WeatherReportView(WeatherReportPageViewModel weatherReportViewModel) {
        this.weatherReportViewModel = weatherReportViewModel;
        this.weatherReportViewModel.addPropertyChangeListener(this);

        // Initialize Service
        this.exportService = new ExportService(false, false);
        this.notificationService = new NotificationService();
        this.shareService = new ShareService(false);
        this.weatherDataService = new WeatherDataService(false);

        // ---- Title ----
        final JLabel title = new JLabel("Weather Report");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        // ---- Labels ----
        cityName = new JLabel();
        cityName.setAlignmentX(Component.CENTER_ALIGNMENT);
        weather = new JLabel();
        weather.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperature = new JLabel();
        temperature.setAlignmentX(Component.CENTER_ALIGNMENT);
        feelsLike = new JLabel();
        feelsLike.setAlignmentX(Component.CENTER_ALIGNMENT);
        humidity = new JLabel();
        humidity.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---- Buttons ----
        backToHomeButton = new JButton(WeatherReportPageViewModel.TO_HOME_LABEL);
        backToSearchButton = new JButton(WeatherReportPageViewModel.TO_SEARCH_LABEL);
        backToHomeButton = new JButton(WeatherReportPageViewModel.TO_HOME_LABEL);
        addToFavouritesButton = new JButton(WeatherReportPageViewModel.FAVOURITE_LABEL);

        //Main Panel and Alignment
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(cityName);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(weather);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(temperature);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(feelsLike);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(humidity);
        mainPanel.add(Box.createVerticalStrut(15));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(backToSearchButton);
        buttonsPanel.add(backToHomeButton);
        buttonsPanel.add(addToFavouritesButton);

        // -----Layout-----
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(mainPanel);
        this.add(buttonsPanel);

        /**
         this.add(Box.createVerticalStrut(20));
         this.add(title);
         this.add(Box.createVerticalStrut(15));

         this.add(mainPanel);
         this.add(buttonsPanel);
         this.add(Box.createVerticalStrut(10));

         this.add(Box.createVerticalStrut(10));
         **/


        this.add(Box.createVerticalGlue());


        backToHomeButton.addActionListener(e ->
                weatherReportController.switchToLoggedInHomePageView()
        );
        backToSearchButton.addActionListener(e ->
                weatherReportController.switchToLoggedInSearchView()
        );
        addToFavouritesButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            try {
                weatherReportController.addToFavourites(state);
            } catch (CoordinatesFetcher.CityNotFoundException | WeatherDataFetcher.CityNotFoundException ex) {
                notificationService.showError("Failed to add to favourites: " + ex.getMessage());
            } catch (Exception ex) {
                notificationService.showError("Add to favourites failed: " + ex.getMessage());
            }
        });

        // export PDF
        exportPdfButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            WeatherData weatherData = buildWeatherDataFromState(state);
            if (weatherData == null) return;

            try {
                exportService.exportAsPdf(weatherData);
                notificationService.showSuccess("PDF exported successfully!");
            } catch (StorageException | DownloadPermissionException ex) {
                notificationService.showError(ex.getMessage());
            } catch (RuntimeException ex) {
                notificationService.showError("PDF export failed: " + ex.getMessage());
            }
        });

        // export Excel
        exportExcelButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            WeatherData weatherData = buildWeatherDataFromState(state);
            if (weatherData == null) return;

            try {
                exportService.exportAsExcel(weatherData);
                notificationService.showSuccess("Excel exported successfully!");
            } catch (StorageException | DownloadPermissionException ex) {
                notificationService.showError(ex.getMessage());
            } catch (RuntimeException ex) {
                notificationService.showError("Excel export failed: " + ex.getMessage());
            }
        });

        // share via Email
        shareEmailButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            WeatherData weatherData = buildWeatherDataFromState(state);
            if (weatherData == null) return;

        this.add(mainPanel);
        this.add(buttonsPanel);
        this.add(Box.createVerticalStrut(10));

        // share to Facebook
        shareFacebookButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            WeatherData weatherData = buildWeatherDataFromState(state);
            if (weatherData == null) return;

        this.add(Box.createVerticalGlue());

        backToHomeButton.addActionListener(
                e -> weatherReportController.switchToLoggedInHomePageView()
        );
        backToSearchButton.addActionListener(
                e -> weatherReportController.switchToLoggedInSearchView()
        );
        addToFavouritesButton.addActionListener(
                e -> {
                    WeatherReportPageState state = weatherReportViewModel.getState();
                    try {
                        weatherReportController.addToFavourites(state);
                    } catch (CoordinatesFetcher.CityNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (CityNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
        );

        weatherReportViewModel.addPropertyChangeListener(evt -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            cityName.setText("City Name: " + state.getCityName());
            weather.setText("Weather: " + state.getWeather());
            temperature.setText("Temperature: " + state.getTemperature());
            feelsLike.setText("Feels Like: " + state.getFeelsLike());
            humidity.setText("Humidity: " + state.getHumidity());
            if (state.getPopUpMessage() != null && !state.getPopUpMessage().isEmpty()) {
                JOptionPane.showMessageDialog(null, state.getPopUpMessage());
                weatherReportController.resetPopUpMessage();
            }
        });
    }


    private WeatherData buildWeatherDataFromState(WeatherReportPageState state) {
        if (state.getCityName() == null || state.getCityName().isEmpty()) {
            notificationService.showError("No weather data available!");
            return null;
        }

        Location location = new Location(
                "loc_" + state.getCityName(),
                state.getCityName()
        );

        String summary = String.format(
                "City Name: %s\nWeather: %s\nTemperature: %s\nFeels Like: %s\nHumidity: %s",
                state.getCityName(),
                state.getWeather(),
                state.getTemperature(),
                state.getFeelsLike(),
                state.getHumidity()
        );

        return new WeatherData(location, summary);
    }


    public void setWeatherReportController(WeatherReportPageController weatherReportController) {
        this.weatherReportController = weatherReportController;
    }

    public String getViewName() {return viewName; }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
