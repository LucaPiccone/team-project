package api.googlePlacesAPI;

import entity.placeSuggestions.PlaceSuggestion;
import org.json.JSONObject;

import java.util.List;

public interface PlaceFetcher {
    List<PlaceSuggestion> getPlace(String place) throws PlaceNotFoundException;

    class PlaceNotFoundException extends Exception {
        public PlaceNotFoundException(String place) {
            super("Place not found: " + place);
        }
    }


}
