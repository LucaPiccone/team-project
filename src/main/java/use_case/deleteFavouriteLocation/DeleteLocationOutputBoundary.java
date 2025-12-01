package use_case.deleteFavouriteLocation;

/**
 * The output boundary for the Delete Favourite Location Use Case.
 */
public interface DeleteLocationOutputBoundary {
    /**
     * Prepares the view model for a successful deletion.
     *
     * @param outputData data containing remaining favourites or other info
     */
    void prepareSuccessView(DeleteLocationOutputData outputData);

    /**
     * Prepares the view model for a failed deletion attempt.
     *
     * @param errorMessage explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
