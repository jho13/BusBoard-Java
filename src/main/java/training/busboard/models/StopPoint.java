package training.busboard.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StopPoint {
    private String naptanId;
    private String commonName;
    private int distance;

    private StopPoint() {}

    public String getNaptanId() {
        return naptanId;
    }

    public String getCommonName() {
        return commonName;
    }

    public int getDistance() {
        return distance;
    }
}
