
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class VehiclePark {
    
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<RegisteredUser> customers = new ArrayList<>();;
    ArrayList<Admin> admins = new ArrayList<>();
    
    public void displayVehicles() {
        System.out.println("<<< Vehicles List >>>");
        vehicles.forEach(vehicle -> {
            System.out.println(vehicle);
        });
    }
    
    public void displayAvailableVehicles(String startDate, String endDate) throws ParseException {
        ArrayList<Vehicle> availableVehicles = getAvailableVehicles(startDate, endDate);
        System.out.println("<<< Available Vehicles List >>>");
        availableVehicles.forEach(vehicle -> {
            System.out.println(vehicle);
        });
    }
    
    private ArrayList<Vehicle> getAvailableVehicles(String startDate, String endDate) throws ParseException {
        Date enteredStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
        Date enteredEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDate);
        ArrayList<Vehicle> availableVehicles = new ArrayList<>();
        
        vehicles.forEach(vehicle -> {
            try {
                if(vehicle.status == VehicleStatus.AVAILABLE) {
                    availableVehicles.add(vehicle);
                } else {
                    Date vehicleStartDate = new SimpleDateFormat("dd/MM/yyyy").parse(vehicle.startDate);
                    Date vehicleEndDate = new SimpleDateFormat("dd/MM/yyyy").parse(vehicle.endDate);

                    long startDiff = enteredStartDate.getTime() - vehicleStartDate.getTime();
                    long endDiff = vehicleEndDate.getTime() - enteredEndDate.getTime();
                    if(startDiff >= 0 && endDiff >= 0) {
                        availableVehicles.add(vehicle);
                    }
                }

            } catch (ParseException ex) {}
        });
        
        return availableVehicles;
    }
    
    public void addVehicle() {
        AddVehicle.run(vehicles);
    }
    
    public void removeVehicle() {
        int indexToRemove = enterVehicleIndex();
        vehicles.remove(indexToRemove);
    }
    
    public void bookVehicle() {

        try {
            int indexToBook = enterVehicleIndex();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter start date in following pattern dd/mm/yyyy: ");
            String startDate = input.nextLine();
            System.out.print("Enter end date in following pattern dd/mm/yyyy: ");
            String endDate = input.nextLine();
            vehicles.get(indexToBook).bookMe(startDate, endDate);
        } catch (ParseException ex) {
            System.out.println("Enter valid date");
        } catch (SorryWeDontHaveThatOneException ex) {
            System.out.println("sorry you cannot book this vehicle");
        }
        
    }
    
    public void cancelBooking() {
        
        try {
            int indexToCancel = enterVehicleIndex();
            vehicles.get(indexToCancel).cancelMe();
        } catch (ParseException ex) {
            System.out.println("Enter valid date");
        } catch (NoCancellationYouMustPayException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void rentVehicle() {
        try {
            int indexToRent = enterVehicleIndex();
            Scanner input = new Scanner(System.in);
            System.out.print("Enter start date in following pattern dd/mm/yyyy: ");
            String startDate = input.nextLine();
            System.out.print("Enter end date in following pattern dd/mm/yyyy: ");
            String endDate = input.nextLine();
            vehicles.get(indexToRent).rentMe(startDate, endDate);
        } catch (ParseException ex) {
            System.out.println("Enter valid date");
        } catch (SorryWeDontHaveThatOneException ex) {
            System.out.println("sorry you cannot book this vehicle");
        }
    }
    
    public void dropVehicle() {
        int indexToDrop = enterVehicleIndex();
        vehicles.get(indexToDrop).dropMe();
    }
    
    private int enterVehicleIndex() {
        Scanner input = new Scanner(System.in);
        int index = -1;
        while(index == -1 || (index+1 <= vehicles.size())) {
            System.out.print("Enter vehicle number starting from 1 to " + vehicles.size() + ": ");
            index = input.nextInt();
        }

        return index - 1;
    }
    
    public void load() {
        int indexToLoad = enterVehicleIndex();
        Vehicle vehicle = vehicles.get(indexToLoad);
        if(vehicle instanceof Loadable) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter additional load: ");
                double load = input.nextDouble();
                ((Loadable) vehicle).loadMe(load);
            } catch (OverWeightException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public void dailyReport(String fileName) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/"+fileName));
            // Write Vehicles
            bw.write("<<< Vehicles List >>>\n");
            bw.write("Plate Number\tNumber of Tires\tDaily Fee\tStatus\t\tStart Date\tEnd Date\n");
            vehicles.forEach(vehicle -> {
                try {
                    bw.write(leftpad(vehicle.plateNumber,12)+"\t");
                    bw.write(leftpad(vehicle.numberOfTires+"",15)+"\t");
                    bw.write(leftpad(vehicle.dailyFee+"",9)+"\t");
                    if(vehicle.status == VehicleStatus.AVAILABLE) {
                        bw.write("available  ");
                    } else if (vehicle.status == VehicleStatus.BOOKED) {
                        bw.write("booked     ");
                        bw.write(vehicle.startDate+"\t");
                        bw.write(vehicle.endDate+"\t");
                    } else {
                        bw.write("rented     ");
                        bw.write(vehicle.startDate+"\t");
                        bw.write(vehicle.endDate+"\t");
                    }
                    bw.write("\n");
                } catch (IOException ex) {
                    System.out.println("Cannot write to file");
                }
            });
            
            bw.write("\n<<< Registered Users >>>\n");
            customers.forEach(customer -> {
                try {
                    bw.write(customer+"\n");
                } catch (IOException ex) {
                    System.out.println("Cannot write to file");
                }
            });
            
            bw.close();
        } catch (IOException ex) {
            System.out.println("Cannot write to file");
            ex.printStackTrace();
        }
        
        System.out.println(fileName+" generated successfully");
    }
    
    private String leftpad(String text, int length) {
        return String.format("%-" + length + "." + length + "s", text);
    }
}