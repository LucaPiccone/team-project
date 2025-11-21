package data_access;

import entity.user.User;
import entity.user.UserFactory;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObjectWithLocations implements SignupUserDataAccessInterface,
        LoginUserDataAccessInterface {

    // CSV header
    private static final String HEADER = "username,password,locations,token";

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();

    private final UserFactory userFactory;

    private String currentUsername = null;

    public FileUserDataAccessObjectWithLocations(String csvPath, UserFactory userFactory) {
        this.userFactory = userFactory;
        this.csvFile = new File(csvPath);

        // order：username, password, locations, token
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("locations", 2);
        headers.put("token", 3);

        // if file is empty, just write header
        if (csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                final String header = reader.readLine();

                if (!HEADER.equals(header)) {
                    throw new RuntimeException(String.format(
                            "header should be%n: %s%n but was:%n%s", HEADER, header));
                }

                String row;
                while ((row = reader.readLine()) != null) {
                    final String[] col = row.split(",", -1);

                    final String username = col[headers.get("username")];
                    final String password = col[headers.get("password")];

                    // locations
                    final String locationsStr = col[headers.get("locations")];
                    List<String> locations;
                    if (locationsStr == null || locationsStr.isEmpty()) {
                        locations = new ArrayList<>();
                    } else {
                        locations = new ArrayList<>(Arrays.asList(locationsStr.split(";")));
                    }

                    // token
                    final String token = col[headers.get("token")];

                    final User user = userFactory.create(username, password, locations, token);
                    accounts.put(username, user);
                }

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Write all users back to CSV (header + data).
     */
    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {

            // header: username,password,locations,token
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String locationsField = String.join(";", user.getLocations());
                String tokenField = user.getToken();

                final String line = String.format("%s,%s,%s,%s",
                        user.getName(),
                        user.getPassword(),
                        locationsField,
                        tokenField);

                writer.write(line);
                writer.newLine();
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    public void setCurrentUsername(String name) {
        currentUsername = name;
    }

    @Override
    public String getCurrentUsername() {
        return currentUsername;
    }
}
