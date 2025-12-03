package interface_adapter.check_outfit;

import use_case.check_outfit.CheckOutfitInputBoundary;
import use_case.check_outfit.CheckOutfitInputData;

public class CheckOutfitController {
    private final CheckOutfitInputBoundary interactor;

    public CheckOutfitController(CheckOutfitInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String outfitId, String weatherMain, String temperature) {
        interactor.execute(new CheckOutfitInputData(outfitId, weatherMain, temperature));
    }

    public void goBack() {
        interactor.goBack();
    }
}
