package ERP_classes;

import java.util.Date;

import invoice.InvoiceList;
import contacts.CustomerList;


public class BusinessLayer {
	
	DataLinkLayer _dal = new DataLinkLayer();
	CustomerList _contacts = null;
	InvoiceList _invoices = null;

	public CustomerList listAllContacts() {
		_contacts = new CustomerList();
		
		return _contacts = _dal.listAllContacts(null, false, false, 0, 0);		
	}
	
	public InvoiceList getInvoicesFromCustomer(String name,
			Date fromDate, 
			Date toDate,
			int fromAmount,
			int toAmount) {
		_invoices = new InvoiceList();
		
		return _invoices = _dal.getInvoicesFromCustomer(name, fromDate, toDate, fromAmount, toAmount);		
	}
}
