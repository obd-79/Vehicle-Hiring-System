
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Test {
    
    static VehiclePark vehiclePark;
    
    public static void main(String[] args) throws ParseException {
        vehiclePark = new VehiclePark();
        loadCustomers();
        loadAdmins();

        printMainMenu();
        

    }
    
    private static void loadCustomers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("files/customers.txt"));
            String line;
            
            int maxId = 0;
            while((line = br.readLine()) != null) {
                String[] splittedLine = line.split("\\*");
                int id = Integer.parseInt(splittedLine[0]);
                String name = splittedLine[1];
                String phoneNumber = splittedLine[2];
                String emailAddress = splittedLine[3];
                String address = splittedLine[4];
                if(id > maxId) {
                    maxId = id;
                }
                RegisteredUser user = new RegisteredUser();
                user.id = id;
                user.name = name;
                user.phoneNumber = phoneNumber;
                user.emailAddress = emailAddress;
                user.address = address;
                vehiclePark.customers.add(user);
            }
            
            Person.lastId = maxId;
            
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Error reading");
        }
        
    }
    
    private static void loadAdmins() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("files/admins.txt"));
            String line;
            
            int maxId = -1;
            while((line = br.readLine()) != null) {
                String[] splittedLine = line.split("\\*");
                String name = splittedLine[0];
                String phoneNumber = splittedLine[1];
                String emailAddress = splittedLine[2];
                String address = splittedLine[3];
                String username = splittedLine[4];
                Admin admin = new Admin();
                admin.name = name;
                admin.phoneNumber = phoneNumber;
                admin.emailAddress = emailAddress;
                admin.address = address;
                admin.userName = username;
                vehiclePark.admins.add(admin);
            }
                        
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Error reading");
        }
        
    }
    
    private static void printMainMenu() {
        Scanner input = new Scanner(System.in);
        int userInput = -1;
        while(userInput != 6) {
            System.out.println("******************************************");
            System.out.println("**\t\tMain Menu\t\t**");
            System.out.println("******************************************");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles");
            System.out.println("3. Register me");
            System.out.println("4. Continue as registered user");
            System.out.println("5. Continue as admin");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            userInput = input.nextInt();
            
            switch(userInput) {
                case 1: 
                    vehiclePark.displayVehicles();
                    break;
                case 2:
                    displayAvailableVehicles();
                    break;
                case 3:
                    registerMe();
                    break;
                case 4:
                    continueAsRegistered();
                    break;
                case 5:
                    continueAsAdmin();
                    break;
            }
        }
        
        saveData();
        
    }
    
    private static void saveData() {
        
        // Save Customers
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/customers.txt"));
            vehiclePark.customers.forEach(customer -> {
                try {
                    bw.write(customer.id+"*");
                    bw.write(customer.name+"*");
                    bw.write(customer.phoneNumber+"*");
                    bw.write(customer.emailAddress+"*");
                    bw.write(customer.address);
                    bw.write("\n");
                } catch (IOException ex) {
                    System.out.println("Cannot write to file");
                }
                
            });
            bw.close();
        } catch (IOException ex) {
            System.out.println("Cannot write to file");
        }
        
        // Save Admins
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("files/admins.txt"));
            vehiclePark.admins.forEach(admin -> {
                try {
                    bw.write(admin.name+"*");
                    bw.write(admin.phoneNumber+"*");
                    bw.write(admin.emailAddress+"*");
                    bw.write(admin.address+"*");
                    bw.write(admin.userName);
                    bw.write("\n");
                } catch (IOException ex) {
                    System.out.println("Cannot write to file");
                }
                
            });
            bw.close();
        } catch (IOException ex) {
            System.out.println("Cannot write to file");
        }
    }
    
    private static void printMenu4() {
        Scanner input = new Scanner(System.in);
        int userInput = -1;
        while(userInput != 8) {
            System.out.println("***********************************");
            System.out.println("**\t\tMenu 4\t\t**");
            System.out.println("***********************************");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles");
            System.out.println("3. Display available vehicles by type");
            System.out.println("4. Book Vehicle");
            System.out.println("5. Cancel my booking");
            System.out.println("6. Rent vehicle");
            System.out.println("7. Drop vehicle");
            System.out.println("8. Quit");
            System.out.print("Enter your choice: ");
            userInput = input.nextInt();
            
            switch(userInput) {
                case 1: 
                    vehiclePark.displayVehicles();
                    break;
                case 2:
                    displayAvailableVehicles();
                    break;
                case 3:
                    displayAvailableVehiclesByType();
                    break;
            }
            
            if(userInput >= 4 && userInput <= 7) {
                
                if(vehiclePark.vehicles.size() > 0) {
                    switch(userInput) {
                        case 4:
                            vehiclePark.bookVehicle();
                            break;
                        case 5:
                            vehiclePark.cancelBooking();
                            break;
                        case 6:
                            vehiclePark.rentVehicle();
                            break;
                        case 7:
                            vehiclePark.dropVehicle();
                            break;
                    }
                } else {
                    System.out.println("No vehicles available");
                }

            }
        }
    }
    
    private static void printMenu5() {
        Scanner input = new Scanner(System.in);
        int userInput = -1;
        while(userInput != 6) {
            System.out.println("***********************************");
            System.out.println("**\t\tMenu 5\t\t**");
            System.out.println("***********************************");
            System.out.println("1. Display all vehicles");
            System.out.println("2. Display available vehicles");
            System.out.println("3. Add a new vehicle to the system");
            System.out.println("4. Remove vehicle");
            System.out.println("5. Reports");
            System.out.println("6. Quit");
            System.out.print("Enter your choice: ");
            userInput = input.nextInt();
            
            switch(userInput) {
                case 1: 
                    vehiclePark.displayVehicles();
                    break;
                case 2:
                    displayAvailableVehicles();
                    break;
                case 3:
                    AddVehicle.run(vehiclePark.vehicles);
                    break;
                case 4:
                    vehiclePark.removeVehicle();
                    break;
                case 5:
                    vehiclePark.dailyReport("report.txt");
                    break;
            }
        }
    }
    
    private static void displayAvailableVehicles() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter start date in following pattern dd/mm/yyyy: ");
        String startDate = input.nextLine();
        System.out.print("Enter end date in following pattern dd/mm/yyyy: ");
        String endDate = input.nextLine();

        try {
            vehiclePark.displayAvailableVehicles(startDate, endDate);
        } catch (ParseException ex) {
            System.out.println("Date is in wrong format");
        }
    }
    
    private static void displayAvailableVehiclesByType() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter vehicle's type (sport, suv, sw, small truck,transport truck): ");
        String type = input.nextLine();
        
        Class sortTypeClass = null;
        switch(type) {
            case "sport":
                sortTypeClass = Sport.class;
                break;
            case "suv":
                sortTypeClass = SUV.class;
                break;
            case "sw":
                sortTypeClass = StationWagon.class;
                break;
            case "small truck":
                sortTypeClass = SmallTrucks.class;
                break;
            case "transport truck":
                sortTypeClass = TransportTrucks.class;
                break;
        }
        
        if(sortTypeClass != null) {
            for(Vehicle vehicle: vehiclePark.vehicles) {
                if(sortTypeClass.isInstance(vehicle)){
                    System.out.print(vehicle);
                }
            }
        }
    }
    
    private static void registerMe() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = input.nextLine();
        System.out.print("Enter your email address: ");
        String email = input.nextLine();
        System.out.print("Enter your address: ");
        String address = input.nextLine();
        
        Person person = new RegisteredUser();
        
        person.id = ++Person.lastId;
        person.name = name;
        person.phoneNumber = phone;
        person.emailAddress = email;
        person.address = address;
        
        RegisteredUser user = (RegisteredUser) person;
        vehiclePark.customers.add(user);
        
        System.out.println("Added successfully");
    }
    
    private static void continueAsRegistered() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter user's id: ");
        int userId = input.nextInt();
        boolean found = false;
        for(RegisteredUser user: vehiclePark.customers) {
            if(userId == user.id) {
                System.out.println("Logged in successfully");
                printMenu4();
                found = true;
            }
        }
        if(!found) {
            System.out.println("Error id not found");
        }
    }
    
    private static void continueAsAdmin() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter admin's username: ");
        String userName = input.nextLine();
        boolean found = false;
        for(Admin admin: vehiclePark.admins) {
            if(userName.equals(admin.userName)) {
                System.out.println("Logged in successfully");
                found = true;
                printMenu5();
            }
        }
        if(!found) {
            System.out.println("Error username not found");
        }
    }
}
