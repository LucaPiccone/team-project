package entity.location;

import java.util.List;

public class Location {
    private final String name;
    private final double latitude;
    private final double longitude;

    /**
     * Creates a new location with the given non-empty name and non-empty password.
     * @param name the name of the city
     * @param latitude the latitude
     * @param longitude the longitude
     * @throws IllegalArgumentException if the name is empty
     */

    public Location(String name, double latitude, double longitude) {
        if ("".equals(name)) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}