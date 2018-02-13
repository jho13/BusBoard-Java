package training.busboard;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        String stopID = promptForStopID();
        List<ArrivalPrediction> predictions = new TFLClient().getPredictions(stopID);
        Collections.sort(predictions, new Comparator<ArrivalPrediction>() {
            @Override
            public int compare(ArrivalPrediction ap1, ArrivalPrediction ap2) {
                return ap1.getTimeToStation() - ap2.getTimeToStation();
            }
        });
        for (ArrivalPrediction prediction : predictions) {
            System.out.println(prediction);
        }
    }

    private static String promptForStopID() {
        Scanner in = new Scanner(System.in);
        String stopID = in.nextLine();
        in.close();
        return stopID;
    }
}	
