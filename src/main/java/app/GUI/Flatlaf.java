package app.GUI;

import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

public class Flatlaf {
    public static void init() {
        FlatDarculaLaf.setup();
        UIManager.put( "ScrollBar.showButtons", true );

        // Button Styles
        UIManager.put("Button.arc", 12);
        UIManager.put("Button.innerFocusWidth", 1);
        UIManager.put("Button.minimumWidth", 90);
        UIManager.put("Button.margin", new Insets(6, 14, 6, 14));


        // Text Field styles
        UIManager.put("TextField.arc", 10);
        UIManager.put("TextField.margin", new Insets(6, 10, 6, 10));
        UIManager.put("TextField.margin", new Insets(6, 10, 6, 10));
        UIManager.put("PasswordField.margin", new Insets(6, 10, 6, 10));
        UIManager.put("ComboBox.margin", new Insets(6, 10, 6, 10));

    }
}
