//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class Transaction {
    
    private String creditcard_num;
    private String company_name;
    private String vehicle_type; // car, SUV or Truck
    private String vehicle; // vehicle description
    private String rental_period; // days, week, months
    private String miles_driven;
    private String rental_cost;
    
    public Transaction(String creditcard_num, String company_name, String vehicle_type, String vehicle, 
            String rental_period, String miles_driven, String rental_cost) {
        this.creditcard_num = creditcard_num;
        this.company_name = company_name;
        this.vehicle_type = vehicle_type;
        this.vehicle = vehicle;
        this.rental_period = rental_period;
        this.miles_driven = miles_driven;
        this.rental_cost = rental_cost;
    }
    
    public String toString() {
        // Example: ABC Company (card #3212546453245879), Car: Ford Fusion, 3 days, 540 mi., $120.54
        return company_name + " (card #" + creditcard_num + "), " + vehicle_type + ": " + vehicle
                + ", " + rental_period + " days, " + miles_driven +" mi., " + "$" + rental_cost;
    }
}
