package contacts;

import java.util.ArrayList;
import java.util.Iterator;

public class CustomerList {

	private ArrayList<Customer> _customerList;

    public CustomerList(){
    	_customerList = new ArrayList<Customer>();
    }

    public void add(Customer c){
    	_customerList.add(c);
    }
    
    public ArrayList<Customer> getCustomers() {
    	return _customerList;
    }

	public CustomerList addAll(CustomerList listToJoin) {
		
		Iterator<Customer> iterator = listToJoin.getCustomers().iterator();
		
		while(iterator.hasNext()){
			this.add((Customer) iterator.next());
		}
		
		return this;
	}
}
