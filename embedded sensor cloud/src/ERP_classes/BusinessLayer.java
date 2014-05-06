package ERP_classes;

import java.sql.Date;

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
	
	public InvoiceList searchInvoices(String name,
			Date fromDate, 
			Date toDate,
			double fromAmount,
			double toAmount) {
		_invoices = new InvoiceList();
		
		return _invoices = _dal.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);		
	}
}
