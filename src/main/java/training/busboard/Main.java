package training.busboard;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        String stopID = promptForStopID();

    }

    private static String promptForStopID() {
        Scanner in = new Scanner(System.in);
        String stopID = in.nextLine();
        in.close();
        return stopID;
    }
}	
