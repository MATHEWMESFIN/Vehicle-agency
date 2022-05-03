//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

import java.util.Scanner;
/**
 *
 * @author mathewmesfin
 */
public class ManagerUI implements UserInterface {
    
    private boolean quit = false;
    
    public void start(Scanner input) {
        
        int selection;
        
        // command loop
        while(!quit) {
            displayMenu();
            selection = getSelection(input);
            execute(selection, input);
        }
    }
    
    private void execute(int selection, Scanner input) {
        
        String[] display_lines;
        int vehicle_type;
        String creditcard_num, company_name;
        boolean prime_customer;
        
        try {
            switch(selection) {
                // View/Update Rates
                case 1: System.out.println("\t__________View / Update Rates__________");
                vehicle_type = getVehicleType(input);
                switch(vehicle_type) {
                    case 1: display_lines = SystemInterface.getCarRates(); break;
                    case 2: display_lines = SystemInterface.getSUVRates(); break;
                    case 3: display_lines = SystemInterface.getTruckRates(); break;
                    default: display_lines = null;
                }
                displayResults(display_lines);
                newVehicleRates(input, vehicle_type);
                break;
                // View All Vehicles
                case 2: System.out.println("\t__________View All Vehicles__________");
                display_lines = SystemInterface.getAllVehicles();
                displayResults(display_lines);
                break;
                // Add an Account
                case 3: System.out.println("\t__________Add An Account__________");
                creditcard_num = getCreditCardNum(input);
                company_name = getCompanyName(input);
                prime_customer = getPrimeCustomer(input);
                display_lines = SystemInterface.addAccount(creditcard_num, company_name, prime_customer);
                displayResults(display_lines);
                break;
                // View all Reservations
                case 4: System.out.println("\t__________View All Reservations__________");
                display_lines = SystemInterface.getAllReservations();
                displayResults(display_lines);
                break;
                // View all Accounts
                case 5: System.out.println("\t__________View All Accounts__________");
                display_lines = SystemInterface.getAllAccounts();
                displayResults(display_lines);
                break;
                // View all Transactions
                case 6: System.out.println("\t__________View All Transactions__________");
                display_lines = SystemInterface.getAllTransactions();
                displayResults(display_lines);
                break;
                // quit program
                case 7: quit = true;
            } 
        } catch(InvalidInputException e) {
            System.out.println("** Invalid input: Returning to main menu **");
        } catch(InvalidCreditCardException e) {
            System.out.println("** Invalid credit card: Returning to main menu **");
        } catch(UnreservedVehicleException e) {
            System.out.println("** System Error: Returning to main menu **");
        }
    }
    
    private void displayMenu() {
        // displays the menu of options
        System.out.println("\n\t__________MAIN MENU - MANAGER__________\n"
                + "\n1 - View/Update Rates             ... allows updating of rental and insurance rates\n"
                + "\n2 - View All Vehicles             ... displays all vehicles of the agency\n"
                + "\n3 - Add Account                   ... allows entry of a new customer account\n"
                + "\n4 - View All Reservations         ... displays all current reservations\n"
                + "\n5 - View All Accounts             ... displays all customer accounts\n"
                + "\n6 - View All Transactions         ... displays all transactions\n"
                + "\n7 - Quit");
    }
    
    private int getSelection(Scanner input) {
        // prompts user for selection from menu (continues to prompt if selection < 1 or selection > 7)
        String in = "";
        int selection = 0;
        boolean valid = false;
        System.out.println("Enter the number corresponding to what you want to do: ");
        do {
            try {
                in = input.next();
                selection = Integer.valueOf(in);
                valid = (selection >= 1) && (selection <= 7);
                if(!valid)
                    System.out.println("** Invalid input, please try again **");
            } catch(NumberFormatException e) {
                System.out.println("** Invalid input, please try again **");
            }
        } while(!valid);
        
        return selection;
    }
    
    private int getVehicleType(Scanner input) throws InvalidInputException {
        // prompts user to enter 1, 2, or 3, and returns (continues to prompt user if invalid input given)
        
        String temp = "";
        int vType = 0;
        int attempts = 3;
        boolean invalid = false; //true if input = 1, 2, or 3
        
        System.out.println("1 - Car\n2 - SUV\n3 - Truck\nEnter the number corresponding to the vehicle type: ");
        do {
            try{
                temp = input.next();
                vType = Integer.valueOf(temp);
                invalid = ((vType < 1) || (vType > 3));
                if(invalid) {
                    attempts--;
                    System.out.println("** Invalid input: " +attempts+ " attempts remaining **");
                }
            } catch(NumberFormatException e) {
                attempts--;
                System.out.println("** Invalid input: " +attempts+ " attempts remaining **");
            }
        } while(invalid && (attempts != 0));
        if(attempts == 0) {
            throw new InvalidInputException();
        }
        return vType;
    }
    
    private String getCreditCardNum(Scanner input) throws InvalidCreditCardException {
        // prompts user to enter the credit card number(checks if the credit card is all numbers and a length of 16)
        String creditCardNum;
        int attempts = 3;
        System.out.println("Enter the credit card number: ");
        do {
            creditCardNum = input.next();
            if(creditCardNum.length() != 16) {
                attempts--;
                System.out.println("** Invalid credit card number: " +attempts+ " attempts remaining **");
            } else {
                for(int i = 0;i < 16;i++) {
                    char c = creditCardNum.charAt(i);
                    if(!(c >= '0' && c <= '9')) {
                        attempts--;
                        System.out.println("** Invalid credit card number: " +attempts+ " attempts remaining **");
                        break;
                    } else if(i == 15) {
                        return creditCardNum;
                    }
                }
            }
        }while(attempts != 0);
        
        throw new InvalidCreditCardException();
    }
    
    private String getCompanyName(Scanner input) {
        // prompts the user to enter the company name(no need to check for errors)
        System.out.println("Enter the company name: ");
        String company_name = input.next();
        return company_name;
    }
    
    private boolean getPrimeCustomer(Scanner input) throws InvalidInputException {
        // prompts the user to enter whether they are a prime customer or not(continues to prompt until a valid response)
        boolean prime_customer;
        
        String response;
        boolean valid_response;
        int attempts = 3;
        
        System.out.println("Prime customer? Enter \"Y\" for yes or \"N\" for no: ");
        do {
            response = input.next().toUpperCase();
            valid_response = (response.equals("Y") || response.equals("N"));
            if(!valid_response) {
                attempts--;
                System.out.println("** Invalid input: " +attempts+ " attempts remaining **");
            }
        }while(!valid_response && attempts != 0);
        if(attempts == 0) {
            throw new InvalidInputException();
        }
        switch(response) {
            case "Y": prime_customer = true; break;
            case "N": prime_customer = false; break;
            default : prime_customer = false;
        }
        return prime_customer;
    }
    
    private void newVehicleRates(Scanner input, int vehicle_type) throws InvalidInputException {
        // prompts for the new vehicle rates
        double daily_rate, weekly_rate, monthly_rate, mileage_chrg, daily_insur_rate;
        VehicleRates new_rates;
        
        System.out.println("Enter the new daily rate: ");
        daily_rate = getValidRates(input);
        
        System.out.println("Enter the new weekly rate: ");
        weekly_rate = getValidRates(input);
        
        System.out.println("Enter the new monthly rate: ");
        monthly_rate = getValidRates(input);
        
        System.out.println("Enter the new mileage charge: ");
        mileage_chrg = getValidRates(input);
        
        System.out.println("Enter the new daily insurance rate: ");
        daily_insur_rate = getValidRates(input);
        
        switch(vehicle_type) {
            case 1: new_rates = new CarRates(daily_rate, weekly_rate, monthly_rate, mileage_chrg, daily_insur_rate);
            SystemInterface.updateCarRates(new_rates);
            case 2: new_rates = new SUVRates(daily_rate, weekly_rate, monthly_rate, mileage_chrg, daily_insur_rate);
            SystemInterface.updateSUVRates(new_rates);
            case 3: new_rates = new TruckRates(daily_rate, weekly_rate, monthly_rate, mileage_chrg, daily_insur_rate);
            SystemInterface.updateTruckRates(new_rates);
            default: new_rates = null;
        }
        
    }
    
    private double getValidRates(Scanner input) throws InvalidInputException {
        // checks for error in the newly entered rates 
        // returns the entered rate if no errors
        // returns an InvalidInputException if errors found
        int attempts = 3;
        boolean valid_rate = false;
        double new_rate = 0;
        do {
            try {
                String temp = input.next();
                new_rate = Integer.valueOf(temp);
                new_rate = Math.round(new_rate * 100.0)/100.0; //Rounds to the nearest hundreth
                valid_rate = true;
            } catch (NumberFormatException e) {
                attempts--;
                System.out.println("** Invalid input: " +attempts+ " attempts remaining **");
            }
        }while(!valid_rate && attempts != 0);
        if(attempts == 0) {
            throw new InvalidInputException();
        }
        return new_rate;
    }
    
    private void displayResults(String[] lines) {
        for(int i = 0;i < lines.length;i++) {
            System.out.println(lines[i]);
        }
    }
    // displays the array of strings passed, one string per screen line
}
