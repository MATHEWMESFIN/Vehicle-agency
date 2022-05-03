//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class ReservationDetails {
    
//    Contains the information for making a reservation (VIN, creditcard_num, rental period, daily insurance
//    option). Only used to pass this reservation information from the user interface to the system interface. Thus,
//    these classes contain only a constructor and getter methods, and are not stored anywhere in the system.
    
    private String vin;
    private String creditCardNum; // credit card number on file for company account
    private TimeSpan rentalPeriod; // e.g., TimeSpan type stores, e.g, 3, ‘D’ (3 days)
    private boolean insuranceSelected; // set to true if optional daily insurance wanted
    
    public ReservationDetails(String vin, String creditCardNum, TimeSpan rentalPeriod, boolean insuranceSelected) {
        this.vin = vin;
        this.creditCardNum = creditCardNum;
        this.rentalPeriod = rentalPeriod;
        this.insuranceSelected = insuranceSelected;
    }
    
    public String getVIN() {
        return vin;
    }
    
    public String getCreditCardNum() {
        return creditCardNum;
    }
    
    public TimeSpan getRentalPeriod() {
        return rentalPeriod;
    }
    
    public boolean getInsuranceSelected() {
        return insuranceSelected;
    }
}
