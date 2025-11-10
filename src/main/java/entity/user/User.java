package entity.user;

public class User {

    private final String name;
    private final String password;
    private final List<Location> locations;

    /**
    * Creates a new user with the given non-empty name and non-empty password.
    * @param name the username
    * @param password the password
    * @param locations the saved locations
    * @throws IllegalArgumentException if the password or name are empty
    */
    public User(String name, String password) {
        if ("".equals(name)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if ("".equals(password)) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        this.name = name;
        this.password = password;
    }
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public List<Location> getLocations() {
        return locations;
    }
}