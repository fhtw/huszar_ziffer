package plugins;

import java.net.Socket;

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
	public String execPlugin(String param, Socket socket) {
		
		if("SearchContacts".equals(param)){
			
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
		return null;
	}
}
