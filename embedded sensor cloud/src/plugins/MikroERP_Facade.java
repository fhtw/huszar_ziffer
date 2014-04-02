package plugins;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;

import contacts.Contact;
import ERP_classes.BusinessLayer;
import server.Plugin;

public class MikroERP_Facade implements Plugin {
	
	BusinessLayer _bl = new BusinessLayer();
	List<Contact> _contacts = null;

	@Override
	public String execPlugin(String param, Socket socket) {
		
		if("SearchContacts".equals(param)){
			
			_contacts = new ArrayList<Contact>();
			_contacts = _bl.searchContacts();
			
			XStream xs = new XStream();
			 
			// OBJECT --> XML
			String xml = xs.toXML(_contacts);
			return xml;
		}
		
		return null;
	}

}
