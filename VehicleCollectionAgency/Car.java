//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class Car extends Vehicle {
    
    public int seating_capacity;
    
    public Car(String description, int mpg, String vin, int seating_capacity) {
        
        super(description, mpg, vin);
        this.seating_capacity = seating_capacity;
    }
    
    public int getseatingCapacity() {
        return seating_capacity;
    }
    
    public String toString() {
        return "(Car) "+ super.getDescription() +"\t MPG: "+ super.getMPG() +"\t Seating: "+ seating_capacity +"\t VIN: "+ super.getVIN();
    }
}
