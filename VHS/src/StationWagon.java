
import java.util.Scanner;

public class StationWagon extends Car implements Loadable {
    double loadingCap;
    double load;
    
    public StationWagon(String plateNumber, int numberOfTires, double dailyFee, String color, int seatingCap, int numOfDoors, double loadingCap) {
        super(plateNumber, numberOfTires, dailyFee, color, seatingCap, numOfDoors);
        this.loadingCap = loadingCap;
    }

    @Override
    public void loadMe(double additionalLoad) throws OverWeightException {
        if(this.load+additionalLoad <= loadingCap) {
            this.load+=additionalLoad;
        } else {
            throw new OverWeightException();
        }
    }
    
    public static StationWagon userInput(Car car) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter loading capacity: ");
        int loadingCap = input.nextInt();
        StationWagon swCar = new StationWagon(
                car.plateNumber,
                car.numberOfTires,
                car.dailyFee,
                car.color,
                car.seatingCap,
                car.numOfDoors,
                loadingCap
        );
        return swCar;
    }
}
