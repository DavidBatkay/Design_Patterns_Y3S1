import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Car> carFleet = Arrays.asList(
                new Car("Dacia Sandero", 5, false),
                new Car("Mercedes E220", 1, true),
                new Car("Tesla Model 3", 12, false),
                new Car("BMW 328i", 8, false)
        );

        List<Scooter> scooterFleet = Arrays.asList(
                new Scooter("Honda PCX", 3, 5),
                new Scooter("Suzuki Access 125", 1, 15),
                new Scooter("Yamaha Aerox", 8, 3)
        );

        IBookingMediator mediator = new VehicleBookingMediator(carFleet, scooterFleet);

        Client alice = new Client("Alice", 50);
        alice.requestCar(mediator);

        mediator.updateCarStatus("Delta", true);

        Client bob = new Client("Bob", 48);
        bob.requestCar(mediator);

        alice.requestScooter(mediator, 5);
        alice.requestScooter(mediator, 30);
    }
}