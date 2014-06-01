package ERP_classes;

import java.sql.Date;
import java.util.ArrayList;

import unittests.FakeDAL;
import invoice.CalculatedValues;
import invoice.Invoice;
import invoice.InvoiceElement;
import invoice.InvoiceList;
import contacts.Customer;
import contacts.CustomerList;


public class BusinessLayer {
	
	DataLinkLayer _dal = new DataLinkLayer();
	FakeDAL _fdal = new FakeDAL();
	CustomerList _contacts = null;
	InvoiceList _invoices = null;

	public CustomerList searchContacts(String surname,String lastname, String businessname) {
		_contacts = new CustomerList();
		
		return _contacts = _dal.searchContacts(surname,lastname,businessname);		
	}
	
	public InvoiceList searchInvoices(String name,
			Date fromDate, 
			Date toDate,
			double fromAmount,
			double toAmount) {
		_invoices = new InvoiceList();
		
		return _invoices = _dal.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);		
	}

	public String createInvoice(Invoice invoice) {
		/* calculate net, ust and total for new invoice */
		double net = 0;
		double ust = 0;
		double total = 0;
		
		for(int i=0; i<invoice.get_articles().size(); i++){
			double price = invoice.get_articles().get(i).get_price();
			int amount = invoice.get_articles().get(i).get_amount();
			net += (price * amount) / 120 * 100;
			ust += (price * amount) / 120 * 20;
			total += price * amount;
		}
		
		invoice.set_net(net);
		invoice.set_ust(ust);
		invoice.set_gross(total);
		
		return _dal.createInvoice(invoice);		
	}
	
	public String createCustomer(Customer customer) {
		
		return _dal.createCustomer(customer);
		
	}

	public ArrayList<InvoiceElement> getArticles() {
		
		return _dal.getArticles();
	}
	
	public CalculatedValues calculateValue(ArrayList<Double> prices) {
		CalculatedValues values = new CalculatedValues();
		double net = 0;
		double ust = 0;
		double total = 0;
		/* calculate net, ust and total for new invoice */		
		for(int i=0; i<prices.size(); i++){
			double price = prices.get(i);
			
			net += price / 120 * 100;
			ust += price / 120 * 20;
			total += price;
			
		}
		
		values.set_net(net);
		values.set_ust(ust);
		values.set_gross(total);	
		System.out.println(values.get_net());
		System.out.println(values.get_ust());
		System.out.println(values.get_gross());
		return values;
	}
}
