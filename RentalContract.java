package vehicle_rental_system.code;

import vehicle_rental_system.code.enums.RentalStatus;
import java.math.BigDecimal;
import java.time.*;
import java.util.*;

public class RentalContract {
    private UUID contractId;
    private Customer customer;
    private RentalPeriod rentalPeriod;
    private Location pickupLocation;
    private Location dropoffLocation;
    private List<Insurance> insuranceOptions;
    private LocalDateTime actualReturnDateTime;
    private BigDecimal lateFeePerDay = BigDecimal.valueOf(25.00);
    private RentalStatus status;
    private Vehicle vehicle;
    private List<Payment> payments = new ArrayList<>();

    public RentalContract(UUID contractId, Customer customer, Vehicle vehicle,
                          RentalPeriod rentalPeriod, Location pickupLocation,
                          Location dropoffLocation, List<Insurance> insuranceOptions) {
        this.contractId = contractId;
        this.customer = customer;
        this.vehicle = vehicle;
        this.rentalPeriod = rentalPeriod;
        this.pickupLocation = pickupLocation;
        this.dropoffLocation = dropoffLocation;
        this.insuranceOptions = insuranceOptions;
        this.status = RentalStatus.ACTIVE;
        this.payments = new ArrayList<>();
        this.vehicle.setAvailable(false);
    }

    public Customer getCustomer() {
        return customer;
    }

    public BigDecimal getLateFeePerDay() {
        return lateFeePerDay;
    }

    public RentalStatus getStatus() {
        return status;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public UUID getContractId() {
        return contractId;
    }

    public void recordPayment(Payment payment) {
        if (payment != null) {
            payments.add(payment);
        }
    }

    public void completeRental(Location returnLocation, double mileageAtReturn) {
        this.status = RentalStatus.COMPLETED;
        this.dropoffLocation = returnLocation;
        this.vehicle.setMileage(mileageAtReturn);
        this.vehicle.setCurrentLocation(returnLocation);
        this.vehicle.setAvailable(true);
    }

    public BigDecimal getTotalCost() {
        long days = java.time.Duration.between(rentalPeriod.getStartDateTime(), rentalPeriod.getEndDateTime()).toDays();
        if (days == 0) days = 1;
        BigDecimal pricePerDay = BigDecimal.valueOf(vehicle.getRentalPricePerDay());
        BigDecimal total = pricePerDay.multiply(BigDecimal.valueOf(days));
        return total;
    }

    // New getter method to resolve the issue
    public Location getDropoffLocation() {
        return dropoffLocation;
    }
}