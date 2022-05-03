//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class Truck extends Vehicle {
    
    int load_capacity;
    
    public Truck(String description, int mpg, String vin, int load_capacity) {
        super(description, mpg, vin);
        this.load_capacity = load_capacity;
    }
    
    public int getLoadCapacity() {
        return load_capacity;
    }
    
    public String toString() {
        return "(Truck) "+ super.getDescription() +"\t MPG: "+ super.getMPG() +"\t Load Capacity: "+ load_capacity +" lbs.\t VIN: "+ super.getVIN();
    }
}
