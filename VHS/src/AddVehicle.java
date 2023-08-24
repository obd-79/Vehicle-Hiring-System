
import java.util.ArrayList;
import java.util.Scanner;

public class AddVehicle {
    
    public static int choice;
    public static Vehicle vehicle;
    public static Car car;
    public static ArrayList<Vehicle> vehicles;
    
    public static void run(ArrayList<Vehicle> vehicles) {
        
        AddVehicle.vehicles = vehicles;
        
        enterChoice();
        
    }
    
    private static int enterChoice() {
        Scanner input = new Scanner(System.in);
        
        int choice = 0;
        while(choice != 6) {
            System.out.println("<<< Add a Vehicle >>>");
            System.out.println("1. Sports Car");
            System.out.println("2. SW Car");
            System.out.println("3. SUV");
            System.out.println("4. Small Truck");
            System.out.println("5. Transport Truck");
            System.out.println("6. Return Back");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            AddVehicle.choice = choice;
            
            if(choice != 6) {
                vehicle = enterVehicleInfo();
                if(choice == 1 || choice == 2 || choice == 3) {
                    enterCarInfo();
                } else if (choice == 4 || choice == 5) {
                    enterTruckInfo();
                }

                System.out.println("added successfully");
            }
            

        }
        
        return choice;
    }
    
    private static Vehicle enterVehicleInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter plate number: ");
        String plateNumber = input.nextLine();
        System.out.print("Enter number of tires: ");
        int numberOfTires = input.nextInt();
        System.out.print("Enter daily fee: ");
        double dailyFee = input.nextDouble();
        Vehicle vehicle = new Vehicle(plateNumber, numberOfTires, dailyFee);
        return vehicle;
    }
    
    private static void enterCarInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter color as string: ");
        String color = input.nextLine();
        System.out.print("Enter seating capacity as number: ");
        int seatingCap = input.nextInt();
        System.out.print("Enter number of doors as number: ");
        int numOfDoors = input.nextInt();

        Car car = new Car(
                vehicle.plateNumber,
                vehicle.numberOfTires,
                vehicle.dailyFee,
                color,
                seatingCap,
                numOfDoors
        );
        
        switch(choice) {
            case 1:
                Sport sportCar = Sport.userInput(car);
                vehicles.add(sportCar);
                break;
            case 2:
                StationWagon swCar = StationWagon.userInput(car);
                vehicles.add(swCar);
                break;
            case 3:
                SUV suvCar = SUV.userInput(car);
                vehicles.add(suvCar);
                break;
        }
    }
    
    private static void enterTruckInfo() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter loading capacity: ");
        double loadingCapacity = input.nextDouble();

        switch(choice) {
            case 4:
                SmallTrucks smallTruck = new SmallTrucks(
                    vehicle.plateNumber,
                    vehicle.numberOfTires,
                    vehicle.dailyFee,
                    loadingCapacity
                );
                vehicles.add(smallTruck);
                break;
            case 5:
                TransportTrucks transportTruck = new TransportTrucks(
                    vehicle.plateNumber,
                    vehicle.numberOfTires,
                    vehicle.dailyFee,
                    loadingCapacity
                );
                vehicles.add(transportTruck);
                break;
        }
    }
    
}
