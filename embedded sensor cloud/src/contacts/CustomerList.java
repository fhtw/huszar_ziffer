package contacts;

import java.util.ArrayList;

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
}
