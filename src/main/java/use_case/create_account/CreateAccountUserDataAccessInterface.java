package use_case.create_account;

import entity.user.User;

public interface CreateAccountUserDataAccessInterface {
    boolean existsByName(String username);

    void save(User user);
}