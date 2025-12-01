package api.googlePlacesAPI;

import entity.place_suggestions.PlaceSuggestion;

import java.util.List;

public interface PlaceFetcher {
    List<PlaceSuggestion> getPlace(String placeId) throws PlaceNotFoundException;

    class PlaceNotFoundException extends Exception {
        public PlaceNotFoundException(String placeId) {
            super("Place not found: " + placeId);
        }
    }


}
