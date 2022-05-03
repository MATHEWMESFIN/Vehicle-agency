//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class Vehicles {
    
    private VehicleNode head;
    private VehicleNode current;
    
    public Vehicles() {
        head = null;
        current = null;
    }
    
    public Vehicle getVehicle(String vin) throws VehicleNotFoundException {
        //checks and returns a vehicle with the matching vin
        VehicleNode tempPtr = head;
        while(tempPtr != null) { 
            if(tempPtr.getVehicle().getVIN().equals(vin)) {
                return tempPtr.getVehicle();
            }
            tempPtr = tempPtr.getNext();
        }
        throw new VehicleNotFoundException();
    }
    
    public void add(Vehicle veh) {
        
        VehicleNode tempPtr = head;
        if(head != null) {
            //loops to the end of the linked list
            while(tempPtr.getNext() != null) {
                tempPtr = tempPtr.getNext();
            }
            tempPtr.setNext(new VehicleNode(veh, null));  //adds the new vehicle
        }
        if(head == null) {
            head = new VehicleNode(veh, null);
        }
    }
    
    //iterator methods
    public boolean hasNext() {
        return current != null;
    }
    
    public Vehicle next() {
        Vehicle temp = current.getVehicle();
        current = current.getNext();
        return temp;
    }
    
    public void reset() {
        current = head;
    }
}
