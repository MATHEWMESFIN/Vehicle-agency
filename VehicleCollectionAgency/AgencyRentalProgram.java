//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

import java.util.Scanner;
/**
 *
 * @author mathewmesfin
 */
public class AgencyRentalProgram {
    
    public static void main(String[ ] args) {
        
        // Provide Hard-coded Current Agency Rates
        CurrentRates agency_rates = new CurrentRates(new CarRates(24.95, 169.95, 514.95, 0.16, 14.95), 
                new SUVRates(29.95, 189.95, 679.95, 0.16, 14.95), 
                new TruckRates(36.95, 224.95, 687.95, 0.26, 21.95));
        
        // Create an Initially Empty Vehicles Collection, and Populate
        Vehicles agency_vehicles = new Vehicles();
        populate(agency_vehicles); // supporting private static method (to be added)
        
        // Create Initially Empty Accounts and Transactions Objects
        Accounts accounts = new Accounts();
        Transactions transactions = new Transactions();
        
        // Establish User Interface
        Scanner input = new Scanner(System.in);
        UserInterface ui;
        boolean quit = false;
        
        // Create Requested UI and Begin Execution
        while(!quit) { // (allows switching between Employee and Manager user interfaces while running)
            ui = getUI(input);
            if(ui == null)
                quit = true;
            else {
                // Init System Interface with Agency Data (if not already initialized)
                if(!SystemInterface.initialized())
                    SystemInterface.initSystem(agency_rates, agency_vehicles, accounts, transactions);
                // Start User Interface
                ui.start(input);
            }
        }
    }
    
    public static UserInterface getUI(Scanner input) {
        
        UserInterface ui = null;
        boolean valid_selection = false;
        int selection;
        
        while(!valid_selection) {
            System.out.println("1 – Employee, 2 – Manager, 3 – quit: ");
            selection = input.nextInt();
            
            if(selection == 1) {
                ui = new EmployeeUI();
                valid_selection = true;
            } 
            else if(selection == 2) {
                ui = new ManagerUI();
                valid_selection = true;
            } 
            else if(selection == 3) {
                ui = null;
                valid_selection = true;
            } else
                System.out.println("Invalid selection – please reenter");
            }
        return ui;
    }
    
    public static void populate(Vehicles agency_vehicles) {
        
        //Cars (description, mpg, vin, seating_capacity)
        agency_vehicles.add(new Car("Chevrolet Camaro - 2018", 30, "KH4GM4564GQ", 2));
        
        agency_vehicles.add(new Car("Ford Fusion - 2018", 34, "AB4FG5689GM", 4));
        
        agency_vehicles.add(new Car("Ford Fusion Hybrid", 32, "KV4EG3245RV", 4));
        
        agency_vehicles.add(new Car("Chevrolet Impala - 2019", 30, "RK3MB3366YH", 4));
        
        //SUVs (description, mpg, vin, seating_capacity, cargo_capacity)
        
        agency_vehicles.add(new SUV("Honda Odyssey - 2020", 28, "VM9RF2635TD", 7, 6));
        
        agency_vehicles.add(new SUV("Dodge Caravan - 2019", 25, "QK3FT4273NE", 5, 4));
        
        agency_vehicles.add(new SUV("Ford Expedition - 2018", 20, "JK2HL8264HY", 5, 3));
        
        //Trucks (description, mpg, vin, loading_capacity)
        
        agency_vehicles.add(new Truck("Ten-Foot", 12, "EJ5KU2437BD", 2810));
        
        agency_vehicles.add(new Truck("Eighteen-Foot", 10, "KG4MD5372RK", 5950));
        
        agency_vehicles.add(new Truck("Twenty-Four-Foot", 8, "EB2WR3082OB", 6500));
        
        agency_vehicles.add(new Truck("Twenty-Four-Foot", 8, "TV3GH4390FK", 6510));
    } 
}
