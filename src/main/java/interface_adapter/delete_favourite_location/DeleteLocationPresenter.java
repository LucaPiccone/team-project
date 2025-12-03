package interface_adapter.delete_favourite_location;

import interface_adapter.weather_report_page.WeatherReportPageViewModel;
import use_case.delete_favourite_location.DeleteLocationOutputBoundary;
import use_case.delete_favourite_location.DeleteLocationOutputData;

public class DeleteLocationPresenter implements DeleteLocationOutputBoundary {

    private final WeatherReportPageViewModel viewModel;

    public DeleteLocationPresenter(WeatherReportPageViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(DeleteLocationOutputData data) {
        // For example, update a list of favourites inside the WeatherReportPageState
        viewModel.getState().setFavouriteLocations(data.getRemainingLocations());
        viewModel.getState().setPopUpMessage("This location was successfully removed!");
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.getState().setPopUpMessage(errorMessage);
        viewModel.firePropertyChanged();
    }
}