package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import training.busboard.models.ArrivalPrediction;
import training.busboard.models.Coordinates;
import training.busboard.models.StopPoint;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class TFLClient extends ApiClient {
    private static final String ARRIVALS_PATH = "StopPoint/{stopId}/Arrivals";
    private static final String STOP_POINT_PATH = "StopPoint";

    public List<StopPoint> getNearbyStopPoints(Coordinates coordinates) {
        return getClient()
                .path(STOP_POINT_PATH)
                .queryParam("stopTypes", "NaptanPublicBusCoachTram")
                .queryParam("lat", coordinates.getLatitude())
                .queryParam("lon", coordinates.getLongitude())
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(StopPointResult.class)
                .getStopPoints();
    }

    public List<ArrivalPrediction> getPredictions(String stopId) {
        return getClient()
                .path(ARRIVALS_PATH)
                .resolveTemplate("stopId", stopId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(new GenericType<List<ArrivalPrediction>>() {});
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class StopPointResult {
        private List<StopPoint> stopPoints;

        public List<StopPoint> getStopPoints() {
            return stopPoints;
        }
    }

    @Override
    protected String getApiUrl() {
        return "https://api.tfl.gov.uk";
    }
}
