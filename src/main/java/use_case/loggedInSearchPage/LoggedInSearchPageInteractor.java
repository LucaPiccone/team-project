package use_case.loggedInSearchPage;

import api.googlePlacesAPI.GooglePlacesFetcher;
import api.googlePlacesAPI.PlaceFetcher;
import entity.placeSuggestions.PlaceSuggestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoggedInSearchPageInteractor implements LoggedInSearchPageInputBoundary {
    private final LoggedInSearchPageOutputBoundary userPresenter;
    private final GooglePlacesFetcher fetcher;

    public LoggedInSearchPageInteractor(LoggedInSearchPageOutputBoundary loggedInSearchPageOutputBoundary, GooglePlacesFetcher fetcher) {
        userPresenter = loggedInSearchPageOutputBoundary;
        this.fetcher = fetcher;
    }

    @Override
    public void switchToLoggedInHomePageView() {
        userPresenter.switchToLoggedInHomePageView();
    }

    @Override
    public void fetchSuggestions(LoggedInSearchPageAutoCompletedInputData inputData) throws PlaceFetcher.PlaceNotFoundException {
        String query = inputData.getUserInput();
        if (query == null || query.trim().isEmpty()) {
            // query is null
            userPresenter.showSuggestionsToUser(
                    new LoggedInSearchPageAutoCompletedOutputData(Collections.emptyList()));
            return;
        }

        try {
            List<PlaceSuggestion> entities = fetcher.getPlace(query.trim());

            List<LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO> dtos = new ArrayList<>();
            for (PlaceSuggestion s : entities) {
                dtos.add(new LoggedInSearchPageAutoCompletedOutputData.SuggestionDTO(
                        s.getPlaceId(),
                        s.getMainText(),
                        s.getSecondaryText()
                ));
            }

            userPresenter.showSuggestionsToUser(new LoggedInSearchPageAutoCompletedOutputData(dtos));

        } catch (PlaceFetcher.PlaceNotFoundException e) {
            userPresenter.presentError("No suggestions found.");
        } catch (RuntimeException e) {
            userPresenter.presentError("Autocomplete service unavailable.");
        }
    }
}