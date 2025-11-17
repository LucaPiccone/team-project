package interface_adapter.logged_in;

import use_case.change_password.ChangePasswordInputData;
import use_case.clothes_choose.ClothesChooseInputBoundary;
import use_case.clothes_choose.ClothesChooseInputData;


public class CheckOutfitController {

    private final ClothesChooseInputBoundary interactor;
    private final LoggedInViewModel loggedInViewModel;

    public CheckOutfitController(ClothesChooseInputBoundary interactor,
                                 LoggedInViewModel loggedInViewModel) {
        this.interactor = interactor;
        this.loggedInViewModel = loggedInViewModel;
    }

    public void execute(String outfit) {
        LoggedInState state = loggedInViewModel.getState();
        Double temp = state.getTodayTemperature();

        if (temp == null) {

            throw new IllegalStateException("No temperature available, please fetch weather first.");
        }

        ClothesChooseInputData inputData = new ClothesChooseInputData(temp, outfit);
        interactor.execute(inputData);
    }
}

