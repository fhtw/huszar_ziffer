package plugins;


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

	@Override
	public String execPlugin(String[] param, List<QueryObject> query) {
		
		if("listAllContacts".equals(param)){
			
			_customerList = new CustomerList();
			_customerList = _bl.listAllContacts();
			
			XStream xs = new XStream(new StaxDriver());
			xs.alias("Customer", Customer.class);
			xs.alias("CustomerList", CustomerList.class);
			xs.addImplicitCollection(CustomerList.class, "_customerList");
		    
			// OBJECT --> XML
			String xml = xs.toXML(_customerList);
			return xml;
		}
		String returned = null;
		for(QueryObject q : query) {
            System.out.println("Key = " + q.get_key() + " Value = " + q.get_value());
            returned += "Key = " + q.get_key() + " Value = " + q.get_value();
        }
		
		return returned;
	}
}
