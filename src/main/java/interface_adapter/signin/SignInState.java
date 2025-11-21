package interface_adapter.signin;

public class SignInState {
    public String getUsernameError() {
        return "SIGN IN STATE: username error";
    }

    public String getPasswordError() {
        return "sign in state: password error";
    }

    public String getRepeatPasswordError() {
        return "sign in state: repeat password error";
    }
}
