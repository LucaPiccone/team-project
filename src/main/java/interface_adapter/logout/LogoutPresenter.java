
package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.create_account.CreateAccountState;
import interface_adapter.create_account.CreateAccountViewModel;
import interface_adapter.logged_in_favourites_page.LoggedInFavouritesPageState;
import interface_adapter.logged_in_favourites_page.LoggedInFavouritesPageViewModel;
import interface_adapter.logged_in_home_page.LoggedInHomePageState;
import interface_adapter.logged_in_home_page.LoggedInHomePageViewModel;
import interface_adapter.logged_in_search_page.LoggedInSearchPageState;
import interface_adapter.logged_in_search_page.LoggedInSearchPageViewModel;
import interface_adapter.sign_in.SignInState;
import interface_adapter.sign_in.SignInViewModel;
import interface_adapter.weather_report_page.WeatherReportPageState;
import interface_adapter.weather_report_page.WeatherReportPageViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private CreateAccountViewModel createAccountViewModel;
    private LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel;
    private LoggedInHomePageViewModel loggedInHomePageViewModel;
    private LoggedInSearchPageViewModel loggedInSearchPageViewModel;
    private SignInViewModel signInViewModel;
    private WeatherReportPageViewModel weatherReportPageViewModel;
    private ViewManagerModel viewManagerModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           CreateAccountViewModel  createAccountViewModel,
                           LoggedInFavouritesPageViewModel loggedInFavouritesPageViewModel,
                           LoggedInHomePageViewModel loggedInHomePageViewModel,
                           LoggedInSearchPageViewModel  loggedInSearchPageViewModel,
                           SignInViewModel  signInViewModel,
                           WeatherReportPageViewModel weatherReportPageViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.createAccountViewModel = createAccountViewModel;
        this.loggedInFavouritesPageViewModel = loggedInFavouritesPageViewModel;
        this.loggedInHomePageViewModel = loggedInHomePageViewModel;
        this.loggedInSearchPageViewModel = loggedInSearchPageViewModel;
        this.signInViewModel = signInViewModel;
        this.weatherReportPageViewModel = weatherReportPageViewModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {

        final CreateAccountState state1 = createAccountViewModel.getState();
        final LoggedInFavouritesPageState state2 = loggedInFavouritesPageViewModel.getState();
        final LoggedInHomePageState state3 = loggedInHomePageViewModel.getState();
        final LoggedInSearchPageState state4 = loggedInSearchPageViewModel.getState();
        final SignInState state5 = signInViewModel.getState();
        final WeatherReportPageState state6 = weatherReportPageViewModel.getState();

        state1.clearState();
        createAccountViewModel.firePropertyChanged();

        state2.clearState();
        loggedInFavouritesPageViewModel.firePropertyChanged();

        state3.clearState();
        loggedInHomePageViewModel.firePropertyChanged();

        state4.clearSuggestions();
        loggedInSearchPageViewModel.firePropertyChanged();

        state6.clearState();
        loggedInFavouritesPageViewModel.firePropertyChanged();

        state5.setUsername(response.getUsername());
        state5.setPassword("");
        this.signInViewModel.firePropertyChanged();
        this.viewManagerModel.setState(signInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
