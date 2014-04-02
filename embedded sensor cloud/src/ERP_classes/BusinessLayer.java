package ERP_classes;

import contacts.CustomerList;


public class BusinessLayer {
	
	DataLinkLayer _dal = new DataLinkLayer();
	CustomerList _contacts = null;

	public CustomerList listAllContacts() {
		_contacts = new CustomerList();
		
		return _contacts = _dal.listAllContacts(null, false, false, 0, 0);		
	}
}
