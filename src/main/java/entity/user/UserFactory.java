package entity.user;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    public User create(String name, String password, String token) {
        return new User(name, password, token);
    }

    public User create(String name, String password, List<String> locations, String token) {

        if (locations == null) {
            locations = new ArrayList<>();
        }
        return new User(name, password, locations, token);
    }
}
