package use_case.signin;

/**
 * Output Data for the Login Use Case.
 */
public class SigninOutputData {

    private final String username;

    public SigninOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}