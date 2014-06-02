package unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contacts.CustomerList;
import ERP_classes.DataLinkLayer;

public class Test_searchContacts {
	
	DataLinkLayer dl;
	CustomerList customerList;
	String surname,lastname,businessname;

	@Before
	public void setUp() throws Exception {
		
		dl = new DataLinkLayer();
		customerList = new CustomerList();
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		customerList = null;
		surname = null;
		lastname = null;
		businessname = null;
	}

	@Test
	public void existing_Name_passed_should_return_right_Customer() {
		
		surname = "Stefan";
		lastname = "Ziffer";
		businessname = null;
		
		customerList = dl.searchContacts(surname, lastname, businessname);
		
		assertEquals("Ziffer",customerList.getCustomers().get(0).get_lastname());
		assertEquals("Wien",customerList.getCustomers().get(0).get_city());
	}
	@Test
	public void existing_Businessname_passed_should_return_right_Customer() {
		
		surname = null;
		lastname = null;
		businessname = "Erste Bank";
		
		customerList = dl.searchContacts(surname, lastname, businessname);
		
		assertEquals("Erste Bank",customerList.getCustomers().get(0).get_name());
		assertEquals("424242",customerList.getCustomers().get(0).get_uid());
	}
	
	@Test
	public void trash_Names_passed_should_return_empty_List() {
		
		surname = "asdasds";
		lastname = "asdasd";
		businessname = null;
		
		customerList = dl.searchContacts(surname, lastname, businessname);
		
		assertTrue(customerList.getCustomers().size() == 0);
		
	}
	@Test
	public void _3_existing_Names_passed_should_return_existing_businessContact() {
		
		surname = "Stefan";
		lastname = "Ziffer";
		businessname = "Erste Bank";
		
		customerList = dl.searchContacts(surname, lastname, businessname);
		
		System.out.println("customerList(0) = "+customerList.getCustomers().get(0).get_id());
		System.out.println("customerList(1) = "+customerList.getCustomers().get(1).get_id());
		assertTrue(customerList.getCustomers().size() == 2);
	}
	@Test
	public void nothing_passed_should_return_all_existing_Contacts() {
		
		surname = "";
		lastname = "";
		businessname = "";
		
		customerList = dl.searchContacts(surname, lastname, businessname);
		
		assertTrue(customerList.getCustomers().size() > 5);
	}

}
