
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle {
    
    String plateNumber;
    int numberOfTires;
    double dailyFee;
    VehicleStatus status = VehicleStatus.AVAILABLE;
    String startDate = null;
    String endDate = null;
    boolean bookable;
    
    public Vehicle(String plateNumber, int numberOfTires, double dailyFee) {
        this.plateNumber = plateNumber;
        this.numberOfTires = numberOfTires;
        this.dailyFee = dailyFee;
    }
    
    public double getDailyFee() throws ParseException{
        Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(this.startDate);
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(this.endDate);

        double numberOfDays = (long) (endDate.getTime() - startDate.getTime())/(1000*60*60*24);

        return numberOfDays * dailyFee;
    }
    
    public void bookMe(String startDate, String endDate) throws ParseException, SorryWeDontHaveThatOneException {
        
        if(status == VehicleStatus.AVAILABLE) {
            this.startDate = startDate;
            this.endDate = endDate;
            status = VehicleStatus.BOOKED;
            
            System.out.println("Booked Successfully");
        } else {
            System.out.println("Cannot book car because it's booked before");
            throw new SorryWeDontHaveThatOneException();
        }
        
    }
    
    public void cancelMe() throws ParseException, NoCancellationYouMustPayException {
        
        Date currentDate = new Date();
        Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(this.endDate);
        
        double numberOfDays = (long) (currentDate.getTime() - endDate.getTime())/(1000*60*60*24);
        if(numberOfDays >= 0) {
            status = VehicleStatus.AVAILABLE;
            startDate = null;
            this.endDate = null;
            System.out.println("Book cancelled successfully");
        } else {
            throw new NoCancellationYouMustPayException();
        }

    }
    
    public void rentMe(String startDate, String endDate) throws ParseException, SorryWeDontHaveThatOneException {
        
        if(status == VehicleStatus.AVAILABLE) {
            this.startDate = startDate;
            this.endDate = endDate;
            status = VehicleStatus.RENTED;
            System.out.println("Rented Successfully");
        } else {
            System.out.println("Cannot book vehcile because it's booked before");
            throw new SorryWeDontHaveThatOneException();
        }
        
    }
    
    public void dropMe() {
        try {
            System.out.println("Total price is: "+getDailyFee()+"$");
            status = VehicleStatus.AVAILABLE;
        } catch (ParseException ex) {}
    }
    
    private String vehicleEnumToString() {
        switch (status) {
            case AVAILABLE:
                return "available";
            case BOOKED:
                return "booked";
            case RENTED:
                return "rented";
        }
        
        return "";
    }
    
    public String toString() {
        String output = "";
        output+="Plate Number: "+plateNumber+"\n";
        output+="Number Of Tires: "+numberOfTires+"\n";
        output+="Daily Fee: "+dailyFee+"\n";
        output+="Status: "+vehicleEnumToString()+"\n";
        output+="Start Date: "+startDate+"\n";
        output+="End Date: "+endDate+"\n";
        return output;
    }
     
}