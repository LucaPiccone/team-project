package use_case.service;

import model.Location;
import model.WeatherData;
import org.junit.jupiter.api.Test;
import service.ExportService;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ExportServiceTest {

    @Test
    void testExportAsPdf_Success() throws Exception {
        Location location = new Location("loc001", "Beijing");

        WeatherData weatherData = new WeatherData(location, "Cloudy, 18°C");

        ExportService exportService = new ExportService(false, false);

        exportService.exportAsPdf(weatherData);

        String desktopPath = System.getProperty("user.home") + "/Desktop";
        File pdfFile = new File(desktopPath + "/weather_Beijing.pdf");
        assertTrue(pdfFile.exists(), "PDF file was not created on the desktop.");
    }
}