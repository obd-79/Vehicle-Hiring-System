public class Truck extends Vehicle implements Loadable {
    
    double loadingCap;
    double load;
    
    public Truck(String plateNumber, int numberOfTires, double dailyFee, double loadingCap) {
        super(plateNumber, numberOfTires, dailyFee);
        this.dailyFee = dailyFee;
        this.loadingCap = loadingCap;
        this.bookable = true;
    }

    @Override
    public void loadMe(double additionalLoad) throws OverWeightException {
        if(this.load+additionalLoad <= loadingCap) {
            this.load+=additionalLoad;
        } else {
            throw new OverWeightException();
        }
    }
    
}