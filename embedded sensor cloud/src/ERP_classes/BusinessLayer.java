package ERP_classes;

import java.sql.Date;
import java.util.ArrayList;

import invoice.Invoice;
import invoice.InvoiceElement;
import invoice.InvoiceList;
import contacts.Customer;
import contacts.CustomerList;


public class BusinessLayer {
	
	DataLinkLayer _dal = new DataLinkLayer();
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
		
		return _dal.createInvoice(invoice);
		
	}
	
	public String createCustomer(Customer customer) {
		
		return _dal.createCustomer(customer);
		
	}

	public ArrayList<InvoiceElement> getArticles() {
		
		return _dal.getArticles();
	}
}
