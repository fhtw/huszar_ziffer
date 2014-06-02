package unittests;

import static org.junit.Assert.*;
import invoice.InvoiceElement;

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

public class Test_getArticlesFromResultSet {

	DataLinkLayer dl;
	Method method;
	ResultSet resultSet;
	Connection connect;
	PreparedStatement preparedStatement;
	ArrayList<InvoiceElement> articles;
	
	@Before
	public void setUp() throws Exception {
		articles = new ArrayList<InvoiceElement>();
		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getArticlesFromResultSet", ResultSet.class);
		method.setAccessible(true);
			  // this will load the MySQL driver, each DB has its own driver
			  Class.forName("com.mysql.jdbc.Driver");
			  connect = DriverManager.getConnection("jdbc:mysql://localhost/mikroerp?"
				    		+ "user=root&password=!eps1loN");
	}

	@After
	public void tearDown() throws Exception {
		articles = null;
		resultSet = null;
		connect = null;
		preparedStatement = null;
		dl = null;
		method = null;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void check_if_name_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from ARTICLES where id=1");
			resultSet = preparedStatement.executeQuery();
			articles = (ArrayList<InvoiceElement>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals("Hose",articles.get(0).get_name());
	}
	@SuppressWarnings("unchecked")
	@Test
	public void check_if_price_is_set_right() {
		try {
			preparedStatement = connect
				    .prepareStatement("SELECT * from ARTICLES where id=1");
			resultSet = preparedStatement.executeQuery();
			articles = (ArrayList<InvoiceElement>) method.invoke(dl, resultSet);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(100,articles.get(0).get_price(),0.001);
	}
}