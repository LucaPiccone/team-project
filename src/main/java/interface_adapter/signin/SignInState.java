package interface_adapter.signin;

public class SignInState {
    private String SigninError;

    public String getUsernameError() {
        return "SIGN IN STATE: username error";
    }

    public String getPasswordError() {
        return "sign in state: password error";
    }

    public String getSigninError() {
        return SigninError;
    }

    public void setSigninError(String usernameError) {
        this.SigninError = usernameError;
    }

}
