@Service
public class RideService {

    private final RideRepository rideRepository;

    @Autowired
    public RideService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    /**
     * Fetch all rides and map them to the Ride DTO.
     * @return a list of Ride objects.
     */
    public List<Ride> getAllRides() {
        List<RideEntity> rideEntities = rideRepository.findAll();

        // Map each RideEntity to a Ride DTO
        return rideEntities.stream()
                .map(this::mapToRide)
                .toList();
    }

    // Other existing methods...

    /**
     * Helper method to map a RideEntity to a Ride DTO.
     */
    private Ride mapToRide(RideEntity rideEntity) {
        Ride ride = new Ride();
        ride.setId(rideEntity.getId());
        ride.setDriverId(rideEntity.getDriver().getId());
        ride.setCarId(rideEntity.getCar().getId());
        ride.setStartingPoint(rideEntity.getStartingPoint());
        ride.setDestination(rideEntity.getDestination());
        ride.setDate(rideEntity.getDate());
        ride.setStartTime(rideEntity.getStartTime());
        ride.setEndTime(rideEntity.getEndTime());
        ride.setPricePerHead(rideEntity.getPricePerHead());
        ride.setAvailableSeats(rideEntity.getAvailableSeats());
        ride.setRideStatus(rideEntity.getRideStatus());

        // Map passengers (CustomUserEntity) to passenger IDs
        ride.setPassengerIds(rideEntity.getPassengers()
                .stream()
                .map(CustomUserEntity::getId)
                .collect(Collectors.toSet()));

        return ride;
    }
}
