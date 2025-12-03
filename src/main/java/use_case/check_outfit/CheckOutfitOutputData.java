package use_case.check_outfit;

public class CheckOutfitOutputData {
    private final String popUpMessage;

    public CheckOutfitOutputData(String popUpMessage) {
        this.popUpMessage = popUpMessage;
    }

    public String getPopUpMessage() { return popUpMessage; }
}
