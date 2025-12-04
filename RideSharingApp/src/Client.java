public class Client {
    private final String name;
    private final int location;

    public Client(String name, int location) {
        this.name = name;
        this.location = location;
    }

    public int getLocation() { return location; }


    public void requestCar(IBookingMediator mediator) {
        System.out.println(name + " is requesting a Car");
        System.out.println(mediator.findCarOptions(location));
    }


    public void requestScooter(IBookingMediator mediator, int tripDistanceKm) {
        System.out.println( name + " is requesting a Scooter for a " + tripDistanceKm + "km trip");
        System.out.println(mediator.findScooterOptions(location, tripDistanceKm));
    }
}