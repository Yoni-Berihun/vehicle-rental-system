Vehicle Rental System

Overview

A console-based Java application designed to manage a vehicle rental business. It allows users to register customers, add vehicles and locations, create rental contracts, calculate costs, and process vehicle returns.

Features





Register customers and manage payment methods.



Add rental locations and vehicles with details (e.g., make, model, price).



Create rental contracts with customizable rental periods and insurance options.



Return vehicles and update contract statuses using RentalStatus enum.



Display lists of customers, locations, vehicles, and contracts.

How to Run





Prerequisites: Ensure you have Java 8 or later installed. Check with java -version.



Clone the Repository:

git clone https://github.com/Yoni-Berihun/vehicle-rental-system.git
cd vehicle-rental-system



Compile the Code:

javac -d . src/vehicle_rental_system/code/*.java



Run the Program:

java vehicle_rental_system.code.Main



Follow the on-screen menu to interact with the system (e.g., select option 7 to create a rental contract).

OOP Principles





Encapsulation: Implemented with private fields (e.g., contractId in RentalContract) and getters to ensure data safety.



Abstraction: Basic use in methods like createRentalContract; future plans include a RentalManager class.



Inheritance: Not used yet; potential for a Vehicle hierarchy (e.g., Car, Van).



Polymorphism: Minimal use (e.g., toString); future plans include a Rentable interface.

Limitations





Data is not persisted (lost when the program exits).



Console-based interface with no graphical user interface.

Future Improvements





Add database support for data persistence.



Implement a GUI using JavaFX.



Include late fee calculations and vehicle maintenance tracking.

License

This project is licensed under the MIT License - see the LICENSE file for details.

Contributing

Feedback and suggestions are welcome! Please open an issue or submit a pull request.