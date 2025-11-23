package use_case.currentWeather;

public class CurrentWeatherInteractor implements CurrentWeatherInputBoundary{
    private final CurrentWeatherOutputBoundary userPresenter;

    public CurrentWeatherInteractor(CurrentWeatherOutputBoundary currentWeatherOutputBoundary) {
        this.userPresenter = currentWeatherOutputBoundary;
    }

    @Override
    public void switchToLoggedInSearchView() {
        userPresenter.switchToLoggedInSearchView();
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }
}
