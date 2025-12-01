package use_case.deleteFavouriteLocation;

import entity.user.User;

/**
 * The DeleteLocation Interactor.
 */
public class DeleteLocationInteractor implements DeleteLocationInputBoundary {
    private final DeleteLocationUserDataAccessInterface userDataAccessObject;
    private final DeleteLocationOutputBoundary deleteLocationPresenter;

    public DeleteLocationInteractor(DeleteLocationOutputBoundary deleteLocationPresenter,
                                    DeleteLocationUserDataAccessInterface userDataAccessObject) {
        this.userDataAccessObject = userDataAccessObject;
        this.deleteLocationPresenter = deleteLocationPresenter;
    }

    @Override
    public void execute(DeleteLocationInputData inputData) {
        final User user = userDataAccessObject.get(userDataAccessObject.getCurrentUsername());
        if (user == null) {
            deleteLocationPresenter.prepareFailView("User not found");
            return;
        }

        if (!user.getLocations().contains(inputData.getCityName())) {
            deleteLocationPresenter.prepareFailView("Location was not favourite");
            return;
        }

        user.removeLocation(inputData.getCityName());

        userDataAccessObject.save(user);
        final DeleteLocationOutputData outputData = new DeleteLocationOutputData(user.getLocations());
        deleteLocationPresenter.prepareSuccessView(outputData);
    }
}
