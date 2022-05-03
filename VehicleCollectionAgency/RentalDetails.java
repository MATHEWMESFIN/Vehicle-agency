//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class RentalDetails {
    
//    A RentalDetails class contains the information related to a given vehicle rental for the sake of computing the
//    rental charges - vehicle type, rental period, estimated number of miles to be driven, insurance option, and
//    whether a prime customer. There are no objects of type RentalDetails stored anywhere in the program (and
//    thus not part of the UML class diagram). These objects are only used for passing information between methods.
//    Thus, these classes contain only a constructor and getter methods, and are not stored anywhere in the system.
        
    private int vehicle_type;
    private TimeSpan rental_period;
    private int num_miles;
    private boolean insurance_option;
    private boolean prime_customer;
    
    public RentalDetails(int vehicle_type, TimeSpan rental_period, int num_miles, boolean insurance_option, boolean prime_customer) {
        this.vehicle_type = vehicle_type;
        this.rental_period = rental_period;
        this.num_miles = num_miles;
        this.insurance_option = insurance_option;
        this.prime_customer = prime_customer;
    }
    
    public int getVehicleType() {
        //1 - car
        //2 - SUV
        //3 - Truck
        return vehicle_type;
    }
    
    public TimeSpan getRentalPeriod() {
        return rental_period;
    }
    
    public int getNumMiles() {
        return num_miles;
    }
    
    public boolean getInsuranceOption() {
        return insurance_option;
    }
    
    public boolean getPrimeCustomer() {
        return prime_customer;
    }
}
