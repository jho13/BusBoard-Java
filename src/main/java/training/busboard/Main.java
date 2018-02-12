package training.busboard;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        String stopID = promptForStopID();
        ArrivalPrediction a = new ArrivalPrediction();
        a.lineName = "225";
        a.destinationName = "Oxford Street";
        a.timeToStation = 200;
        System.out.println(a.toString());
    }

    private static String promptForStopID() {
        Scanner in = new Scanner(System.in);
        String stopID = in.nextLine();
        in.close();
        return stopID;
    }
}	
