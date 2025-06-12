// Vehicle.java

package vehicle_rental_system.code;

import vehicle_rental_system.code.enums.FuelType;
import vehicle_rental_system.code.enums.RentalStatus;
import vehicle_rental_system.code.enums.VehicleCategory;
import vehicle_rental_system.code.enums.ConditionStatus;

import java.util.*;

public class Vehicle {
    private UUID vehicleId;
    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private double mileage;
    private ConditionStatus condition;
    private VehicleCategory category;
    private FuelType fuelType;
    private Location currentLocation;
    private double rentalPricePerDay;
    private List<String> features;
    private boolean available;
    private RentalStatus rentalStatus;

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public Vehicle(UUID vehicleId, String make, String model, int year, String licensePlate,
                   double mileage, ConditionStatus condition, VehicleCategory category,
                   FuelType fuelType, Location currentLocation, double rentalPricePerDay, List<String> features) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.licensePlate = licensePlate;
        this.mileage = mileage;
        this.condition = condition;
        this.category = category;
        this.fuelType = fuelType;
        this.currentLocation = currentLocation;
        this.rentalPricePerDay = rentalPricePerDay;
        this.features = features;
        this.available = true; // Vehicles are available by default when created
        this.rentalStatus = RentalStatus.ACTIVE; //  Initial status
    }

    public UUID getVehicleId() {
        return vehicleId;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public double getMileage() {
        return mileage;
    }

    public ConditionStatus getCondition() {
        return condition;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public double getRentalPricePerDay() {
        return rentalPricePerDay;
    }

    public List<String> getFeatures() {
        return features;
    }

    public boolean checkAvailability() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public void setCondition(ConditionStatus condition) {
        this.condition = condition;
    }

    public void scheduleMaintenance(String maintenanceRecord) {
        this.condition = ConditionStatus.NEEDS_MAINTENANCE;
        this.available = false;
        // In a real system, you'd likely store maintenance records
        System.out.println("Vehicle " + licensePlate + " scheduled for maintenance: " + maintenanceRecord);
    }

    @Override
    public String toString() {
        return make + " " + model + " (" + year + ") [" + licensePlate + "] - $" + rentalPricePerDay + "/day";
    }
}