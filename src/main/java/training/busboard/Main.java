package training.busboard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        String postcode = promptForPostcode();
        Coordinate coordinate = new PostcodeClient().getCoordinate(postcode);
        System.out.println(coordinate.getLatitude() + " " + coordinate.getLongitude());

        /*
        List<>
        List<ArrivalPrediction> predictions = new TFLClient().getPredictions(stopId);
        Collections.sort(predictions, new Comparator<ArrivalPrediction>() {
            @Override
            public int compare(ArrivalPrediction ap1, ArrivalPrediction ap2) {
                return ap1.getTimeToStation() - ap2.getTimeToStation();
            }
        });

        System.out.println(String.format("%-15s%-25s%-25s",
                "Line Name", "Destination", "Time to Arrival (Minutes)"));
        for (int i = 0; i < predictions.size() && i < 5; ++i) {
            System.out.println(predictions.get(i));
        }
        */
    }

    private static String promptForPostcode() {
        Scanner in = new Scanner(System.in);
        String postcode = in.nextLine();
        in.close();
        return postcode;
    }
}	
