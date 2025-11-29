package view;

import interface_adapter.check_outfit.CheckOutfitController;
import interface_adapter.check_outfit.CheckOutfitViewModel;
import interface_adapter.homepage.HomePageViewModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CheckOutfitView extends JPanel implements PropertyChangeListener {
    private final String viewName = "Check Outfit View";

    private final JButton goBack;

    CheckOutfitViewModel checkOutfitViewModel;
    CheckOutfitController checkOutfitController = null;

    public CheckOutfitView(CheckOutfitViewModel checkOutfitViewModel) {
        this.checkOutfitViewModel = checkOutfitViewModel;

        //** Title **//
        final JLabel title = new JLabel(checkOutfitViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));

        //** BUTTONS **//
        final JPanel buttons = new JPanel();
        goBack = new JButton(checkOutfitViewModel.GO_BACK_LABEL);
        buttons.add(goBack);


        //** Build View **//
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(20));
        this.add(title);
        this.add(Box.createVerticalStrut(20)); // spacing under title
        this.add(buttons);

        goBack.addActionListener(e -> {

        });

    }

    void setCheckOutfitController(CheckOutfitController controller) {
        checkOutfitController = controller;
    }

    public String getViewName() {return viewName;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
