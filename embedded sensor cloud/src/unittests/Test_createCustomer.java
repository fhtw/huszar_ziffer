package unittests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contacts.Customer;
import ERP_classes.DataLinkLayer;

public class Test_createCustomer {
	
	DataLinkLayer dl;
	Customer customer;
	Connection connect;
	ResultSet resultSet;
	PreparedStatement preparedStatement;

	@Before
	public void setUp() throws Exception {
		
		dl = new DataLinkLayer();
		customer = new Customer();
		Class.forName("com.mysql.jdbc.Driver");
		  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
			    		+ "user=root&password=!eps1loN");
		//preparedStatements can use variables and are more efficient
	}

	@After
	public void tearDown() throws Exception {
		try {
			preparedStatement = connect
				    .prepareStatement("DELETE from CUSTOMER where lastname=? AND surname=?");
			preparedStatement.setString(1, customer.get_lastname());
			preparedStatement.setString(2, customer.get_surname());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dl = null;
		customer = null;
	}

	@Test
	public void normal_insert_is_tested() {
				
		customer.set_lastname("lastTest");
		customer.set_surname("Test");
		customer.set_address("testaddy");
		customer.set_plz(8485);
		customer.set_city("testcity");
		dl.createCustomer(customer);
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT surname from CUSTOMER where lastname=? AND surname=?");
			preparedStatement.setString(1, customer.get_lastname());
			preparedStatement.setString(2, customer.get_surname());
			resultSet = preparedStatement.executeQuery();
			assertTrue(resultSet.next());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}