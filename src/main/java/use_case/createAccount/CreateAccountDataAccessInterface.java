package use_case.createAccount;

import entity.user.User;

public interface CreateAccountDataAccessInterface {
    boolean existsByName(String username);

    void save(User user);
}