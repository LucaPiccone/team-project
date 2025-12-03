package use_case.delete_favourite_location;

import data_access.InMemoryUserDataAccessObject;
import entity.user.User;
import entity.user.UserFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteLocationInteractorTest {

    @Test
    void successfulDeletionTest() {
        UserFactory factory = new UserFactory();
        User user = factory.create("username", "password");
        user.addLocation("Toronto");
        user.addLocation("London");

        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);

        userRepository.setCurrentUsername(user.getName());

        DeleteLocationInputBoundary interactor = getDeleteLocationInputBoundary(userRepository);

        DeleteLocationInputData inputData = new DeleteLocationInputData("Toronto");
        interactor.execute(inputData);

        User updatedUser = userRepository.get(user.getName());
        List<String> remaining = updatedUser.getLocations();
        assertEquals(1, remaining.size());
        assertFalse(remaining.contains("Toronto"));
        assertTrue(remaining.contains("London"));
    }

    @Test
    void deletionFailsWhenLocationNotInList() {
        UserFactory factory = new UserFactory();
        User user = factory.create("username", "password");
        user.addLocation("Toronto");

        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();
        userRepository.save(user);
        userRepository.setCurrentUsername(user.getName());

        DeleteLocationInputBoundary interactor
                = getFailDeleteLocationInputBoundary("Location was not favourite", userRepository);

        DeleteLocationInputData inputData = new DeleteLocationInputData("London");
        interactor.execute(inputData);
    }

    @NotNull
    private static DeleteLocationInputBoundary getFailDeleteLocationInputBoundary(String locationNotFound,
                                                                                  InMemoryUserDataAccessObject
                                                                                          userRepository) {
        DeleteLocationOutputBoundary presenter = new DeleteLocationOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteLocationOutputData outputData) {
                fail("Should not succeed");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals(locationNotFound, error);
            }
        };

        return new DeleteLocationInteractor(presenter, userRepository);
    }

    @Test
    void deletionFailsWhenNoCurrentUser() {
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        DeleteLocationInputBoundary interactor = getFailDeleteLocationInputBoundary("User not found", userRepository);

        DeleteLocationInputData inputData = new DeleteLocationInputData("Toronto");
        interactor.execute(inputData);
    }

    @Test
    void outputDataGetterTest() {
        List<String> list = List.of("Toronto", "London");
        DeleteLocationOutputData data = new DeleteLocationOutputData(list);

        assertEquals(list, data.getRemainingLocations());
    }

    @NotNull
    private static DeleteLocationInputBoundary getDeleteLocationInputBoundary(DeleteLocationUserDataAccessInterface userRepository) {
        DeleteLocationOutputBoundary presenter = new DeleteLocationOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteLocationOutputData outputData) {
                // nothing should happen...
            }

            @Override
            public void prepareFailView(String error) {
                fail("Deletion should succeed, failure is unexpected");
            }
        };

        return new DeleteLocationInteractor(presenter, userRepository);
    }
}
