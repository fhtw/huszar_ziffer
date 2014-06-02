package unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ERP_classes.DataLinkLayer;

public class Test_getPriceFromArticle {
	
	DataLinkLayer dl;
	Method method;
	double price;

	@Before
	public void setUp() throws Exception {
	
		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getPriceFromArticle", Integer.class);
		method.setAccessible(true);
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		method = null;
	}

	@Test
	public void nothing_is_passed_should_return_minus_1() {
		int id = 1;
		try {
			price = (Double) method.invoke(dl, id);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals(100,price,0.001);
	}
}