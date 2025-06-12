package vehicle_rental_system.code;

import vehicle_rental_system.code.enums.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class VehicleRentalSystem {
    private final Map<UUID, Customer> customers = new HashMap<>();
    private final Map<UUID, Vehicle> vehicles = new HashMap<>();
    private final Map<UUID, Location> locations = new HashMap<>();
    private final Map<UUID, RentalContract> contracts = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        // Add some initial data for demonstration
        seedData();

System.out.println("=======================================");
System.out.println("   🚗  YO VEHICLE RENTAL SYSTEM  🚗   ");
System.out.println("=======================================");


        while (true) {
            printMainMenu();
            int choice = getUserChoice(10); // Adjusted max choice

            switch (choice) {
                case 1 -> addLocation();
                case 2 -> addVehicleToLocation();
                case 3 -> registerCustomer();
                case 4 -> listLocations();
                case 5 -> listAvailableVehiclesByLocation();
                case 6 -> listCustomers();
                case 7 -> createRentalContract();
                case 8 -> returnVehicle();
                case 9 -> listRentalContracts();
                case 10 -> { // Exit option
                    System.out.println("+-------------------------------------+");
                    System.out.println("|      Exiting Vehicle Rental System  |");
                    System.out.println("+-------------------------------------+");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
            System.out.println(); // Add a newline for spacing
        }
    }

    private void printMainMenu() {
        System.out.println("+-----------------------------------------+");
        System.out.println("|     Vehicle Rental System - Main Menu   |");
        System.out.println("+-----------------------------------------+");
        System.out.println("| 1. Add Location                         |");
        System.out.println("| 2. Add Vehicle to Location              |");
        System.out.println("| 3. Register Customer                    |");
        System.out.println("| 4. List Locations                       |");
        System.out.println("| 5. List Available Vehicles by Location  |");
        System.out.println("| 6. List Customers                       |");
        System.out.println("| 7. Create Rental Contract               |");
        System.out.println("| 8. Return Vehicle                       |");
        System.out.println("| 9. List Rental Contracts                |");
        System.out.println("| 10. Exit                                |"); // Updated exit option
        System.out.println("+-----------------------------------------+");
        System.out.print("   Enter your choice: ");
    }

    private void seedData() {
        // Create addresses
        Address loc1Addr = new Address("ghana street", "Adiss Ababa", "Adiss Ababa", "90210");
        Address loc2Addr = new Address("HU avenue", "Hawassa ", "Sidama", "10001");
        Address cust1Addr = new Address("BahirDar avenue", "Avenue", "Amhara", "90210");

        // Create locations
        Location loc1 = new Location(UUID.randomUUID(), "Hawassa, Atote ", loc1Addr, "24/7", 50);
        Location loc2 = new Location(UUID.randomUUID(), "Adiss Ababa, Bole", loc2Addr, "9am-5pm", 30);
        locations.put(loc1.getLocationId(), loc1);
        locations.put(loc2.getLocationId(), loc2);

        // Create vehicles
        Vehicle car1 = new Vehicle(UUID.randomUUID(), "Toyota", "Corolla", 2022, "69987", 15000, ConditionStatus.GOOD, VehicleCategory.SEDAN, FuelType.PETROL, loc1, 45.99, Arrays.asList("GPS", "Bluetooth"));
        Vehicle car2 = new Vehicle(UUID.randomUUID(), "Honda", "CR-V", 2023, "52663", 8000, ConditionStatus.EXCELLENT, VehicleCategory.SUV, FuelType.PETROL, loc1, 65.50, Arrays.asList("Sunroof", "Heated Seats"));
        Vehicle truck1 = new Vehicle(UUID.randomUUID(), "Ford", "F-150", 2021, "40552", 30000, ConditionStatus.FAIR, VehicleCategory.TRUCK, FuelType.DIESEL, loc2, 89.00, Arrays.asList("Tow Package"));

        // Add vehicles to locations
        loc1.addVehicle(car1);
        vehicles.put(car1.getVehicleId(), car1);
        loc1.addVehicle(car2);
        vehicles.put(car2.getVehicleId(), car2);
        loc2.addVehicle(truck1);
        vehicles.put(truck1.getVehicleId(), truck1);


        // Create a customer
        DriverLicense cust1License = new DriverLicense("CA987654321", "Ethiopia", "2027-12-31");
        Customer cust1 = new Customer(UUID.randomUUID(), "Abebe", "Belachew", "abebeB@gmail.com", "+25111963522", cust1Addr, cust1License);
        cust1.addPaymentMethod(PaymentMethod.CREDIT_CARD);
        customers.put(cust1.getCustomerId(), cust1);

        System.out.println("Initial data loaded.");
    }


    private void addLocation() {
        System.out.println("\n=== Add New Location ===");
        System.out.print("Enter location name: ");
        String name = scanner.nextLine();

        System.out.println("Enter address details:");
        System.out.print("Street: ");
        String street = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("State: ");
        String state = scanner.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = scanner.nextLine();
        Address address = new Address(street, city, state, zipCode);

        System.out.print("Enter operating hours (e.g., 9am-5pm): ");
        String hours = scanner.nextLine();

        int capacity = -1;
        while (capacity <= 0) {
            System.out.print("Enter capacity (number of vehicles): ");
            capacity = getUserChoice(Integer.MAX_VALUE); // Allow large capacity
            if (capacity <= 0) {
                System.out.println("Capacity must be a positive number.");
            }
        }

        Location newLocation = new Location(UUID.randomUUID(), name, address, hours, capacity);
        locations.put(newLocation.getLocationId(), newLocation);

        //  *** CRITICAL DEBUGGING PRINT STATEMENT ***
        System.out.println("Location added with ID: " + newLocation.getLocationId());
        //  ****************************************

        System.out.println("Location added successfully with ID: " + newLocation.getLocationId().toString().substring(0, 8));
    }
    private void addVehicleToLocation() {
        System.out.println("\n=== Add Vehicle to Location ===");

        if (locations.isEmpty()) {
            System.out.println("No locations available. Please add a location first.");
            return;
        }

        listLocations();
        Location selectedLocation = selectLocation();
        if (selectedLocation == null) {
            System.out.println("Invalid location selected.");
            return;
        }

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine();
        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine();

        int year = -1;
        while (year <= 1900 || year > LocalDateTime.now().getYear() + 1) { // Basic year validation
            System.out.print("Enter vehicle year: ");
            year = getUserChoice(Integer.MAX_VALUE);
            if (year <= 1900 || year > LocalDateTime.now().getYear() + 1) {
                System.out.println("Invalid year.");
            }
        }


        System.out.print("Enter license plate: ");
        String licensePlate = scanner.nextLine();

        double mileage = -1;
        while(mileage < 0) {
            System.out.print("Enter current mileage: ");
            mileage = getDoubleInput();
            if(mileage < 0){
                System.out.println("Mileage cannot be negative.");
            }
        }


        ConditionStatus condition = selectConditionStatus();
        VehicleCategory category = selectVehicleCategory();
        FuelType fuelType = selectFuelType();

        double rentalPrice = -1;
        while(rentalPrice <= 0) {
            System.out.print("Enter rental price per day: ");
            rentalPrice = getDoubleInput();
            if(rentalPrice <= 0){
                System.out.println("Rental price must be positive.");
            }
        }


        System.out.print("Enter features (comma-separated, or leave blank): ");
        String featuresInput = scanner.nextLine();
        List<String> features = featuresInput.isEmpty() ? new ArrayList<>() : Arrays.asList(featuresInput.split(",\\s*"));


        Vehicle newVehicle = new Vehicle(UUID.randomUUID(), make, model, year, licensePlate, mileage, condition, category, fuelType, selectedLocation, rentalPrice, features);

        if (selectedLocation.addVehicle(newVehicle)) {
            vehicles.put(newVehicle.getVehicleId(), newVehicle);
            System.out.println("Vehicle added successfully with ID: " + newVehicle.getVehicleId().toString().substring(0, 8));
        }
    }



    private void registerCustomer() {
        System.out.println("\n=== Register New Customer ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.println("Enter address details:");
        System.out.print("Street: ");
        String street = scanner.nextLine();
        System.out.print("City: ");
        String city = scanner.nextLine();
        System.out.print("State: ");
        String state = scanner.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = scanner.nextLine();
        Address address = new Address(street, city, state, zipCode);

        System.out.println("Enter driver license details:");
        System.out.print("License Number: ");
        String licenseNumber = scanner.nextLine();
        System.out.print("Issuing Country: ");
        String issuingCountry = scanner.nextLine();
        System.out.print("Expiry Date (YYYY-MM-DD): ");
        String expiryDate = scanner.nextLine(); // Consider robust date parsing

        DriverLicense driverLicense = new DriverLicense(licenseNumber, issuingCountry, expiryDate);

        Customer newCustomer = new Customer(UUID.randomUUID(), firstName, lastName, email, phoneNumber, address, driverLicense);

        // Add payment methods (simple selection for now)
        System.out.println("Select Payment Method(s) (comma-separated numbers):");
        PaymentMethod[] methods = PaymentMethod.values();
        for (int i = 0; i < methods.length; i++) {
            System.out.println((i + 1) + ". " + methods[i]);
        }
        System.out.print("Enter choices: ");
        String paymentChoices = scanner.nextLine();
        Arrays.stream(paymentChoices.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt) // Consider NumberFormatException
                .filter(i -> i > 0 && i <= methods.length)
                .forEach(i -> newCustomer.addPaymentMethod(methods[i - 1]));


        customers.put(newCustomer.getCustomerId(), newCustomer);
        System.out.println("Customer registered successfully with ID: " + newCustomer.getCustomerId().toString().substring(0, 8));
    }

    private void listLocations() {
        System.out.println("\n=== Locations ===");
        if (locations.isEmpty()) {
            System.out.println("No locations available yet.");
        } else {
            locations.values().forEach(System.out::println);
        }
    }

    private void listAvailableVehiclesByLocation() {
        System.out.println("\n=== Available Vehicles by Location ===");
        if (locations.isEmpty()) {
            System.out.println("No locations available yet.");
            return;
        }

        listLocations();
        Location selectedLocation = selectLocation();
        if (selectedLocation != null) {
            List<Vehicle> available = selectedLocation.getAvailableVehicles();
            if (available.isEmpty()) {
                System.out.println("No available vehicles at " + selectedLocation.getName() + ".");
            } else {
                System.out.println("Available vehicles at " + selectedLocation.getName() + ":");
                available.forEach(v -> System.out.println("- " + v.getVehicleId().toString().substring(0, 8) + " - " + v.getMake() + " " + v.getModel() + " ($" + v.getRentalPricePerDay() + "/day)"));
            }
        } else {
            System.out.println("Invalid location selected.");
        }
    }


    private void listCustomers() {
        System.out.println("\n=== Customers ===");
        if (customers.isEmpty()) {
            System.out.println("No customers registered yet.");
        } else {
            customers.values().forEach(System.out::println);
        }
    }

    private void createRentalContract() {
        System.out.println("\n=== Create Rental Contract ===");
        int maxAttempts = 3; // Maximum retry limit for invalid inputs

        // Check prerequisites
        if (customers.isEmpty()) {
            System.out.println("No customers available. Please register a customer first.");
            return;
        }
        if (locations.isEmpty()) {
            System.out.println("No locations available. Please add a location first.");
            return;
        }
        boolean vehiclesAvailable = locations.values().stream()
                .anyMatch(location -> !location.getAvailableVehicles().isEmpty());
        if (!vehiclesAvailable) {
            System.out.println("No available vehicles across all locations.");
            return;
        }

        // Select customer with retry limit
        listCustomers();
        Customer customer = null;
        int attempts = 0;
        while (attempts < maxAttempts) {
            customer = selectCustomer();
            if (customer != null) {
                break;
            }
            attempts++;
            if (attempts < maxAttempts) {
                System.out.println("Please try again (" + (maxAttempts - attempts) + " attempts remaining).");
            }
        }
        if (customer == null) {
            System.out.println("Too many invalid attempts. Aborting rental contract creation.");
            return;
        }

        // Select pickup location
        listLocations();
        Location pickupLocation = null;
        attempts = 0;
        while (attempts < maxAttempts) {
            pickupLocation = selectLocation();
            if (pickupLocation != null) {
                break;
            }
            attempts++;
            if (attempts < maxAttempts) {
                System.out.println("Please try again (" + (maxAttempts - attempts) + " attempts remaining).");
            }
        }
        if (pickupLocation == null) {
            System.out.println("Too many invalid attempts. Aborting rental contract creation.");
            return;
        }

        // Select vehicle
        List<Vehicle> availableVehiclesAtPickup = pickupLocation.getAvailableVehicles();
        if (availableVehiclesAtPickup.isEmpty()) {
            System.out.println("No available vehicles at the selected pickup location.");
            return;
        }
        System.out.println("Available vehicles at " + pickupLocation.getName() + ":");
        availableVehiclesAtPickup.forEach(v -> System.out.println("- " + v.getVehicleId().toString().substring(0, 8) + " - " + v.getMake() + " " + v.getModel() + " ($" + v.getRentalPricePerDay() + "/day)"));

        Vehicle vehicle = null;
        attempts = 0;
        while (attempts < maxAttempts) {
            vehicle = selectVehicle(new ArrayList<>(availableVehiclesAtPickup));
            if (vehicle != null) {
                break;
            }
            attempts++;
            if (attempts < maxAttempts) {
                System.out.println("Please try again (" + (maxAttempts - attempts) + " attempts remaining).");
            }
        }
        if (vehicle == null) {
            System.out.println("Too many invalid attempts. Aborting rental contract creation.");
            return;
        }

        // Enter rental period
        System.out.println("Enter rental period:");
        LocalDateTime startDateTime = getLocalDateTimeInput("Enter start date and time (YYYY-MM-DD HH:MM): ");
        LocalDateTime endDateTime = getLocalDateTimeInput("Enter end date and time (YYYY-MM-DD HH:MM): ");
        if (startDateTime == null || endDateTime == null || endDateTime.isBefore(startDateTime)) {
            System.out.println("Invalid date or time input. End date must be after start date.");
            return;
        }
        RentalPeriod rentalPeriod = new RentalPeriod(startDateTime, endDateTime);

        // Select drop-off location
        Location dropoffLocation = selectLocation("Enter drop-off location ID (press Enter for same as pickup): ");
        if (dropoffLocation == null) {
            dropoffLocation = pickupLocation; // Default to pickup location
        }

        // Select insurance options
        List<Insurance> availableInsurances = Arrays.asList(
                new Insurance("Basic", "Basic liability coverage"),
                new Insurance("Standard", "Collision and basic liability"),
                new Insurance("Premium", "Full coverage")
        );
        List<Insurance> selectedInsurances = new ArrayList<>();
        System.out.println("Available Insurance Options:");
        for (int i = 0; i < availableInsurances.size(); i++) {
            System.out.println((i + 1) + ". " + availableInsurances.get(i));
        }
        System.out.print("Select insurance options (comma-separated numbers, or 0 for none): ");
        attempts = 0;
        while (attempts < maxAttempts) {
            String insuranceChoices = scanner.nextLine().trim();
            if (insuranceChoices.equals("0")) {
                break; // No insurance selected
            }
            try {
                Arrays.stream(insuranceChoices.split(","))
                        .map(String::trim)
                        .filter(s -> !s.isEmpty())
                        .map(Integer::parseInt)
                        .filter(i -> i > 0 && i <= availableInsurances.size())
                        .forEach(i -> selectedInsurances.add(availableInsurances.get(i - 1)));
                break; // Valid selection, exit loop
            } catch (NumberFormatException e) {
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Invalid input. Please enter comma-separated numbers between 1 and " + availableInsurances.size() + ", or 0 for none.");
                    System.out.print("Select insurance options (comma-separated numbers, or 0 for none): ");
                }
            }
        }
        if (attempts >= maxAttempts) {
            System.out.println("Too many invalid attempts. Proceeding without insurance.");
        }

        // Create the rental contract
        RentalContract newContract = new RentalContract(UUID.randomUUID(), customer, vehicle, rentalPeriod, pickupLocation, dropoffLocation, selectedInsurances);
        contracts.put(newContract.getContractId(), newContract);

        // Display cost breakdown
        long rentalDays = java.time.Duration.between(rentalPeriod.getStartDateTime(), rentalPeriod.getEndDateTime()).toDays();
        if (rentalDays == 0) rentalDays = 1; // Minimum 1 day
        BigDecimal baseCost = BigDecimal.valueOf(vehicle.getRentalPricePerDay()).multiply(BigDecimal.valueOf(rentalDays));
        // Placeholder insurance cost: $10 per day per insurance option (adjust as needed)
        BigDecimal insuranceCost = BigDecimal.valueOf(selectedInsurances.size() * 10).multiply(BigDecimal.valueOf(rentalDays));
        BigDecimal totalCost = baseCost.add(insuranceCost);

        System.out.println("Rental contract created successfully with ID: " + newContract.getContractId().toString().substring(0, 8));
        System.out.println("=== Cost Breakdown ===");
        System.out.println("Base Rental Cost (" + rentalDays + " days at $" + vehicle.getRentalPricePerDay() + "/day): $" + baseCost.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Insurance Cost (" + selectedInsurances.size() + " options at $10/day each): $" + insuranceCost.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Total Cost: $" + totalCost.setScale(2, BigDecimal.ROUND_HALF_UP));
        System.out.println("Note: Late returns may incur a fee of $" + newContract.getLateFeePerDay().setScale(2, BigDecimal.ROUND_HALF_UP) + "/day.");
        System.out.println("=== Contract Details ===");
        System.out.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
        System.out.println("Vehicle: " + vehicle.getMake() + " " + vehicle.getModel());
        System.out.println("Pickup Location: " + pickupLocation.getName());
        System.out.println("Drop-off Location: " + dropoffLocation.getName());
        System.out.println("Rental Period: " + rentalPeriod);
        System.out.println("Insurance Selected: " + (selectedInsurances.isEmpty() ? "None" : selectedInsurances));

        // Process payment
        System.out.print("Process payment now? (yes/no): ");
        String processPayment = "no"; // Initialize with default value
        attempts = 0;
        while (attempts < maxAttempts) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("no")) {
                processPayment = input;
                break;
            }
            attempts++;
            if (attempts < maxAttempts) {
                System.out.println("Invalid input. Please enter 'yes' or 'no' (" + (maxAttempts - attempts) + " attempts remaining).");
                System.out.print("Process payment now? (yes/no): ");
            }
        }
        if (attempts >= maxAttempts) {
            System.out.println("Too many invalid attempts. Skipping payment process.");
            processPayment = "no"; // Ensure processPayment is set
        }

        if (processPayment.equals("yes")) {
            if (customer.getPaymentMethods().isEmpty()) {
                System.out.println("No payment methods available for customer. Defaulting to CASH.");
                Payment payment = new Payment(UUID.randomUUID(), totalCost, PaymentMethod.CASH, PaymentStatus.PENDING);
                newContract.recordPayment(payment);
                System.out.println("Payment recorded with status: " + payment.getStatus());
            } else {
                // Allow user to select a payment method
                System.out.println("Available Payment Methods for " + customer.getFirstName() + " " + customer.getLastName() + ":");
                List<PaymentMethod> paymentMethods = customer.getPaymentMethods();
                for (int i = 0; i < paymentMethods.size(); i++) {
                    System.out.println((i + 1) + ". " + paymentMethods.get(i));
                }
                System.out.print("Select a payment method (1-" + paymentMethods.size() + "): ");
                attempts = 0;
                PaymentMethod selectedMethod = null;
                while (attempts < maxAttempts) {
                    try {
                        int choice = Integer.parseInt(scanner.nextLine().trim());
                        if (choice >= 1 && choice <= paymentMethods.size()) {
                            selectedMethod = paymentMethods.get(choice - 1);
                            break;
                        }
                        throw new NumberFormatException("Out of range");
                    } catch (NumberFormatException e) {
                        attempts++;
                        if (attempts < maxAttempts) {
                            System.out.println("Invalid input. Please enter a number between 1 and " + paymentMethods.size() + " (" + (maxAttempts - attempts) + " attempts remaining).");
                            System.out.print("Select a payment method (1-" + paymentMethods.size() + "): ");
                        }
                    }
                }
                if (selectedMethod == null) {
                    System.out.println("Too many invalid attempts. Skipping payment process.");
                } else {
                    Payment payment = new Payment(UUID.randomUUID(), totalCost, selectedMethod, PaymentStatus.PENDING);
                    newContract.recordPayment(payment);
                    System.out.println("Payment recorded with status: " + payment.getStatus() + " using " + selectedMethod);
                }
            }
        }
    }
    private void returnVehicle() {
        System.out.println("\n=== Return Vehicle ===");
        System.out.println("=== ACTIVE Rental Contracts ===");
        Map<String, RentalContract> truncatedIdMap = new HashMap<>();
        boolean hasActiveContracts = false;

        for (RentalContract contract : contracts.values()) {
            if (contract.getStatus() == RentalStatus.ACTIVE) {
                hasActiveContracts = true;
                String truncatedId = contract.getContractId().toString().substring(0, 8);
                System.out.println("- ID: " + truncatedId +
                        ", Customer: " + contract.getCustomer().getFirstName() + " " + contract.getCustomer().getLastName() +
                        ", Vehicle: " + contract.getVehicle().getMake() + " " + contract.getVehicle().getModel() +
                        ", Status: " + contract.getStatus());
                truncatedIdMap.put(truncatedId, contract);
            }
        }

        if (!hasActiveContracts) {
            System.out.println("No active rental contracts found.");
            return;
        }

        System.out.print("Enter the ID of the contract to return: ");
        String inputId = scanner.nextLine().trim();

        RentalContract contract = truncatedIdMap.get(inputId);
        if (contract == null) {
            System.out.println("Active rental contract not found with ID: " + inputId);
            return;
        }

        // Perform the return operation
        contract.completeRental(contract.getDropoffLocation(), contract.getVehicle().getMileage()); // Assuming completeRental exists
        contract.getVehicle().setAvailable(true);
        System.out.println("Vehicle returned successfully. Contract ID: " + contract.getContractId().toString().substring(0, 8));
    }
    private void listRentalContracts() {
        System.out.println("\n=== All Rental Contracts ===");
        if (contracts.isEmpty()) {
            System.out.println("No rental contracts exist yet.");
        } else {
            contracts.values().forEach(contract -> System.out.println(
                    "- ID: " + contract.getContractId().toString().substring(0, 8) +
                            ", Customer: " + contract.getCustomer().getFirstName() + " " + contract.getCustomer().getLastName() +
                            ", Vehicle: " + contract.getVehicle().getMake() + " " + contract.getVehicle().getModel() +
                            ", Status: " + contract.getStatus()
            ));
        }
    }

    private void listRentalContractsByStatus(RentalStatus status) {
        System.out.println("\n=== " + status + " Rental Contracts ===");
        List<RentalContract> filteredContracts = contracts.values().stream()
                .filter(contract -> contract.getStatus() == status)
                .collect(Collectors.toList());

        if (filteredContracts.isEmpty()) {
            System.out.println("No " + status + " rental contracts found.");
        } else {
            filteredContracts.forEach(contract -> System.out.println(
                    "- ID: " + contract.getContractId().toString().substring(0, 8) +
                            ", Customer: " + contract.getCustomer().getFirstName() + " " + contract.getCustomer().getLastName() +
                            ", Vehicle: " + contract.getVehicle().getMake() + " " + contract.getVehicle().getModel() +
                            ", Status: " + contract.getStatus()
            ));
        }
    }
    // --- Helper methods for selection and input ---

    private Customer selectCustomer() {
        System.out.print("Enter the ID of the customer: ");
        String input = scanner.nextLine().trim();
        System.out.println("Raw input: \"" + input + "\""); // Debugging

        // Try to find a customer by matching the first 8 characters of the UUID
        for (Customer customer : customers.values()) {
            String uuidPrefix = customer.getCustomerId().toString().substring(0, 8);
            if (uuidPrefix.equals(input)) {
                System.out.println("Customer found: " + customer);
                return customer;
            }
        }

        // If the input is a full UUID, try parsing it directly
        try {
            UUID customerId = UUID.fromString(input);
            Customer customer = customers.get(customerId);
            if (customer != null) {
                System.out.println("Customer found: " + customer);
                return customer;
            }
        } catch (IllegalArgumentException e) {
            // Input is not a valid full UUID, fall through to error
        }

        System.out.println("Invalid customer ID. Please enter the 8-character ID as shown in the customer list or the full UUID.");
        System.out.println("Available customer IDs:");
        for (Customer customer : customers.values()) {
            System.out.println("- " + customer.getCustomerId().toString().substring(0, 8) + " - " + customer.getFirstName() + " " + customer.getLastName());
        }
        return null;
    }
    private Location selectLocation() {
        return selectLocation("Enter the ID of the location: ");
    }

    private Location selectLocation(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        System.out.println("Raw input: \"" + input + "\""); // Debugging

        if (input.isEmpty() && prompt.contains("press Enter for same as pickup")) {
            return null;
        }

        // Try to find a location by matching the first 8 characters of the UUID
        for (Location loc : locations.values()) {
            String uuidPrefix = loc.getLocationId().toString().substring(0, 8);
            if (uuidPrefix.equals(input)) {
                System.out.println("Location found: " + loc);
                return loc;
            }
        }

        // If the input is a full UUID, try parsing it directly
        try {
            UUID locationId = UUID.fromString(input);
            Location loc = locations.get(locationId);
            if (loc != null) {
                System.out.println("Location found: " + loc);
                return loc;
            }
        } catch (IllegalArgumentException e) {
            // Input is not a valid full UUID, fall through to error
        }

        System.out.println("Invalid location ID. Please enter the 8-character ID as shown in the location list or the full UUID.");
        System.out.println("Available location IDs:");
        for (Location loc : locations.values()) {
            System.out.println("- " + loc.getLocationId().toString().substring(0, 8) + " (" + loc.getName() + ")");
        }
        return null;
    }

    private Vehicle selectVehicle(List<Vehicle> vehicleList) {
        System.out.print("Enter the ID of the vehicle: ");
        String input = scanner.nextLine().trim();
        System.out.println("Raw input: \"" + input + "\""); // Debugging

        // Try to find a vehicle by matching the first 8 characters of the UUID
        for (Vehicle vehicle : vehicleList) {
            String uuidPrefix = vehicle.getVehicleId().toString().substring(0, 8);
            if (uuidPrefix.equals(input)) {
                System.out.println("Vehicle found: " + vehicle);
                return vehicle;
            }
        }

        // If the input is a full UUID, try parsing it directly
        try {
            UUID vehicleId = UUID.fromString(input);
            for (Vehicle vehicle : vehicleList) {
                if (vehicle.getVehicleId().equals(vehicleId)) {
                    System.out.println("Vehicle found: " + vehicle);
                    return vehicle;
                }
            }
        } catch (IllegalArgumentException e) {
            // Input is not a valid full UUID, fall through to error
        }

        System.out.println("Invalid vehicle ID. Please enter the 8-character ID as shown in the vehicle list or the full UUID.");
        System.out.println("Available vehicle IDs:");
        for (Vehicle vehicle : vehicleList) {
            System.out.println("- " + vehicle.getVehicleId().toString().substring(0, 8) + " - " + vehicle);
        }
        return null;
    }

    private RentalContract findContractById(String contractIdString) {
        try {
            UUID contractId = UUID.fromString(contractIdString);
            return contracts.get(contractId);
        } catch (IllegalArgumentException e) {
            return null; // Invalid UUID format
        }
    }


    private ConditionStatus selectConditionStatus() {
        return selectEnumValue(ConditionStatus.class, "Select vehicle condition:");
    }

    private VehicleCategory selectVehicleCategory() {
        return selectEnumValue(VehicleCategory.class, "Select vehicle category:");
    }

    private FuelType selectFuelType() {
        return selectEnumValue(FuelType.class, "Select fuel type:");
    }

    private <E extends Enum<E>> E selectEnumValue(Class<E> enumClass, String prompt) {
        E[] enumConstants = enumClass.getEnumConstants();
        E selectedValue = null;
        while (selectedValue == null) {
            System.out.println("\n" + prompt);
            for (int i = 0; i < enumConstants.length; i++) {
                System.out.println((i + 1) + ". " + enumConstants[i]);
            }
            System.out.print("Enter choice (" + "1-" + enumConstants.length + "): ");
            int choice = getUserChoice(enumConstants.length);
            try {
                selectedValue = enumConstants[choice - 1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid choice.");
            }
        }
        return selectedValue;
    }

    private int getUserChoice(int maxChoice) {
        int choice = -1;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= maxChoice) {
                    return choice;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and " + maxChoice + ".");
                    System.out.print("Enter your choice: ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter your choice: ");
            }
        }
    }

    private double getDoubleInput() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.print("Enter value: ");
            }
        }
    }

    private LocalDateTime getLocalDateTimeInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                // Basic parsing, consider adding more robust handling for different formats
                return LocalDateTime.parse(input, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Please use YYYY-MM-DD HH:MM.");
            }
        }
    }
}