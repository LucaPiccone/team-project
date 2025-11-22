package app;


import app.GUI.GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GUI gui = new GUI();
        JFrame application = gui
                .addHomePageView()
                .addCreateAccountView()
                .addSignInView()
                .addLoggedInFavouritePageView()
                .addLoggedInHomePageView()
                .addLoggedInSearchPageView()
                .addCreateAccountUseCases()
                .addHomePageUseCase()
                .addSignInViewUseCase()
                .addLoggedInSearchPageUseCases()
                .addLoggedInFavouritePageUseCases()
                .addLoggedInHomePageUseCases()
                .build();

        application.pack();
        application.setLocationRelativeTo(null);
        application.setVisible(true);
    }
}
