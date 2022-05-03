//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public abstract class Vehicle {
    
    private String description; // stores make-model-year for cars and SUVs, stores length for trucks
    private int mpg; // miles per gallon rating
    private String vin; // unique vehicle identification number
    private Reservation resv; // reservation information (a null value means vehicle not reserved)
    private VehicleRates rates;  //only assigned when vehicle is given
    
    public Vehicle(String description, int mpg, String vin) {
        this.description = description;
        this.mpg = mpg;
        this.vin = vin;
        resv = null;
        rates = null;
    }
    
    public String getDescription() { return description; }
    
    public int getMPG() { return mpg; }
    
    public String getVIN() { return vin; }
    
    public VehicleRates getQuotedRates() { return rates; }
    
    public void setQuotedRates(VehicleRates cost) { rates = cost; } //only assigned when vehicle is reserved
    
    public boolean isReserved() { return resv != null; } 
    
    public Reservation getReservation() throws UnreservedVehicleException {
        if(isReserved()) {
            return resv;
        } else 
            throw new UnreservedVehicleException();
    }
    
    public void setReservation(Reservation resv) throws ReservedVehicleException {
        //throws ReservedVehicleException (if vehicle reserved)
        if(isReserved()) {
            throw new ReservedVehicleException();
        } else {
            this.resv = resv;
        }
    } 
    
    public void cancelReservation() throws UnreservedVehicleException {
        //throws UnreservedVehicleException if reservation doesn’t exist
        if (isReserved()) {
            resv = null;
        } else {
            throw new UnreservedVehicleException();
        }
    }
            
    public abstract String toString(); // ABSTRACT METHOD – implemented in each subclass
    
}
