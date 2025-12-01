package use_case.check_outfit;

public interface CheckOutfitInputBoundary {
    void execute(CheckOutfitInputData inputData);

    void goBack();
}
