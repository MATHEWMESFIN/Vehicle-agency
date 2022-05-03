//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;
import java.util.ArrayList;
/**
 *
 * @author mathewmesfin
 */
public class Accounts {
    
    ArrayList<Account> accounts = new ArrayList<Account>();
    private int current = 0;
    
    public void add(Account acc) {
        accounts.add(acc);
    }
    
    public Account getAccount(String creditcard_num) throws AccountNotFoundException {
        //returns the account with the entered credit car number
        //throws InvalidCreditCardException if credit card entered not 16 digits (with no non-digit chars)
        //throws AccountNotFoundException if credit card number does not exist for any customer account
        
        Account acc = null; //to be initialized if all requirements are met
        
        //creditcard is checked for error when prompted in the UI
        
        for(int i = 0;i < accounts.size();i++) {
            if(accounts.get(i) == null) {
                throw new AccountNotFoundException();
            } else if(accounts.get(i).getCreditCardNum().equals(creditcard_num)) {
                acc = accounts.get(i);
            }
        }
        if(acc == null) {
            throw new AccountNotFoundException();
        }
        
        return acc;
    }
    
    // iterator methods
    public void reset() {
        current = 0;
    }
    
    public boolean hasNext() {
        return current != accounts.size();
    }

    public Account getNext() {
        current++;
        return accounts.get(current - 1);
    }
}