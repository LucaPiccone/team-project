package entity.location;

public class LocationFactory {
    public Location create(String name) {
        // placeholder => need to update using geocoding API to convert name to lat/long
        double latitude = 2.0;
        double longitude = 3.0;
        return new Location(name, latitude, longitude);
    }
}
