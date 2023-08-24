
import java.util.Scanner;

public class Sport extends Car {
    double horsePower;
    
    public Sport(String plateNumber, int numberOfTires, double dailyFee, String color, int seatingCap, int numOfDoors, double horsePower) {
        super(plateNumber, numberOfTires, dailyFee, color, seatingCap, numOfDoors);
        this.horsePower = horsePower;
        bookable = true;
    }
    
    public static Sport userInput(Car car) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter horse power: ");
        double horsePower = input.nextDouble();
        Sport sportCar = new Sport(
                car.plateNumber,
                car.numberOfTires,
                car.dailyFee,
                car.color,
                car.seatingCap,
                car.numOfDoors,
                horsePower
        );
        return sportCar;
    }
}
