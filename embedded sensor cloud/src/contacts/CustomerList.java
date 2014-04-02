package contacts;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {

	private List<Customer> _customerList;

    public CustomerList(){
    	_customerList = new ArrayList<Customer>();
    }

    public void add(Customer c){
    	_customerList.add(c);
    }
}
