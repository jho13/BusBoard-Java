package training.busboard.web;

import training.busboard.*;

import java.util.List;

public class BusInfo {

    private final String postcode;
    private final List<StopWithArrivals> stopsWithArrivals;

    public String getPostcode() {
        return postcode;
    }

    public List<StopWithArrivals> getStopsWithArrivals() {
        return stopsWithArrivals;
    }

    public BusInfo(String postcode, List<StopWithArrivals> stopsWithArrivals) {
        this.postcode = postcode;
        this.stopsWithArrivals = stopsWithArrivals;
    }
}
