//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

/**
 *
 * @author mathewmesfin
 */
public class Account {
    
    private String creditcard_num;
    private String company_name;
    private boolean prime_customer;
    
    public Account(String creditcard_num, String company_name, boolean prime_customer) {
        
        this.creditcard_num = creditcard_num; 
        //creditcard is checked for error when prompted in the UI
        this.company_name = company_name;
        this.prime_customer = prime_customer;
    }
    
    public String getCreditCardNum() {
        return creditcard_num;
    }
    
    public String getCompanyName() {
        return company_name;
    }
    
    public boolean primeCustomer() {
        return prime_customer;
    }
    
    public String toString() {
        String prime = (prime_customer)? "prime customer" : "";
        return company_name + " (card #" + creditcard_num + ") " + prime;
    }
    
}
