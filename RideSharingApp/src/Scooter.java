public class Scooter {
    private final String id;
    private final int distanceToClient;
    private final int maxRangeKm;

    public Scooter(String id, int distanceToClient, int maxRangeKm) {
        this.id = id;
        this.distanceToClient = distanceToClient;
        this.maxRangeKm = maxRangeKm;
    }

    public String getId() { return id; }
    public int getDistanceToClient() { return distanceToClient; }
    public int getMaxRangeKm() { return maxRangeKm; }
}