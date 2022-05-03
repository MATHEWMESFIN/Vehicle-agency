//Mathew Mesfin
//COSC 237.003
//Spring 2022
package VehicleCollectionAgency;

import java.util.ArrayList;
/**
 *
 * @author mathewmesfin
 */
public class Transactions {
    
    private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
    private int current = 0;
    
    public void add(Transaction tran) {
        transactions.add(tran);
    }
    
    //iterator methods
    public void reset() {
        current = 0;
    }
    
    public boolean hasNext() {
        return current != transactions.size();
    }

    public Transaction getNext() {
        current++;
        return transactions.get(current - 1);
    }
}
