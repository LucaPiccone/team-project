package use_case.delete_favourite_location;

import entity.user.User;

/**
 * DAO interface for the Delete Favourite Location Use Case.
 */
public interface DeleteLocationUserDataAccessInterface {

    /**
     * Saves the updated user data.
     *
     * @param user the user to save
     */
    void save(User user);

    /**
     * Retrieves a user by username.
     *
     * @param username the username to look up
     * @return the user with that username
     */
    User get(String username);

    /**
     * Returns the username of the currently logged-in user.
     *
     * @return the active username
     */
    String getCurrentUsername();
}
