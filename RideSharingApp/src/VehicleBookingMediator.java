import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class VehicleBookingMediator implements IBookingMediator {

    private final List<Car> carFleet;
    private final List<Scooter> scooterFleet;

    public VehicleBookingMediator(List<Car> carFleet, List<Scooter> scooterFleet) {
        this.carFleet = carFleet;
        this.scooterFleet = scooterFleet;
    }

    @Override
    public void updateCarStatus(String carId, boolean isInTrip) {
        carFleet.stream()
                .filter(car -> car.getId().equals(carId))
                .findFirst()
                .ifPresent(car -> car.setInTrip(isInTrip));
    }

    @Override
    public String findCarOptions(int clientLocation) {
        System.out.println("Finding Car Options for Client at Location: " + clientLocation );

        List<Car> availableCars = carFleet.stream()
                .filter(car -> !car.isInTrip())
                .toList();

        if (availableCars.isEmpty()) {
            return "No cars are currently available.";
        }

        List<Car> closestCars = availableCars.stream()
                .sorted(Comparator.comparingInt(Car::getDistanceToClient))
                .limit(3)
                .toList();

        StringBuilder sb = new StringBuilder("Found " + closestCars.size() + " closest available car options:\n");
        for (int i = 0; i < closestCars.size(); i++) {
            Car car = closestCars.get(i);
            sb.append(String.format("%d. Car %s: %d km away.\n", i + 1, car.getId(), car.getDistanceToClient()));
        }
        return sb.toString();
    }

    @Override
    public String findScooterOptions(int clientLocation, int tripDistanceKm) {
        System.out.println("\n--- Finding Scooter Options for Client at Location " + clientLocation + " (Trip: " + tripDistanceKm + " km) ---");

        final double minRequiredRange = tripDistanceKm * 0.5;

        List<Scooter> validScooters = scooterFleet.stream()
                .filter(scooter -> scooter.getMaxRangeKm() >= minRequiredRange)
                .toList();

        if (validScooters.isEmpty()) {
            return String.format("No scooters meet the minimum 50%% range requirement (min range: %.1f km).", minRequiredRange);
        }

        List<Scooter> sortedValidScooters = validScooters.stream()
                .sorted(Comparator.comparingInt(Scooter::getDistanceToClient))
                .toList();


        StringBuilder sb = getStringBuilder(tripDistanceKm, sortedValidScooters, minRequiredRange);
        return sb.toString();
    }

    private static StringBuilder getStringBuilder(int tripDistanceKm, List<Scooter> sortedValidScooters, double minRequiredRange) {
        StringBuilder sb = new StringBuilder("Found " + sortedValidScooters.size() + " valid scooter options (Min range: " + minRequiredRange + " km):\n");
        for (int i = 0; i < sortedValidScooters.size(); i++) {
            Scooter scooter = sortedValidScooters.get(i);
            String status = (scooter.getMaxRangeKm() >= tripDistanceKm)
                    ? "Covers Full Trip"
                    : "Partial Coverage (Trip: " + tripDistanceKm + "km, Range: " + scooter.getMaxRangeKm() + "km)";

            sb.append(String.format("%d. Scooter %s: %d km away (Range: %d km). Status: %s\n",
                    i + 1, scooter.getId(), scooter.getDistanceToClient(), scooter.getMaxRangeKm(), status));
        }
        return sb;
    }
}