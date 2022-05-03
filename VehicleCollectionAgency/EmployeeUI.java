//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

import java.util.Scanner;
/**
 *
 * @author mathewmesfin
 */
public class EmployeeUI implements UserInterface {
    // no constructor needed, calls static methods of the SystemInterface
    // starts a “command loop” that repeatedly: (a) displays a menu of options, (b) gets the selected
    // option from the user, and (c) executes the corresponding command.
    
    private boolean quit = false;
    public void start(Scanner input) {
        
        int selection;
        
        //command loop
        while(!quit) {
            displayMenu();
            selection = getSelection(input);
            execute(selection, input);
        }
    }
    
    private void execute(int selection, Scanner input) {
        
        int veh_type = 0, num_days_used = 0, num_miles_driven = 0;
        String vin = "", creditcard_num; String[] display_lines;
        RentalDetails rental_details; ReservationDetails reserv_details;
        
        try {
            switch(selection) {
                // display rental rates
                case 1: System.out.println("\t__________View Current Rates__________");
                veh_type = getVehicleType(input);
                switch(veh_type){
                    case 1: display_lines = SystemInterface.getCarRates(); break;
                    case 2: display_lines = SystemInterface.getSUVRates(); break;
                    case 3: display_lines = SystemInterface.getTruckRates(); break;
                    default: display_lines = null;
                }
                displayResults(display_lines);
                break;
                // display available vehicles
                case 2: System.out.println("\t__________View Available Vehicles__________");
                veh_type = getVehicleType(input);
                switch(veh_type){
                    case 1: display_lines = SystemInterface.getAvailCars(); break;
                    case 2: display_lines = SystemInterface.getAvailSUVs(); break;
                    case 3: display_lines = SystemInterface.getAvailTrucks(); break;
                    default: display_lines = null;
                }
                displayResults(display_lines);
                break;
                // display estimated rental cost
                case 3: System.out.println("\t__________Calculate Estimated Rental Cost__________");
                rental_details = getRentalDetails(input);
                display_lines = SystemInterface.estimatedRentalCost(rental_details);
                displayResults(display_lines);
                break;
                // make a reservation
                case 4: System.out.println("\t__________Make A Reservation__________");
                reserv_details = getReservationDetails(input);
                display_lines = SystemInterface.makeReservation(reserv_details);
                displayResults(display_lines);
                break;
                // cancel a reservation
                case 5: System.out.println("\t__________Cancel A Reservation__________");
                vin = getVIN(input);
                creditcard_num = getCreditCardNum(input);
                display_lines = SystemInterface.cancelReservation(vin, creditcard_num);
                displayResults(display_lines);
                break;
                // view corporate account (and company reservations)
                case 6: System.out.println("\t__________View A Corporate Account__________");
                creditcard_num = getCreditCardNum(input);
                display_lines = SystemInterface.getAccount(creditcard_num);
                displayResults(display_lines);
                break;
                // process returned vehicle
                case 7: System.out.println("\t__________Process A Returned Vehicle__________");
                vin = getVIN(input);
                num_days_used = getNumDays(input);
                num_miles_driven = getNumMiles(input);
                display_lines = SystemInterface.processReturnedVehicle(vin,
                num_days_used,num_miles_driven);
                displayResults(display_lines);
                break;
                // quit program
                case 8: quit = true;
            }
        } catch(VehicleNotFoundException e) {
            System.out.println("** Vehicle " +vin+ " not found: Returning to main menu **");
        } catch(AccountNotFoundException e) {
            System.out.println("** Account not found: Returning to main menu **");
        } catch(InvalidCreditCardException e) {
            System.out.println("** Invalid credit card: Returning to main menu **");
        } catch(UnreservedVehicleException e) {
            System.out.println("** Vehicle " +vin+ " is not reserved: Returning to main menu **");
        } catch(ReservedVehicleException e) {
            System.out.println("** Vehicle " +vin+ " is already reserved: Returning to main menu **");
        } catch(InvalidInputException e) {
            System.out.println("** Invalid input: Returning to main menu **");
        }
    }
    
    // ------- private methods
    private void displayMenu() {
    // displays the menu of options
        String nLine = String.format("%37s","");
        System.out.println("\n\t__________MAIN MENU - EMLPLOYEE__________\n"
                      +"\n1 - View Current Rates               ... displays rental (and insurance rates)\n"
                                                +nLine+ "for one of cars, SUVs, or trucks\n"
                      +"\n2 - View Available Vehicles          ... displays available vehicles (cars, SUVs, or\n"
                                                +nLine+ "trucks\n"
                      +"\n3 - Calculate Estimated Rental Cost  ... displays estimated rental cost for given vehicle\n"
                                                +nLine+ "type, renal period, expectetd miles driven,\n"
                                                +nLine+ "optional daily insurance, and if Prime Customer\n"
                      +"\n4 - Make a Reservation               ... creates a reservation for VIN, credit card number,\n"
                                                +nLine+ "rental period, and insurance option\n"
                      +"\n5 - Cancel Reservation               ... cancels a reservation by VIN\n"
                      +"\n6 - View Corporate Account           ... displays account information for a given account\n"
                                                +nLine+ "number, including all current reservations\n"
                      +"\n7 - Process Returned Vehicle         ... requests VIN and actual number of miles driven\n"
                                                +nLine+ "and processes returned vehicle and displays\n"
                                                +nLine+ "total charge\n"
                      +"\n8 - Quit");
    }
    
    
    private int getSelection(Scanner input) {
    // prompts user for selection from menu (continues to prompt if selection < 1 or selection > 8)
        
        String in = "";
        int selection = 0;
        boolean valid = false;
        System.out.println("\tEnter the number corresponding to what you want to do: ");
        do {
            try {
                in = input.next();
                selection = Integer.valueOf(in);
                valid = (selection >= 1) && (selection <= 8);
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
    
    private String getVIN(Scanner input) {
    // prompts user to enter VIN for a given vehicle (does not do any error checking on the input)
        System.out.println("Enter the VIN: ");
        String vin = input.next();
        return vin;
    }
    
    private String getCompanyName(Scanner input) {
        // prompts the user to enter the company name(no need to check for errors)
        System.out.println("Enter the company name: ");
        String company_name = input.next();
        return company_name;
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
    
    private int getNumDays(Scanner input) throws InvalidInputException {
        // prompts user to enter the num of days relating to a reservation(checks if what they entered is an integer)
        int num_of_days = 0;
        boolean valid_num_days = false;
        int attempts = 3;
        System.out.println("Enter the number of days: ");
        do {
            try {
                String temp_num_days = input.next();
                num_of_days = Integer.valueOf(temp_num_days);
                valid_num_days = true;
            } catch(NumberFormatException e) {
                attempts--;
                System.out.println("** Invalid input: " +attempts+ " attempts remaining");
            }
        } while(!valid_num_days && attempts != 0);
        if(attempts == 0) {
            throw new InvalidInputException();
        }
        return num_of_days;
    }
    
    private int getNumMiles(Scanner input) throws InvalidInputException {
        // prompts user to enter the num of miles relating to a reservation(checks if what they entered is an integer)
        int num_miles = 0;
        boolean valid_num_miles = false;
        int attempts = 3;
        System.out.println("Enter the number of miles: ");
        do {
            try {
                String temp_num_miles = input.next();
                num_miles = Integer.valueOf(temp_num_miles);
                valid_num_miles = true;
            } catch(NumberFormatException e) {
                attempts--;
                System.out.println("** Invalid input: " +attempts+ " attempts remaining **");
            }
        } while(!valid_num_miles && attempts != 0);
        if(attempts == 0) {
            throw new InvalidInputException();
        }
        
        return num_miles;
    }
    
    private boolean getInsuranceSelected(Scanner input) throws InvalidInputException {
        // prompts the user to enter whether they have insurance or not(continues to prompt until a valid response)
        boolean insurance_selected;
        
        String response;
        boolean valid_response;
        int attempts = 3;
        
        System.out.println("Insurance? Enter \"Y\" for yes or \"N\" for no: ");
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
            case "Y": insurance_selected = true; break;
            case "N": insurance_selected = false; break;
            default : insurance_selected = false;
        }
        return insurance_selected;
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
    
    private TimeSpan getRentalPeriod(Scanner input) throws InvalidInputException{
        
        // --------Start of getting the time unit
        String temp_time_unit = "";
        boolean valid_time_unit;
        int unit_attempts = 3;
        
        System.out.println("D - days\tW - weeks\tM - months\nEnter the letter corresponding to the prefered time unit: ");
        
        do {
            temp_time_unit = input.next();
            temp_time_unit = temp_time_unit.toUpperCase();
            valid_time_unit = (temp_time_unit.equals("D") || temp_time_unit.equals("W") || temp_time_unit.equals("M"));
            if(!valid_time_unit) {
                unit_attempts--;
                System.out.println("** Invalid input: " +unit_attempts+ " attempts remaining **");
            }
        } while(!valid_time_unit && unit_attempts != 0);
        if(unit_attempts == 0) {
            throw new InvalidInputException();
        }
        char time_unit = temp_time_unit.charAt(0);
        
        // --------Start of getting the number of units
        int num_unit = 0;
        String time_unit_word = ((time_unit == 'D')? "Days": (time_unit == 'W')? "Weeks": (time_unit == 'M')? "Months": "");
        boolean valid_num_unit = false;
        int numOfTime_attempts = 3;
        
        System.out.println("Enter the number of " + time_unit_word + " planned to rent the Vehicle: ");
        
        do {
            try {
                String temp_num_unit = input.next();
                num_unit = Integer.valueOf(temp_num_unit);
                valid_num_unit = true;
            } catch(NumberFormatException e) {
                numOfTime_attempts--;
                System.out.println("** Invalid input: " +numOfTime_attempts+ " attempts remaining **");
            }
        } while(!valid_num_unit && numOfTime_attempts != 0);
        if(numOfTime_attempts == 0) {
            throw new InvalidInputException();
        }
        //constructing the TimeSpan object
        TimeSpan rental_period = new TimeSpan(num_unit, time_unit);
        return rental_period;
    }
            
    private RentalDetails getRentalDetails(Scanner input) throws InvalidInputException {
    // prompts user to enter required information for an estimated rental cost (vehicle type, estimated
    // number of miles expected to be driven, rental period (number of days, weeks or months), and
    // insurance option, returning the result packaged as a RentalDetails object (to be passed in method
    // calls to the SystemInterface)
        
        int vehicle_type = getVehicleType(input);

        TimeSpan rental_period = getRentalPeriod(input);
        
        int num_miles = getNumMiles(input);
        
        boolean insurance_option = getInsuranceSelected(input);
        
        boolean prime_customer = getPrimeCustomer(input);
        
        RentalDetails renDetails = new RentalDetails(vehicle_type, rental_period, num_miles, insurance_option, prime_customer);
        return renDetails;
    }
    
    private ReservationDetails getReservationDetails(Scanner input) throws InvalidCreditCardException, InvalidInputException {
        
        String vin = getVIN(input);
        
        String creditcard_num = getCreditCardNum(input);
        
        TimeSpan rental_period = getRentalPeriod(input);
        
        boolean insurance_option = getInsuranceSelected(input);
        
        ReservationDetails resvDetails = new ReservationDetails(vin, creditcard_num, rental_period, insurance_option);
        return resvDetails;
    }
    // prompts user to enter required information for making a reservation (VIN of vehicle to reserve,
    // credit card num, rental period, and insurance option), returning the result packaged as a
    // ReservationDetails object (to be passed in method calls to the SystemInterface)
    
    private void displayResults(String[] lines) {
        for(int i = 0;i < lines.length;i++) {
            System.out.println(lines[i]);
        }
    }
    // displays the array of strings passed, one string per screen line
    
    
}
