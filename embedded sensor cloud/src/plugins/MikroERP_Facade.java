package plugins;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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
			
			//_contacts to Xml
			//danach die xml zurück schicken
			
		}
		
		return null;
	}

}
