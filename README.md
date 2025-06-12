# 🚗 YO VEHICLE RENTAL SYSTEM

Welcome to **YO Vehicle Rental System** – a Java-based console application for managing vehicle rentals, customers, locations, and contracts with ease!

---

## ✨ Features

- 📍 **Location Management**: Add and list rental locations with address and capacity.
- 🚙 **Vehicle Management**: Add vehicles to locations, track their status, and list available vehicles.
- 👤 **Customer Management**: Register customers with driver license and payment methods.
- 📄 **Rental Contracts**: Create, list, and manage rental contracts, including insurance options and payment processing.
- 🔄 **Vehicle Returns**: Return vehicles and update contract status.
- 📊 **Cost Breakdown**: See detailed rental and insurance costs.
- 🖥️ **User-Friendly Console UI**: Simple, menu-driven interface.

---

## 🧑‍💻 Object-Oriented Programming (OOP) Principles

This project is designed to demonstrate core OOP concepts in Java:

- **Encapsulation**:  
  All data fields are private and accessed via getters/setters, ensuring data integrity and hiding internal implementation details.

- **Abstraction**:  
  The system exposes high-level operations (like creating contracts or registering customers) through clear, well-defined methods.

- **Inheritance**:  
  The structure is ready for future extension, such as creating specialized vehicle types (e.g., `Car`, `Truck`, `Van`) that inherit from a base `Vehicle` class.

- **Polymorphism**:  
  Methods like `toString()` are overridden for custom output, and the design allows for future use of interfaces (e.g., a `Rentable` interface).

---

## 🛠️ Getting Started

### **Prerequisites**
- Java 17+ (or compatible version)
- Git (for cloning the repo)

### **Clone the Repository**
```sh
git clone https://github.com/<your-username>/vehicle-rental-system.git
cd vehicle-rental-system/code
```

### **Compile the Project**
```sh
javac -d . enums/*.java *.java
```

### **Run the Application**
```sh
java -cp . vehicle_rental_system.code.Main
```

---

## 📸 Demo

```
=======================================
   🚗  YO VEHICLE RENTAL SYSTEM  🚗   
=======================================
+-----------------------------------------+
|     Vehicle Rental System - Main Menu   |
+-----------------------------------------+
| 1. Add Location                         |
| 2. Add Vehicle to Location              |
| 3. Register Customer                    |
| 4. List Locations                       |
| 5. List Available Vehicles by Location  |
| 6. List Customers                       |
| 7. Create Rental Contract               |
| 8. Return Vehicle                       |
| 9. List Rental Contracts                |
| 10. Exit                                |
+-----------------------------------------+
   Enter your choice: 
```

---

## 📦 Project Structure

```
vehicle_rental_system/
│
├─ code/
│   ├─ enums/                # Enum types (FuelType, PaymentMethod, etc.)
│   ├─ Main.java             # Entry point
│   ├─ VehicleRentalSystem.java
│   ├─ ...                   # Other classes (Customer, Vehicle, etc.)
│
└─ README.md
```

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 💡 Author

[Yoni Berihun](https://github.com/Yoni-Berihun)

---

> _“Drive your ideas forward with code!”_cls
