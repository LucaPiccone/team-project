package view;

import interface_adapter.weatherReportPage.WeatherReportPageController;
import interface_adapter.weatherReportPage.WeatherReportPageViewModel;
import interface_adapter.weatherReportPage.WeatherReportPageState;
import api.geocodingapi.CoordinatesFetcher;
import api.OpenWeatherApi.WeatherDataFetcher;
import model.Location;
import model.WeatherData;
import service.ExportService;
import service.NotificationService;
import service.ShareService;
import service.WeatherDataService;
import exception.StorageException;
import exception.DownloadPermissionException;
import exception.ShareAppNotFoundException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class WeatherReportView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Weather Report View";
    private final WeatherReportPageViewModel weatherReportViewModel;
    private WeatherReportPageController weatherReportController;

    // 注入所有Service
    private final ExportService exportService;
    private final NotificationService notificationService;
    private final ShareService shareService;
    private final WeatherDataService weatherDataService;

    // 界面组件
    private final JLabel cityName;
    private final JLabel weather;
    private final JLabel temperature;
    private final JLabel feelsLike;
    private final JLabel humidity;
    private final JButton backToHomeButton;
    private final JButton backToSearchButton;
    private final JButton addToFavouritesButton;
    private final JButton exportPdfButton;
    private final JButton exportExcelButton;
    private final JButton shareEmailButton;
    private final JButton shareFacebookButton;


    public WeatherReportView(WeatherReportPageViewModel weatherReportViewModel) {
        this.weatherReportViewModel = weatherReportViewModel;
        this.weatherReportViewModel.addPropertyChangeListener(this);

        // 初始化Service
        this.exportService = new ExportService(false, false);
        this.notificationService = new NotificationService();
        this.shareService = new ShareService(false);
        this.weatherDataService = new WeatherDataService(false);

        // ---- 标题 ----
        JLabel title = new JLabel("Weather Report");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        // ---- 天气数据标签 ----
        cityName = new JLabel();
        weather = new JLabel();
        temperature = new JLabel();
        feelsLike = new JLabel();
        humidity = new JLabel();
        // 统一对齐
        cityName.setAlignmentX(Component.CENTER_ALIGNMENT);
        weather.setAlignmentX(Component.CENTER_ALIGNMENT);
        temperature.setAlignmentX(Component.CENTER_ALIGNMENT);
        feelsLike.setAlignmentX(Component.CENTER_ALIGNMENT);
        humidity.setAlignmentX(Component.CENTER_ALIGNMENT);

        // ---- 按钮初始化 ----
        backToHomeButton = new JButton(WeatherReportPageViewModel.TO_HOME_LABEL);
        backToSearchButton = new JButton(WeatherReportPageViewModel.TO_SEARCH_LABEL);
        addToFavouritesButton = new JButton(WeatherReportPageViewModel.FAVOURITE_LABEL);
        exportPdfButton = new JButton("Export as PDF");
        exportExcelButton = new JButton("Export as Excel");
        shareEmailButton = new JButton("Share via Email");
        shareFacebookButton = new JButton("Share to Facebook");


        // ---- 布局 ----
        // 数据面板
        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.add(Box.createVerticalStrut(10));
        dataPanel.add(cityName);
        dataPanel.add(Box.createVerticalStrut(10));
        dataPanel.add(weather);
        dataPanel.add(Box.createVerticalStrut(10));
        dataPanel.add(temperature);
        dataPanel.add(Box.createVerticalStrut(10));
        dataPanel.add(feelsLike);
        dataPanel.add(Box.createVerticalStrut(10));
        dataPanel.add(humidity);
        dataPanel.add(Box.createVerticalStrut(15));

        // 按钮面板
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(backToSearchButton);
        buttonsPanel.add(backToHomeButton);
        buttonsPanel.add(addToFavouritesButton);
        buttonsPanel.add(exportPdfButton);
        buttonsPanel.add(exportExcelButton);
        buttonsPanel.add(shareEmailButton);
        buttonsPanel.add(shareFacebookButton);

        // 主布局
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(dataPanel);
        this.add(buttonsPanel);
        this.add(Box.createVerticalGlue());


        // ---- 按钮逻辑 ----
        // 返回主页
        backToHomeButton.addActionListener(e ->
                weatherReportController.switchToLoggedInHomePageView()
        );

        // 返回搜索页
        backToSearchButton.addActionListener(e ->
                weatherReportController.switchToLoggedInSearchView()
        );

        // 收藏按钮（已修复参数不匹配）
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

        // 导出PDF
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

        // 导出Excel
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

        // 分享到Email
        shareEmailButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            WeatherData weatherData = buildWeatherDataFromState(state);
            if (weatherData == null) return;

            try {
                shareService.shareByEmail(weatherData);
                notificationService.showSuccess("Shared via Email!");
            } catch (ShareAppNotFoundException ex) {
                notificationService.showError(ex.getMessage());
            }
        });

        // 分享到Facebook
        shareFacebookButton.addActionListener(e -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            WeatherData weatherData = buildWeatherDataFromState(state);
            if (weatherData == null) return;

            try {
                shareService.shareToFacebook(weatherData);
                notificationService.showSuccess("Shared to Facebook!");
            } catch (ShareAppNotFoundException ex) {
                notificationService.showError(ex.getMessage());
            }
        });


        // ---- 监听ViewModel数据更新 ----
        weatherReportViewModel.addPropertyChangeListener(evt -> {
            WeatherReportPageState state = weatherReportViewModel.getState();
            cityName.setText("City Name: " + state.getCityName());
            weather.setText("Weather: " + state.getWeather());
            temperature.setText("Temperature: " + state.getTemperature());
            feelsLike.setText("Feels Like: " + state.getFeelsLike());
            humidity.setText("Humidity: " + state.getHumidity());

            // 弹窗提示
            if (state.getPopUpMessage() != null && !state.getPopUpMessage().isEmpty()) {
                JOptionPane.showMessageDialog(null, state.getPopUpMessage());
                weatherReportController.resetPopUpMessage();
            }
        });
    }


    // 工具方法：从State构造WeatherData
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


    // 原有方法
    public void setWeatherReportController(WeatherReportPageController weatherReportController) {
        this.weatherReportController = weatherReportController;
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {}
}
