package unittests;

import static org.junit.Assert.*;
import invoice.Invoice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ERP_classes.DataLinkLayer;

public class Test_getInvoicesFromResultSet {

	DataLinkLayer dl;
	Method method;
	ResultSet resultSet;
	Connection connect;
	PreparedStatement preparedStatement;
	ArrayList<Invoice> invoiceList;
	
	@Before
	public void setUp() throws Exception {
		invoiceList = new ArrayList<Invoice>();
		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getInvoicesFromResultSet", ResultSet.class);
		method.setAccessible(true);
			  // this will load the MySQL driver, each DB has its own driver
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
	}

	@After
	public void tearDown() throws Exception {
		invoiceList = null;
		resultSet = null;
		connect = null;
		preparedStatement = null;
		dl = null;
		method = null;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void check_if_message_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("message!",invoiceList.get(0).get_message());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_comment_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("comment!",invoiceList.get(0).get_comment());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_invoiceNumber_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(3,invoiceList.get(0).get_invoiceNumber());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_isOutgoing_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertFalse(invoiceList.get(0).is_isOutgoing());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_creationDate_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("2014-05-05",invoiceList.get(0).get_creationDate());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_expirationDate_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("2014-05-05",invoiceList.get(0).get_expirationDate());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_customerName_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Stefan Ziffer",invoiceList.get(0).get_customerName());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_shippingAddress_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("testaddress",invoiceList.get(0).get_shippingAddress());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_invoiceAddress_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("testaddress",invoiceList.get(0).get_invoiceAddress());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_net_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1000,invoiceList.get(0).get_net(),0.001);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_ust_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(200,invoiceList.get(0).get_ust(),0.001);
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_gross_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from INVOICES where id=6");
			resultSet = preparedStatement.executeQuery();
			invoiceList = (ArrayList<Invoice>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1200,invoiceList.get(0).get_gross(),0.001);
	}
}