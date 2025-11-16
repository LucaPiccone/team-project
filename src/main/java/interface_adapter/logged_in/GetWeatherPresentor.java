package interface_adapter.logged_in;

import use_case.GetCurrentWeather.GetWeatherOutputBoundary;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.GetCurrentWeather.
public class GetWeatherPresenter implements GetWeatherOutputBoundary {

    private final LoggedInViewModel loggedInViewModel;

    public GetWeatherPresenter(LoggedInViewModel loggedInViewModel) {
        this.loggedInViewModel = loggedInViewModel;
    }

    @Override
    public void present(GetWeatherOutputData outputData) {
        LoggedInState state = loggedInViewModel.getState();

        state.setTodayTemperature(outputData.getTemperature());
        state.setTodayCondition(outputData.getCondition());

        loggedInViewModel.setState(state);
        loggedInViewModel.firePropertyChange("weather");
    }
}
