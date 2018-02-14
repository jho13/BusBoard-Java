package training.busboard.web;

import training.busboard.models.ArrivalPrediction;

import java.util.List;

public class StopWithArrivals {
    private String name;
    private List<ArrivalPrediction> arrivals;

    public String getName() {
        return name;
    }

    public List<ArrivalPrediction> getArrivals() {
        return arrivals;
    }

    public StopWithArrivals(String commonName, List<ArrivalPrediction> arrivals) {
        this.name = commonName;
        this.arrivals = arrivals;
    }
}
