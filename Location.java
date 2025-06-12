// Location.java

package vehicle_rental_system.code;

import vehicle_rental_system.code.enums.RentalStatus;

import vehicle_rental_system.code.enums.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Location {
    private final UUID locationId;
    private String name;
    private Address address;
    private String operatingHours;
    private int capacity;
    private final List<Vehicle> vehicles;

    public Location(UUID locationId, String name, Address address, String operatingHours, int capacity) {
        this.locationId = locationId;
        this.name = name;
        this.address = address;
        this.operatingHours = operatingHours;
        this.capacity = capacity;
        this.vehicles = new ArrayList<>();
    }

    public UUID getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(String operatingHours) {
        this.operatingHours = operatingHours;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicles;
    }

    public boolean addVehicle(Vehicle vehicle) {
        if (vehicles.size() < capacity) {
            vehicle.setCurrentLocation(this);
            return vehicles.add(vehicle);
        } else {
            System.out.println("Location " + name + " is at full capacity.");
            return false;
        }
    }

    public boolean removeVehicle(Vehicle vehicle) {
        vehicle.setCurrentLocation(null);
        return vehicles.remove(vehicle);
    }

    public List<Vehicle> getAvailableVehicles() {
        return vehicles.stream()
                .filter(vehicle -> vehicle.checkAvailability())  //  Updated to use checkAvailability()
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + locationId.toString().substring(0, 8) +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", operatingHours='" + operatingHours + '\'' +
                ", capacity=" + capacity +
                ", vehicleCount=" + vehicles.size() +
                '}';
    }
}