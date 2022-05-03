//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class TimeSpan {
    
    private char timeUnit;
    private int numUnits;
    
    public TimeSpan(int numUnits,char timeUnit) {
        this.numUnits = numUnits;
        this.timeUnit = timeUnit;
    }
    
    public int getNumUnits() {
        return numUnits;
    }
    
    public char getTimeUnit() {
        return timeUnit;
    }
    public String toString() {
        boolean day = (timeUnit == 'D');
        boolean week = (timeUnit == 'W');
        boolean month = (timeUnit == 'M');
        String unit = (day)? " days" : (week)? " weeks" : (month)? " months" : "";
        
        return numUnits +" "+ unit;
    }
}
