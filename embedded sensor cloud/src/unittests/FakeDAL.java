package unittests;

import invoice.Invoice;
import invoice.InvoiceElement;
import invoice.InvoiceList;

import java.sql.Date;
import java.util.ArrayList;

import contacts.Customer;
import contacts.CustomerList;

public class FakeDAL {

	public CustomerList searchContacts(String surname, 
		    String lastname, 
		    String businessname){
		
		CustomerList customerList = new CustomerList();
		
		for(int i=0; i<5; i++){
			Customer customer = new Customer();
			if(i % 2 ==0){
				customer.set_name("Name" + i);
				customer.set_uid(Integer.toString(i));
			} else{
				customer.set_surname("Surname" + i);
				customer.set_lastname("Lastname" + i);
			}
			customer.set_id(i);
			customer.set_address("Street Number " + i);
			customer.set_plz(1010);
			customer.set_city("City " + i);
			
			customerList.add(customer);
		}
		return customerList;
	}
	
	public InvoiceList searchInvoices(String name,
			Date fromDate, 
			Date toDate,
			double fromAmount,
			double toAmount) {
		
		InvoiceList invoiceList = new InvoiceList();
		InvoiceElement elem = new InvoiceElement();
		ArrayList<InvoiceElement> articles = new ArrayList<InvoiceElement>();
		
		elem.set_amount(2);
		elem.set_name("Hose");
		elem.set_price(100);
		articles.add(elem);
		
		for(int i=0; i<5; i++){
			Invoice invoice = new Invoice();
			
			invoice.set_id(i+1);
			invoice.set_invoiceNumber(i);
			invoice.set_creationDate("2014-05-05");
			invoice.set_isOutgoing(false);
			invoice.set_customerName("Customer" + i);
			invoice.set_articles(articles);
			invoice.set_net(160);
			invoice.set_ust(40);
			invoice.set_gross(200);
			
			invoiceList.add(invoice);
		}
		return invoiceList;			
	}
	
	public ArrayList<InvoiceElement> getArticles() {
		ArrayList<InvoiceElement> articles = new ArrayList<InvoiceElement>();
		InvoiceElement elem = new InvoiceElement();
		
		elem.set_name("Hose");
		elem.set_price(100);
		articles.add(elem);
		elem = new InvoiceElement();
		
		elem.set_name("Hemd");
		elem.set_price(80.55);
		articles.add(elem);
		elem = new InvoiceElement();
		
		elem.set_name("Kappe");
		elem.set_price(99.99);
		articles.add(elem);
		
		return articles;
	}
}
