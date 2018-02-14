package training.busboard.web;

import training.busboard.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BusInfo {

    private static final PostcodeClient postcodeClient = new PostcodeClient();
    private static final TFLClient tflClient = new TFLClient();

    private final String postcode;
    private final List<StopWithArrivals> stopsWithArrivals;

    public String getPostcode() {
        return postcode;
    }

    public List<StopWithArrivals> getStopsWithArrivals() {
        return stopsWithArrivals;
    }

    public BusInfo(String postcode) {
        this.postcode = postcode;

        Coordinates coordinates = postcodeClient.getCoordinate(postcode);
        if (coordinates == null) {
            this.stopsWithArrivals = null;
            return;
        }

        this.stopsWithArrivals = new ArrayList<>();
        List<StopPoint> nearbyStopPoints = tflClient.getNearbyStopPoints(coordinates);
        if (nearbyStopPoints.isEmpty()) {
            return;
        }

        Collections.sort(nearbyStopPoints, new Comparator<StopPoint>() {
            @Override
            public int compare(StopPoint o1, StopPoint o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });

        for (int i = 0; i < nearbyStopPoints.size() && i < 2; ++i) {
            StopPoint stop = nearbyStopPoints.get(i);
            List<ArrivalPrediction> predictions =
                    tflClient.getPredictions(stop.getNaptanId());
            Collections.sort(predictions, new Comparator<ArrivalPrediction>() {
                @Override
                public int compare(ArrivalPrediction ap1, ArrivalPrediction ap2) {
                    return ap1.getTimeToStation() - ap2.getTimeToStation();
                }
            });
            predictions = predictions.size() > 5 ? predictions.subList(0, 5) : predictions;
            stopsWithArrivals.add(new StopWithArrivals(stop.getCommonName(), predictions));
        }
    }

}
