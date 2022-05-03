//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class CurrentRates {
    
    private VehicleRates[] rates = new VehicleRates[3]; //stores three types of vehicle rates
    
    public CurrentRates() {
        
    }
    
    public CurrentRates(CarRates cR, SUVRates sR, TruckRates tR) {
        //The rates go as follows: daily, weekly, monthly, mileage charge, daily insurance
        cR = new CarRates(24.95, 169.95, 514.95, 0.16, 14.95);
        sR = new SUVRates(29.95, 189.95, 679.95, 0.16, 14.95);
        tR = new TruckRates(36.95, 224.95, 687.95, 0.26, 21.95);
        rates[0] = cR;
        rates[1] = sR;
        rates[2] = tR;
    }
    
    public VehicleRates getCarRates() { 
        return rates[0]; 
    }
    
    public VehicleRates getSUVRates() { 
        return rates[1]; 
    }
    
    public VehicleRates getTruckRates() { 
        return rates[2]; 
    }
    
    public void setCarRates(VehicleRates r) { 
        rates[0] = r; 
    }
    
    public void setSUVRates(VehicleRates r) { 
        rates[1] = r; 
    }
    
    public void setTruckRates(VehicleRates r) { 
        rates[2] = r; 
    }
    
    public double calcEstimatedCost(int vehicleType,TimeSpan estimatedRentalPeriod, int estimatedNumMiles,
    boolean dailyInsur, boolean primeCustomer) {
        
        // ***make sure the user enterd 1, 2, or 3 for the vehicle type***
        double estimatedCost = 0;
        
        if(estimatedRentalPeriod.getTimeUnit() == 'D') {
            estimatedCost += rates[vehicleType - 1].getDailyRate() * estimatedRentalPeriod.getNumUnits();
            if(dailyInsur) {
                estimatedCost += rates[vehicleType - 1].getDailyInsurRate() * estimatedRentalPeriod.getNumUnits();
            }
                
        } else if(estimatedRentalPeriod.getTimeUnit() == 'W') {
            estimatedCost += rates[vehicleType - 1].getWeeklyRate() * estimatedRentalPeriod.getNumUnits();
            if(dailyInsur) {
                estimatedCost += rates[vehicleType - 1].getDailyInsurRate() * (estimatedRentalPeriod.getNumUnits() * 7);
            }
                
        } else if(estimatedRentalPeriod.getTimeUnit() == 'M') {
            estimatedCost += rates[vehicleType - 1].getMonthlyRate() * estimatedRentalPeriod.getNumUnits();
            if(dailyInsur) {
                estimatedCost += rates[vehicleType - 1].getDailyInsurRate() * (estimatedRentalPeriod.getNumUnits() * 30);
            }
        }
        
        if(primeCustomer) {
            //free 100 miles if prime customer
            if(estimatedNumMiles < 100) {
                estimatedNumMiles = 0;
            } else {
                estimatedNumMiles -= 100;
            }
        }
        estimatedCost += rates[vehicleType - 1].getMileageChrg() * estimatedNumMiles;
                
        
        return estimatedCost;
        
    }
    
    public double calcActualCost(VehicleRates rates, int numDaysUsed, int numMilesDriven,
    boolean dailyInsur, boolean primeCustomer) {
        
        double actualCost = 0;
        
        if(numDaysUsed < 7) {
            actualCost += rates.getDailyRate() * numDaysUsed;
            
        } else if((numDaysUsed >= 7) && (numDaysUsed <= 30)) {
            double numWeeksUsed = numDaysUsed/7;
            actualCost += rates.getWeeklyRate() * numWeeksUsed;
            
        } else if(numDaysUsed > 30) {
            double numMonthsUsed = numDaysUsed/30;
            actualCost += rates.getMonthlyRate() * numMonthsUsed;
        }
        
        if(primeCustomer) {
            //free 100 miles if prime customer
            if(numMilesDriven < 100) {
                numMilesDriven = 0;
            } else {
                numMilesDriven -= 100;
            }
        }
        actualCost += rates.getMileageChrg() * numMilesDriven;
        
        if (dailyInsur) {
            actualCost += rates.getDailyInsurRate() * numDaysUsed;
        }
        
        return actualCost;
    }
}
