package interface_adapter.check_outfit;

import interface_adapter.ViewModel;

public class CheckOutfitViewModel extends ViewModel<CheckOutfitState> {

    public CheckOutfitViewModel() {
        super("Checkout Fit View");
        setState(new CheckOutfitState());
    }
}
