public class Car {
    private final String id;
    private final int distanceToClient;
    private boolean isInTrip;

    public Car(String id, int distanceToClient, boolean isInTrip) {
        this.id = id;
        this.distanceToClient = distanceToClient;
        this.isInTrip = isInTrip;
    }

    public String getId() { return id; }
    public int getDistanceToClient() { return distanceToClient; }
    public boolean isInTrip() { return isInTrip; }
    public void setInTrip(boolean inTrip) { isInTrip = inTrip; }
}