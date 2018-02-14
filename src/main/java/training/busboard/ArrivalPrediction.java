package training.busboard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArrivalPrediction {

    private String lineName;
    private String destinationName;
    private int timeToStation;

    public String getLineName() {
        return lineName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public int getTimeToStation() {
        return timeToStation;
    }

    private ArrivalPrediction() {}

    @Override
    public String toString() {
        return String.format("%-15s%-35s%-25d",
                getLineName(), getDestinationName(), Math.round(getTimeToStation() / 60.0));
    }
}
