package interface_adapter.loggedInSearchPage;

import use_case.loggedInSearchPage.LoggedInSearchPageAutoCompletedOutputData;

import java.util.ArrayList;
import java.util.List;

public class LoggedInSearchPageState {
    // Current list of autocomplete suggestions
    private List<LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO> suggestions = new ArrayList<>();
    private String errorMessage = "";

    public List<LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO> suggestions) {
        this.suggestions = suggestions;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
