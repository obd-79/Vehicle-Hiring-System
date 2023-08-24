
import java.util.Scanner;

public class SUV extends Car {
    String wheelDrive;
    
    public SUV(String plateNumber, int numberOfTires, double dailyFee, String color, int seatingCap, int numOfDoors, String wheelDrive) {
        super(plateNumber, numberOfTires, dailyFee, color, seatingCap, numOfDoors);
        this.wheelDrive = wheelDrive;
    }
    
    public static SUV userInput(Car car) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter wheel drive: ");
        String wheelDrive = input.nextLine();
        SUV suvCar = new SUV(
            car.plateNumber,
            car.numberOfTires,
            car.dailyFee,
            car.color,
            car.seatingCap,
            car.numOfDoors,
            wheelDrive
        );
        return suvCar;
    }
}