package use_case.signIn;

import entity.user.User;

/**
 * DAO interface for the Login Use Case.
 */
public interface SigninUserDataAccessInterface {

    boolean existsByName(String username);

    void save(User user);

    User get(String username);
}