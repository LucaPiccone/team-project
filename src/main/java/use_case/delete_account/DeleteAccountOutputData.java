package use_case.delete_account;

public class DeleteAccountOutputData {
    private final String username;

    public DeleteAccountOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
