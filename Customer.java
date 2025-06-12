package vehicle_rental_system.code;

import vehicle_rental_system.code.enums.PaymentMethod;
import vehicle_rental_system.code.enums.PaymentStatus;
import vehicle_rental_system.code.DriverLicense;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Customer {
    private UUID customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address address;
    private DriverLicense driverLicense;
    private List<PaymentMethod> paymentMethods;

    public Customer(UUID customerId, String firstName, String lastName, String email, String phoneNumber,
                    Address address, DriverLicense driverLicense) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.driverLicense = driverLicense;
        this.paymentMethods = new ArrayList<>();
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public DriverLicense getDriverLicense() {
        return driverLicense;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void addPaymentMethod(PaymentMethod method) {
        paymentMethods.add(method);
    }

    @Override
    public String toString() {
        return "Customer ID: " + customerId.toString().substring(0, 8) +
                ", Name: " + firstName + " " + lastName +
                ", Email: " + email +
                ", Phone: " + phoneNumber;
    }
}