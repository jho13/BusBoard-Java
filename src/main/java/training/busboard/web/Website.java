package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.*;
import training.busboard.models.ArrivalPrediction;
import training.busboard.models.Coordinates;
import training.busboard.models.StopPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class Website {

    private static final PostcodeClient postcodeClient = new PostcodeClient();
    private static final TFLClient tflClient = new TFLClient();

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index");
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        List<StopWithArrivals> stopsWithArrivals;

        Coordinates coordinates = postcodeClient.getCoordinate(postcode);
        if (coordinates == null) {
            return new ModelAndView("info", "busInfo",
                    new BusInfo(postcode, null)) ;
        }

        stopsWithArrivals = new ArrayList<>();

        List<StopPoint> nearbyStopPoints = tflClient.getNearbyStopPoints(coordinates);
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
        return new ModelAndView("info", "busInfo", new BusInfo(postcode, stopsWithArrivals)) ;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }
}