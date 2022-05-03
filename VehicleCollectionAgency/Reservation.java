//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class Reservation {
    
    private String companyName;
    private String creditCardNum; // credit card number on file for company account
    private TimeSpan rentalPeriod; // e.g., TimeSpan type stores, e.g, 3, ‘D’ (3 days)
    private boolean insuranceSelected; // set to true if optional daily insurance wanted
    
    public Reservation(String companyName, String creditCardNum, TimeSpan rentalPeriod, boolean insuranceSelected) {
        this.companyName = companyName;
        this.creditCardNum = creditCardNum;
        this.rentalPeriod = rentalPeriod;
        this.insuranceSelected = insuranceSelected;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public boolean getInsuranceSelected() {
        return insuranceSelected;
    }
    
    public String getCreditCardNum() {
        return creditCardNum;
    }
    
    public String toString() {
        String insurance = (insuranceSelected) ? "insured" : "not insured";
        return "Name: "+ companyName +" \t rental period: "+ rentalPeriod +" \t "+ insurance;
    }
}
