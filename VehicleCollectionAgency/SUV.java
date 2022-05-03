//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class SUV extends Vehicle {
    
    public int seating_capacity;
    public int cargo_capacity;
    
    public SUV(String description, int mpg, String vin, int seating_capacity, int cargo_capacity) {
    
        super(description, mpg, vin);
        this.seating_capacity = seating_capacity;
        this.cargo_capacity = cargo_capacity;
    }
    
    public int getSeatingCapacity() {
        return seating_capacity;
    }
    
    public int getCargoCapacity() {
        return cargo_capacity;
    }
    
    public String toString() {
        return "(SUV) "+ super.getDescription() +"\t MPG: "+ super.getMPG() +"\t Seating: "+ seating_capacity +"\t Storage: "+ cargo_capacity +" cubic ft.\t VIN: "+ super.getVIN();
    }
    
}
