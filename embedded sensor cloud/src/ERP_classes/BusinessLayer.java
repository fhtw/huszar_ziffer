package ERP_classes;

import java.util.ArrayList;
import java.util.List;

import contacts.Contact;


public class BusinessLayer {
	
	DataLinkLayer _dal = new DataLinkLayer();
	List<Contact> _contacts = null;

	public List<Contact> searchContacts() {
		_contacts = new ArrayList<Contact>();
		
		return _contacts = _dal.searchContacts(null, false, false, 0, 0);
		
	}

}
