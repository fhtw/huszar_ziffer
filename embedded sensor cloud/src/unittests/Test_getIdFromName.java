package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ERP_classes.DataLinkLayer;

public class Test_getIdFromName {
	
	DataLinkLayer dl;
	Method method;
	ArrayList<Integer> idList;

	@Before
	public void setUp() throws Exception {
	
		dl = new DataLinkLayer();
		method = DataLinkLayer.class.getDeclaredMethod("getIdFromName", String.class);
		method.setAccessible(true);
		idList = new ArrayList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		method = null;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void nothing_is_passed_should_return_minus_1() {
		String string = "";
		try {
			idList = (ArrayList<Integer>) method.invoke(dl, string);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int expected = -1;
		assertEquals(expected,idList.get(0),0.001);
	}
	/*@SuppressWarnings("unchecked") //not posiible cause you need the connect variable in dl object
	@Test
	public void trash_is_passed_should_return_NullPointerException() {
		String string = "Stefan";
		try {
			idList = (ArrayList<Integer>) method.invoke(dl, string);
			System.out.println("idlist = "+ idList.toString());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}*/

}
