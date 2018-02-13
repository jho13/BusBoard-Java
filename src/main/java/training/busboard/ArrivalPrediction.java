package training.busboard;

public class ArrivalPrediction {


    private String lineName;
    private String destinationName;
    private int timeToStation;

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public int getTimeToStation() {
        return timeToStation;
    }

    public void setTimeToStation(int timeToStation) {
        this.timeToStation = timeToStation;
    }

    private ArrivalPrediction() {}

    @Override
    public String toString() {
        return String.format("%-5s%-25s%-2d", lineName, destinationName, timeToStation);
    }
}
