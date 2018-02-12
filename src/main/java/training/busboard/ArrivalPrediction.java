package training.busboard;

public class ArrivalPrediction {
    public String lineName;
    public String destinationName;
    public int timeToStation;

    public ArrivalPrediction() {}

    @Override
    public String toString() {
        return String.format("%5s%15s%2d", lineName, destinationName, timeToStation);
    }
}
