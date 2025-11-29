package interface_adapter.check_outfit;

import interface_adapter.ViewModel;

public class CheckOutfitViewModel extends ViewModel<CheckOutfitState> {
    public static final String GO_BACK_LABEL = "Go Back";
    public static final String TITLE_LABEL = "Check Outfit";

    public CheckOutfitViewModel() {
        super("Check Outfit View");
    }
}
