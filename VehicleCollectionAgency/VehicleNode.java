//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class VehicleNode {
    
    private Vehicle veh;
    private VehicleNode next;
    
    public VehicleNode(Vehicle veh, VehicleNode next) {
        this.veh = veh;
        this.next = next;
    }
    
    public Vehicle getVehicle() {
        return veh;
    }
    
    public VehicleNode getNext() {
        return next;
    }
    
    public void setNext(VehicleNode next) {
        this.next = next;
    }
}
