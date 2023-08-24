public class Car extends Vehicle {
    String color;
    int seatingCap;
    int numOfDoors;
    
    public Car(String plateNumber, int numberOfTires, double dailyFee, String color, int seatingCap, int numOfDoors) {
        super(plateNumber, numberOfTires, dailyFee);
        this.color = color;
        this.seatingCap = seatingCap;
        this.numOfDoors = numOfDoors;
    }
}
