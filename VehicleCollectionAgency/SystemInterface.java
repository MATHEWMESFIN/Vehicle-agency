//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class SystemInterface {
    
    private static CurrentRates agency_rates;
    private static Vehicles agency_vehicles;
    private static Accounts accounts;
    private static Transactions transactions_history;
    
    public static void initSystem(CurrentRates r, Vehicles v, Accounts a, Transactions t) {
        agency_rates = r;
        agency_vehicles = v;
        accounts = a;
        transactions_history = t;
    }
    
    public static boolean initialized() {
        return agency_vehicles != null;
    }
    
    // Note that methods makeReservation, cancelReservation, addAccount, and updateXXXRates return
    // an acknowledgement of successful completion of the requested action (e.g. "Vehicle QK3FL4273ME
    // successfully reserved"). Method processReturnedVehicle returns the final cost for the returned
    // vehicle (e.g., "Total charge for VIN QK3FL4273ME for 3 days, 233 miles @ 0.15/mile and daily
    // insurance @ 14.95/day (with 100 miles credit as Prime customer) = $xxx.xx.)
    
    // Current Rates Related Methods
    public static String[ ] getCarRates() { 
        String[] GCROutput = new String[1];
        GCROutput[0] = agency_rates.getCarRates().toString();
        return GCROutput;
    }
    public static String[ ] getSUVRates() { 
        String[] GSROutput = new String[1];
        GSROutput[0] = agency_rates.getSUVRates().toString();
        return GSROutput;
    }
    public static String[ ] getTruckRates() { 
        String[] GTROutput = new String[1];
        GTROutput[0] = agency_rates.getTruckRates().toString();
        return GTROutput;
    }
    
    public static String[ ] updateCarRates(VehicleRates r) { 
        String[] UCROutput = new String[1];
        agency_rates.setCarRates(r);
        UCROutput[0] = "** Car rates successfully updated **";
        return UCROutput;
    }
    public static String[ ] updateSUVRates(VehicleRates r) { 
        String[] USROutput = new String[1];
        agency_rates.setSUVRates(r);
        USROutput[0] = "** SUV rates successfully updated **";
        return USROutput;
    }
    public static String[ ] updateTruckRates(VehicleRates r) { 
        String[] UTROutput = new String[1];
        agency_rates.setTruckRates(r);
        UTROutput[0] = "** Truck rates successfully updated **";
        return UTROutput;
    }
    
    public static String[ ] estimatedRentalCost(RentalDetails details) { 
        
        String[] ERCOutput = new String[1];
        
        double estimate = agency_rates.calcEstimatedCost(details.getVehicleType(), details.getRentalPeriod(), 
                details.getNumMiles(), details.getInsuranceOption(), details.getPrimeCustomer());
        
        String vehicleType = "";
        switch(details.getVehicleType()) {
            case 1: vehicleType = "car"; break;
            case 2: vehicleType = "SUV"; break;
            case 3: vehicleType = "truck"; break;
        }
        
        String insured = (details.getInsuranceOption()) ? "with insurance" : "with no insurance";
        String prime = (details.getPrimeCustomer()) ? "(with 100 miles credit as a prime customer)" : "";
        
        ERCOutput[0] = "The estimated charge for a " + vehicleType + " for " + details.getRentalPeriod().toString() + ", " 
                + details.getNumMiles() + " miles and " + insured + " " + prime + " = $" + estimate;
        return ERCOutput;
    }
    
    public static String[ ] processReturnedVehicle(String vin, int num_days_used, int num_miles_driven) 
            throws VehicleNotFoundException, AccountNotFoundException, InvalidCreditCardException, UnreservedVehicleException{ 
        
        String[] PRVOutput = new String[1];
        
        Vehicle v;
        Account a;
        VehicleRates rates;
        String creditCardNum;
        boolean insured;
        boolean primeCustomer;
        double totalCharge;
        
        int vehicle_type = 0; //to be used for adding transaction
        
        
        v = agency_vehicles.getVehicle(vin);
            
        rates = v.getQuotedRates();
        insured = v.getReservation().getInsuranceSelected();
        creditCardNum = v.getReservation().getCreditCardNum();
        a = accounts.getAccount(creditCardNum);
        primeCustomer = a.primeCustomer();
            
        totalCharge = agency_rates.calcActualCost(rates, num_days_used, num_miles_driven, insured, primeCustomer);
        totalCharge = Math.round(totalCharge * 100.0)/100.0; //Rounds to the nearest hundreth
        
        // ------after processing the vehicle, the reservation is canceled
        v.cancelReservation(); 
        
        if(v instanceof Car) {
            vehicle_type = 1;
        } else if(v instanceof SUV) {
            vehicle_type = 2;
        } else if(v instanceof Truck) {
            vehicle_type = 3;
        }
        
        String cost = String.valueOf(totalCharge);
        String days_used = String.valueOf(num_days_used);
        String miles_driven = String.valueOf(num_miles_driven);
        
        // ------after processing the vehicle, the transaction is added
        addTransactions(creditCardNum, a.getCompanyName(), vehicle_type, vin, days_used, miles_driven, cost);
                
        String insured_String = (insured)? "daily insurance at " + rates.getDailyInsurRate() + "/day"
                : "no daily insurance";
        String primeCustomer_String = (primeCustomer)? "(with 100 miles credit as a Prime Customer)"
                : "";
        PRVOutput[0] = "Total Charge for Vehicle " + vin + " for " + num_days_used + " days, "
                + num_miles_driven + " miles at " + rates.getMileageChrg() + "/mile and "
                + insured_String + " " + primeCustomer_String + " = $" + totalCharge;
        
        return PRVOutput;
    }
    
    // Note that the rates to be used are retrieved from the VehicleRates object stored in the specific rented
    // vehicle object, the daily insurance option is retrieved from the Reservation object of the rented
    // vehicle, and whether they are a Prime customer is retrived from the Customer Account object vehicle
    // associated with the Reservation object associated with the specific rented vehicle.
    
    // Vehicle Related Methods
    public static String[ ] getAvailCars() {
        // count the number of available cars
        int num_cars = 0;
        Vehicle v;
        
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            v = agency_vehicles.next();
            if((v instanceof Car) && !(v.isReserved()))
                num_cars++;
        }
        // create appropriate size array
        String[ ] avail_cars = new String[num_cars];
        
        // populate the array with available cars
        int i = 0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            v = agency_vehicles.next();
            if((v instanceof Car) && !(v.isReserved()))
                avail_cars[i++] = v.toString();
        }
        
        return avail_cars;
    }
    
    public static String[ ] getAvailSUVs() {
        
        int num_SUVs = 0;
        Vehicle v;
        
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            v = agency_vehicles.next();
            if((v instanceof SUV) && !(v.isReserved()))
                num_SUVs++;
        }
        // create appropriate size array
        String[ ] avail_SUVs = new String[num_SUVs];
        
        // populate the array with available SUVs
        int i = 0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            v = agency_vehicles.next();
            if((v instanceof SUV) && !(v.isReserved()))
                avail_SUVs[i++] = v.toString();
        }
        
        return avail_SUVs;
    }
    
    public static String[ ] getAvailTrucks() {
        
        int num_Trucks = 0;
        Vehicle v;
        
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            v = agency_vehicles.next();
            if((v instanceof Truck) && !(v.isReserved()))
                num_Trucks++;
        }
        // create appropriate size array
        String[ ] avail_Trucks = new String[num_Trucks];
        
        // populate the array with available Trucks
        int i = 0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            v = agency_vehicles.next();
            if((v instanceof Truck) && !(v.isReserved()))
                avail_Trucks[i++] = v.toString();
        }
        
        return avail_Trucks;
    }
    
    public static String[ ] getAllVehicles() {
        
        int num_vehs = 0;
        
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            agency_vehicles.next();
            num_vehs++;
        }
        // create appropriate size array
        String[ ] avail_vehs = new String[num_vehs];
        
        // populate the array with available cars
        int i = 0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            avail_vehs[i++] = agency_vehicles.next().toString();
        }
        
        return avail_vehs;
    }
    
    //Reservation related methods
    public static String[ ] makeReservation(ReservationDetails details) 
            throws VehicleNotFoundException, AccountNotFoundException, InvalidCreditCardException, ReservedVehicleException {
        
        String[] MROutput = new String[1];
        
        Vehicle v;
        Reservation r;
        Account a;
        
        v = agency_vehicles.getVehicle(details.getVIN());
        a = accounts.getAccount(details.getCreditCardNum());
        r = new Reservation(a.getCompanyName(), details.getCreditCardNum(), details.getRentalPeriod(), details.getInsuranceSelected());
        v.setReservation(r);
        
        if(v instanceof Car) {
            v.setQuotedRates(agency_rates.getCarRates());
        } else if(v instanceof SUV) {
            v.setQuotedRates(agency_rates.getSUVRates());
        } else if(v instanceof Truck) {
            v.setQuotedRates(agency_rates.getTruckRates());
        }
        
        MROutput[0] = "** Successfully reserved Vehicle " + details.getVIN() + " **";
        return MROutput;
    }
    
    public static String[ ] cancelReservation(String vin, String creditcard_num) 
            throws VehicleNotFoundException, UnreservedVehicleException {
        
        String[] CROutput = new String[1];
        
        Reservation r;
        
        r = agency_vehicles.getVehicle(vin).getReservation();
        if(r.getCreditCardNum().equals(creditcard_num)) {
            agency_vehicles.getVehicle(vin).cancelReservation();
        }
        
        CROutput[0] = "** Successfully canceled reservation on Vehicle " + vin + " **";
        return CROutput;
    }
    
    public static String[ ] getReservation(String vin) 
            throws VehicleNotFoundException, UnreservedVehicleException {
        
        String[] GROutput = new String[1];
        
        Reservation r;
        r = agency_vehicles.getVehicle(vin).getReservation();
        
        GROutput[0] = r.toString();
        return GROutput;
    }
    
    public static String[ ] getAllReservations() throws UnreservedVehicleException {
        // passing through the list to find the total size
        int total_reserved = 0;
        agency_vehicles.reset();
        while(agency_vehicles.hasNext()) {
            if(agency_vehicles.next().isReserved()) {
                total_reserved++;
            }
        }
        // creating the appropriate sized array
        String[] all_reservations = new String[total_reserved];
        
        Vehicle v;
        int i = 0;
        agency_vehicles.reset();
        // populating the list with all reservations
        while(agency_vehicles.hasNext()){
            v = agency_vehicles.next();
            if(v.isReserved()) {
                all_reservations[i++] = v.getReservation().toString() +" "+ v.toString();
            }
        }
        
        return all_reservations;
    }
    
    // Customer Accounts Related Methods
    public static String[ ] addAccount(String creditcard, String company_name, boolean prime_cust) throws InvalidCreditCardException { 
        
        String[] AAOutput = new String[1];
        
        Account a;
        
        a = new Account(creditcard, company_name, prime_cust);
        accounts.add(a);
            
        AAOutput[0] = "** Successfully added Account under " + company_name + " **";
        return AAOutput;
    }
    
    public static String[ ] getAccount(String creditcard_num) 
            throws InvalidCreditCardException, AccountNotFoundException { 
        
        String[] GAOutput = new String[1];
        
        Account a;
        
        a = accounts.getAccount(creditcard_num);
        
        GAOutput[0] = a.toString();
        return GAOutput;
    }
    
    public static String[ ] getAllAccounts() { 
        // passing through the list to find the total size
        int total_accounts = 0;
        accounts.reset();
        while(accounts.hasNext()) {
            accounts.getNext();
            total_accounts++;
        }
        // creating the appropriate sized array
        String[] all_accounts = new String[total_accounts];
        
        accounts.reset();
        int i = 0;
        // populating the list with all accounts
        while(accounts.hasNext()) {
            all_accounts[i++] = accounts.getNext().toString();
        }
        return all_accounts;
    }
    
    public static String[ ] getAllTransactions() {
        // passing through the list to find the total size
        int total_transactions = 0;
        transactions_history.reset();
        while(transactions_history.hasNext()) {
            transactions_history.getNext();
            total_transactions++;
        }
        // creating the appropriate sized array
        String[] all_transactions = new String[total_transactions];
        
        transactions_history.reset();
        int i = 0;
        // populating the list with all transactions
        while(transactions_history.hasNext()) {
            all_transactions[i++] = transactions_history.getNext().toString();
        }
        return all_transactions;
    }
    
    //A transaction is only added when a vehicle is processed
    //therfore it is only used by the SystemInterface.processReturnedVehicle
    private static void addTransactions(String creditcard_num, String company_name, int vehicle_type, 
            String vin, String rental_period, String miles_driven, String rental_cost) 
            throws VehicleNotFoundException {
        
        Vehicle v;
        Transaction t;
        String v_type = "";
        String vehicle;
        switch(vehicle_type) {
            case 1: v_type = "Car"; break;
            case 2: v_type = "SUV"; break;
            case 3: v_type = "Truck"; break;
        }
        
        v = agency_vehicles.getVehicle(vin);
        vehicle = v.getDescription();
            
         t = new Transaction(creditcard_num, company_name, v_type, vehicle, rental_period, miles_driven, rental_cost);
         transactions_history.add(t);
    }
    
}
