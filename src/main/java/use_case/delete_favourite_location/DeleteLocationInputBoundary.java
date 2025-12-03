package use_case.delete_favourite_location;

/**
 * Input Boundary for actions which are related to deleting a favourite location.
 */
public interface DeleteLocationInputBoundary {
    /**
     * Executes the delete location request.
     *
     * @param deleteInputData data needed to remove the location
     */
    void execute(DeleteLocationInputData deleteInputData);
}
