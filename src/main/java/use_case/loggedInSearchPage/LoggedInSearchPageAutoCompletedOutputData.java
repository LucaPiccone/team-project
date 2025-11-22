package use_case.loggedInSearchPage;

import java.util.List;

public class LoggedInSearchPageAutoCompletedOutputData {
    public static class SuggestionDTO {
        private final String placeId;
        private final String mainText;
        private final String secondaryText;

        public SuggestionDTO(String placeId, String mainText, String secondaryText) {
            this.placeId = placeId;
            this.mainText = mainText;
            this.secondaryText = secondaryText;
        }

        public String getPlaceId() { return placeId; }

        public String getMainText() { return mainText; }

        public String getSecondaryText() { return secondaryText; }
    }

    private final List<SuggestionDTO> suggestions;

    public LoggedInSearchPageAutoCompletedOutputData(List<SuggestionDTO> suggestions) {
        this.suggestions = suggestions;
    }

    public List<SuggestionDTO> getSuggestions() {
        return suggestions;
    }
}
