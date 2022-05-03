//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public abstract class VehicleRates {
    
    private double dailyRate;
    private double weeklyRate;
    private double monthlyRate;
    private double mileageChrge;
    private double dailyInsurance;
    
    public VehicleRates(double daily_rate, double weekly_rate, double monthly_rate, double mileage_chrg, 
            double daily_insur_rate) {
        dailyRate = daily_rate;
        weeklyRate = weekly_rate;
        monthlyRate = monthly_rate;
        mileageChrge = mileage_chrg;
        dailyInsurance = daily_insur_rate;
    }
    
    public double getDailyRate() { // cost per day
        return dailyRate;
    }
    
    public double getWeeklyRate() { // cost per week
        return weeklyRate;
    }
    
    public double getMonthlyRate() { // cost per month
        return monthlyRate;
    }
    
    public double getMileageChrg() { // cost per mile, based on vehicle type
        return mileageChrge;
    }
    
    public double getDailyInsurRate() { // insurance cost (per day)
        return dailyInsurance;
    }
    
    public abstract String toString(); // forces subclasses to provde an appropriate toString method
}
