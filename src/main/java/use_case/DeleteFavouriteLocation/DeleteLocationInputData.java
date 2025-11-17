package use_case.DeleteFavouriteLocation;

public class DeleteLocationInputData {
    private final String cityName;
    private final String username;

    public DeleteLocationInputData(String username, String name) {
        this.cityName = name;
        this.username = username;
    }

    public String getCityName() {
        return cityName;
    }

    public String getUsername() {
        return username;
    }
}
