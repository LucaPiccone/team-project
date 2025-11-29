package use_case.service;


import exception.ShareAppNotFoundException;
import model.Location;
import model.WeatherData;
import org.junit.jupiter.api.Test;
import service.ShareService;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ShareServiceTest {

    @Test
    void shareByEmail_Success() throws Exception {
        Location location = new Location("loc002", "Shanghai");
        WeatherData weatherData = new WeatherData(location, "Sunny, 28°C");

        ShareService shareService = new ShareService(false);

        shareService.shareByEmail(weatherData);
    }

    @Test
    void shareByEmail_WhenNoApp_ThrowException() {
        Location location = new Location("loc003", "Guangzhou");
        WeatherData weatherData = new WeatherData(location, "Rainy, 22°C");

        ShareService shareService = new ShareService(true);

        ShareAppNotFoundException exception = assertThrows(ShareAppNotFoundException.class, () -> {
            shareService.shareByEmail(weatherData);
        });
        assert exception.getMessage().contains("No available email app detected");
    }
}