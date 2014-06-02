package unittests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import contacts.CustomerList;
import ERP_classes.DataLinkLayer;

public class Test_getContactsFromResultSet {

	DataLinkLayer dl;
	Method method;
	ResultSet resultSet;
	Connection connect;
	PreparedStatement preparedStatement;
	CustomerList customerList;
	
	@Before
	public void setUp() throws Exception {
		customerList = new CustomerList();
		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getContactsFromResultSet", ResultSet.class);
		method.setAccessible(true);
			  // this will load the MySQL driver, each DB has its own driver
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
	}

	@After
	public void tearDown() throws Exception {
		customerList = null;
		resultSet = null;
		connect = null;
		preparedStatement = null;
		dl = null;
		method = null;
	}

	@Test
	public void check_if_PLZ_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=4");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1010,customerList.getCustomers().get(0).get_plz());
	}

	@Test
	public void check_if_uid_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=4");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("424242",customerList.getCustomers().get(0).get_uid());
	}
	
	@Test
	public void check_if_name_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=4");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Erste Bank",customerList.getCustomers().get(0).get_name());
	}
	
	@Test
	public void check_if_address_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=4");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Stephansplatz 42",customerList.getCustomers().get(0).get_address());
	}
	@Test
	public void check_if_city_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=4");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Wien",customerList.getCustomers().get(0).get_city());
	}
	@Test
	public void check_if_title_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=2");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("DDr.",customerList.getCustomers().get(0).get_title());
	}
	@Test
	public void check_if_surname_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=2");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Stefan",customerList.getCustomers().get(0).get_surname());
	}
	@Test
	public void check_if_lastname_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=2");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Ziffer",customerList.getCustomers().get(0).get_lastname());
	}
	@Test
	public void check_if_suffix_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=2");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Herr",customerList.getCustomers().get(0).get_suffix());
	}
	@Test
	public void check_if_dateOfBirth_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=2");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("1992-03-20",customerList.getCustomers().get(0).get_dateOfBirth());
	}
	@Test
	public void check_if_employedAt_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from CUSTOMER where id=2");
			resultSet = preparedStatement.executeQuery();
			customerList = (CustomerList) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("LooserAG",customerList.getCustomers().get(0).get_employedAt());
	}
}
