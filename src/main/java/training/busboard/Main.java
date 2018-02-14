package training.busboard;

import training.busboard.models.ArrivalPrediction;
import training.busboard.models.Coordinates;
import training.busboard.models.StopPoint;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final PostcodeClient postcodeClient = new PostcodeClient();
    private static final TFLClient tflClient = new TFLClient();

    public static void main(String args[]) {

        String postcode = promptForPostcode();
        Coordinates coordinates = postcodeClient.getCoordinate(postcode);
        if (coordinates == null) {
            System.out.println("Invalid postcode!");
            return;
        }

        List<StopPoint> nearbyStopPoints = tflClient.getNearbyStopPoints(coordinates);
        if (nearbyStopPoints.isEmpty()) {
            System.out.println("No bus stops found within 200m!");
            return;
        }

        Collections.sort(nearbyStopPoints, new Comparator<StopPoint>() {
            @Override
            public int compare(StopPoint o1, StopPoint o2) {
                return o1.getDistance() - o2.getDistance();
            }
        });

        for (int i = 0; i < nearbyStopPoints.size() && i < 2; ++i) {
            System.out.println(nearbyStopPoints.get(i).getCommonName() + nearbyStopPoints.get(i).getNaptanId());
            displayBusBoard(nearbyStopPoints.get(i));
            System.out.println();
        }
    }

    private static void displayBusBoard(StopPoint stopPoint) {
        List<ArrivalPrediction> predictions = new TFLClient().getPredictions(stopPoint.getNaptanId());
        Collections.sort(predictions, new Comparator<ArrivalPrediction>() {
            @Override
            public int compare(ArrivalPrediction ap1, ArrivalPrediction ap2) {
                return ap1.getTimeToStation() - ap2.getTimeToStation();
            }
        });

        System.out.println(String.format("%-15s%-35s%-25s",
                "Line Name", "Destination", "Time to Arrival (Minutes)"));
        for (int i = 0; i < predictions.size() && i < 5; ++i) {
            System.out.println(predictions.get(i));
        }
    }

    private static String promptForPostcode() {
        Scanner in = new Scanner(System.in);
        String postcode = in.nextLine();
        in.close();
        return postcode;
    }
}	
