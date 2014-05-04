package plugins;


import invoice.Invoice;
import invoice.InvoiceList;

import java.util.List;

import server.QueryObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import contacts.Customer;
import contacts.CustomerList;
import ERP_classes.BusinessLayer;
import Exceptions.NameNotSetException;
import server.Plugin;

public class MikroERP_Facade implements Plugin {
	
	BusinessLayer _bl = new BusinessLayer();
	CustomerList _customerList = null;
	InvoiceList _invoiceList = null;

	@Override
	public String execPlugin(String[] param, List<QueryObject> query) {
		if(param.length > 1){			
			if("listAllContacts".equals(param[1])){
			
				_customerList = new CustomerList();
				_customerList = _bl.listAllContacts();
				
				XStream xs = new XStream(new StaxDriver());
				xs.alias("Customer", Customer.class);
				xs.alias("CustomerList", CustomerList.class);
				xs.addImplicitCollection(CustomerList.class, "_customerList");
			    
				// OBJECT --> XML
				String xml = xs.toXML(_customerList);
				return xml;
			} else if("getInvoicesFromCustomer".equals(param[1])){
				try{
					_invoiceList = new InvoiceList();
					_invoiceList = _bl.getInvoicesFromCustomer(getValueFromKey(query,"name"), null, null, 0, 0);
					
					XStream xs = new XStream(new StaxDriver());
					xs.alias("Invoice", Invoice.class);
					xs.alias("InvoiceList", InvoiceList.class);
					xs.addImplicitCollection(InvoiceList.class, "_invoiceList");
				    
					// OBJECT --> XML
					String xml = xs.toXML(_invoiceList);
					return xml;
				}catch(NameNotSetException e){
					System.out.println("Facade: \"getInvoicesFromCustomer\" called but no parameter \"name = value\" set!");
					return null;
				}
			}
		} else {		
			String returned = null;
			if(query == null){
				System.out.println("Liste ist null");
			}
			if(query != null){
				for(QueryObject q : query) {
		            System.out.println("Key = " + q.get_key() + " Value = " + q.get_value());
		            returned += "Key = " + q.get_key() + " Value = " + q.get_value() + "\n";
		        }
			}
		return returned;
		}
	}
		

	private String getValueFromKey(List<QueryObject> query,String key) throws NameNotSetException {
		try{
		for(QueryObject q : query) {
            if(key.equals(q.get_key())){
            	return q.get_value();
            }
        }
		}catch(NullPointerException e){
			throw new NameNotSetException();
		}
		throw new NameNotSetException();
	}
}
