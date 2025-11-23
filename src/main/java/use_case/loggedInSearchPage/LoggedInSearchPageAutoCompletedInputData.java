package use_case.loggedInSearchPage;

public class LoggedInSearchPageAutoCompletedInputData {
    private final String userInput;

    public LoggedInSearchPageAutoCompletedInputData(String userInput) {
        this.userInput = userInput;
    }

    public String getUserInput() {
        return userInput;
    }
}
