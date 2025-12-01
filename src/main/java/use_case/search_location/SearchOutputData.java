package use_case.search_location;

/**
 * Output Data for the Search Use Case
 */
public class SearchOutputData {
    private final String cityName;
    public SearchOutputData(String cityName) {
        this.cityName = cityName;
    }
    public String getCityName() {
        return cityName;
    }
}
