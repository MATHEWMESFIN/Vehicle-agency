//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class TruckRates extends VehicleRates{
    
    public TruckRates(double daily_rate, double weekly_rate, double monthly_rate, double mileage_chrg,
    double daily_insur_rate) {
        super(daily_rate, weekly_rate, monthly_rate, mileage_chrg, daily_insur_rate);
    }
    
    public String toString() {
        return "(Truck) Daily rate: " + getDailyRate() + ", Weekly rate: " + getWeeklyRate() +
                ", Monthly rate: " + getMonthlyRate() + ", Mileage charge: " + getMileageChrg() +
                ", Insurance rates: " + getDailyInsurRate();
    }
}
