package interface_adapter.sign_in;

import interface_adapter.ViewModel;

public class SignInViewModel extends ViewModel<SignInState> {

    public SignInViewModel() {
        super("Sign in view");
        setState(new SignInState());
    }
}
