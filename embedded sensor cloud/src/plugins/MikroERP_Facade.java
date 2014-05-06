package plugins;


import invoice.Invoice;
import invoice.InvoiceList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import server.QueryObject;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import contacts.Customer;
import contacts.CustomerList;
import ERP_classes.BusinessLayer;
import server.Plugin;

public class MikroERP_Facade implements Plugin {
	
	BusinessLayer _bl = new BusinessLayer();
	CustomerList _customerList = null;
	InvoiceList _invoiceList = null;

	@Override
	public String execPlugin(String[] param, List<QueryObject> query) {
		if(param.length > 1){			
			if("searchContacts".equals(param[1])){
			
				_customerList = new CustomerList();
				_customerList = _bl.searchContacts(getValueFromKey(query,"surname"),
									getValueFromKey(query,"lastname"),
										getValueFromKey(query,"businessname"));
				
				XStream xs = new XStream(new StaxDriver());
				xs.alias("Customer", Customer.class);
				xs.alias("CustomerList", CustomerList.class);
				xs.addImplicitCollection(CustomerList.class, "_customerList");
			    
				// OBJECT --> XML
				String xml = xs.toXML(_customerList);
				return xml;
			} else if("searchInvoices".equals(param[1])){//if no query isset all invoices in database return
					_invoiceList = new InvoiceList();
					
					_invoiceList = _bl.searchInvoices(getValueFromKey(query,"name"), getDateFromKey(query,"fromDate"), getDateFromKey(query,"toDate"),
														getDoubleFromKey(query,"fromAmount"),
															getDoubleFromKey(query,"toAmount"));
					
					XStream xs = new XStream(new StaxDriver());
					xs.alias("Invoice", Invoice.class);
					xs.alias("InvoiceList", InvoiceList.class);
					xs.addImplicitCollection(InvoiceList.class, "_invoiceList");
				    
					// OBJECT --> XML
					String xml = xs.toXML(_invoiceList);
					return xml;
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
		return null;
	}
		

	private String getValueFromKey(List<QueryObject> query,String key){
		try{
		for(QueryObject q : query) {
            if(key.equals(q.get_key())){
            	return q.get_value();//gibt nur den erste value zur�ck den er findet, da wir vorraussetzen dass es keine doppelten namen gibt
            }
        }
		}catch(NullPointerException e){
			return null;
		}
		return null;
	}
	
	private double getDoubleFromKey(List<QueryObject> query,String key){
		try{
		for(QueryObject q : query) {
            if(key.equals(q.get_key())){
            	return Double.parseDouble(q.get_value());//gibt nur den erste value zur�ck den er findet, da wir vorraussetzen dass es keine doppelten namen gibt
            }
        }
		}catch(NullPointerException e){
			return -1;
		}
		return -1;
	}
	
	private java.sql.Date getDateFromKey(List<QueryObject> query,String key){
		try{
		for(QueryObject q : query) {
            if(key.equals(q.get_key())){
            	SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
    			java.util.Date utilDate = simpleDate.parse(q.get_value());
    			java.sql.Date date = new java.sql.Date(utilDate.getTime());
            	return date;
            }
        }
		}catch(NullPointerException e){
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
