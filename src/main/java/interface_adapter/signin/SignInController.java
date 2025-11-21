package interface_adapter.signin;

public class SignInController {
    private SignInViewModel signInViewModel;

    public SignInController(SignInViewModel signInViewModel) {
        this.signInViewModel = signInViewModel;
    }

    public void execute(String username, String password, String repeatPassword) {

    }

    public void switchToHomePage() {

    }
}
