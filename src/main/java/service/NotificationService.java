package service;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class NotificationService {

    public void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Failure");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
