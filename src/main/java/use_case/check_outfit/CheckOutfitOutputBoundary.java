package use_case.check_outfit;

public interface CheckOutfitOutputBoundary {
    void prepareSuccessView(CheckOutfitOutputData outputData);
    void prepareFailView(String error);
    void goBack();
}
