public interface IBookingMediator {

    String findCarOptions(int clientLocation);

    String findScooterOptions(int clientLocation, int tripDistanceKm);

    void updateCarStatus(String carId, boolean isInTrip);

}