package unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ERP_classes.DataLinkLayer;

public class Test_getIdFromArticle {
	
	DataLinkLayer dl;
	Method method;
	int id;

	@Before
	public void setUp() throws Exception {
	
		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getIdFromArticle", String.class);
		method.setAccessible(true);
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		method = null;
	}

	@Test
	public void nothing_is_passed_should_return_minus_1() {
		String string = "";
		try {
			id = (Integer) method.invoke(dl, string);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals(-1,id,0.001);
	}
	
	@Test
	public void existing_name_is_passed_should_return_1() {
		String string = "Hose";
		try {
			id = (Integer) method.invoke(dl, string);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals(1,id,0.001);
	}
	@Test
	public void trashname_is_passed_should_return_minus_1() {
		String string = "dfgge";
		try {
			id = (Integer) method.invoke(dl, string);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		assertEquals(-1,id,0.001);
	}
}