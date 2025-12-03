package use_case.search_location;

public interface SearchOutputBoundary {
    void prepareSuccessView(SearchOutputData outputData);
    void prepareFailView(String errorMessage);
}
