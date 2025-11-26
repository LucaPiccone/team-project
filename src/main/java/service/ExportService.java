package service;

import exception.DownloadPermissionException;
import exception.StorageException;
import model.WeatherData;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExportService {

    private final boolean simulateLowStorage;
    private final boolean simulateNoPermission;

    public ExportService(boolean simulateLowStorage, boolean simulateNoPermission) {
        this.simulateLowStorage = simulateLowStorage;
        this.simulateNoPermission = simulateNoPermission;
    }

    public void exportAsPdf(WeatherData data)
            throws StorageException, DownloadPermissionException {

        check();

        String filename = "weather_" + data.getLocation().getName() + ".pdf";

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();
            document.add(new Paragraph("weather data export"));
            document.add(new Paragraph("-------------------------"));
            document.add(new Paragraph(data.getSummary()));
            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Fail to generate pdf: " + e.getMessage(), e);
        }
    }

    public void exportAsExcel(WeatherData data)
            throws StorageException, DownloadPermissionException {

        check();

        String filename = "weather_" + data.getLocation().getName() + ".xlsx";

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("data weather");

            Row head = sheet.createRow(0);
            head.createCell(0).setCellValue("location");
            head.createCell(1).setCellValue("summary");

            Row row = sheet.createRow(1);
            row.createCell(0).setCellValue(data.getLocation().getName());
            row.createCell(1).setCellValue(data.getSummary());

            try (FileOutputStream out = new FileOutputStream(filename)) {
                workbook.write(out);
            }
        } catch (IOException e) {
            throw new RuntimeException("Fail to generate Excel: " + e.getMessage(), e);
        }
    }

    private void check() throws StorageException, DownloadPermissionException {
        if (simulateLowStorage) {
            throw new StorageException("Insufficient storage space.Please free up space and try again.");
        }
        if (simulateNoPermission) {
            throw new DownloadPermissionException("Download permission not enabled.Please check your browser or system settings.");
        }
    }
}
