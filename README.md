# Vehicle Hiring System (VHS) for VPark Company

## Introduction
The Vehicle Hiring System (VHS) is an Object-Oriented Programming (OOP) application designed to manage vehicle rentals for VPark company. It includes a wide range of features to handle different types of vehicles, rental rules, and user roles.

### Key Features
- **Vehicle Management**: Classify vehicles as Cars or Trucks with specific attributes.
- **Booking and Renting**: Book and rent vehicles with date specifications.
- **Loading Capability**: Handle additional load requirements.
- **User Roles**: Differentiate between guests, registered users, and admins.
- **Reporting**: Generate daily reports of the rental system.

## Classes and Structure

### Vehicle Class
- **Attributes**: plateNumber, numberOfTires, dailyFee.
- **Methods**: getDailyFee(), rentMe(), dropMe(), loadMe().

### Car and Truck Subclasses
- **Car Class**: Includes attributes like color, seatingCap, numOfDoors, and classifications like Sports, StationWagon, SUV.
- **Truck Class**: Defines loadingCap and classifications like SmallTrucks, TransportTrucks.

### Person Class
- **Subclasses**: RegisteredUser and Admin, each with specific attributes and functionalities.

### VehiclePark Class
- Manages the list of all vehicles, bookings, rentals, and registered customers.

### TestClass
- Serves as the main interface with various menu options for different user roles.

## Assumptions and Constraints
- All vehicles can be remotely delivered and dropped off unless otherwise stated.
- Trucks must be booked at least 7 days before rental.
- Specific exceptions are handled, such as SorryWeDontHaveThatOneException, NoCancellationYouMustPayException, OverWeightException.

## How to Run
1. Compile the project using your preferred Java compiler.
2. Run the main class (TestClass) to launch the application.
3. Follow the menu prompts to interact with the system.

## Challenges and Learnings
- This project provided valuable insights into OOP principles, class hierarchies, exception handling, and file operations.
- Challenges included managing complex relationships between classes and ensuring a user-friendly interface.

## Conclusion
The Vehicle Hiring System (VHS) showcases a comprehensive application of OOP concepts in a real-world scenario. It serves as a robust platform for vehicle rentals, catering to various user needs and administrative functions.
