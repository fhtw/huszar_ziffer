package unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ERP_classes.DataLinkLayer;

public class Test_getNameFromArticle {
	
	DataLinkLayer dl;
	Method method;
	String name;

	@Before
	public void setUp() throws Exception {

		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getNameFromArticle",Integer.class);
		method.setAccessible(true);
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		method = null;
	}

	@Test
	public void _1_is_passed_should_return_Hose() {
		int id = 1;
		try {
			name = (String) method.invoke(dl, id);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals("Hose",name);
	}
	@Test
	public void _0_is_passed_should_return_null() {
		int id = 0;
		try {
			name = (String) method.invoke(dl, id);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals(null,name);
	}
	@Test
	public void minus_1_is_passed_should_return_null() {
		int id = 0;
		try {
			name = (String) method.invoke(dl, id);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals(null,name);
	}
}