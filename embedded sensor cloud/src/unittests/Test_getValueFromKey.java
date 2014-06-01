package unittests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;


import plugins.MikroERP_Facade;
import server.QueryObject;

public class Test_getValueFromKey {
	
	@Test
	public void franz_shouldReturn_franz() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getValueFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("name", "Franz"));
			
			String output = (String) method.invoke(facade, query, "name");
			
			assertEquals(output, "Franz");			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void wrongKey_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getValueFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("wrong", "Franz"));
			
			String output = (String) method.invoke(facade, query, "name");
			
			assertNull(output);		
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}	
	}
	
	@Test
	public void nullValue_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getValueFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("name", null));
			
			String output = (String) method.invoke(facade, query, "name");
			
			assertNull(output);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
		
	@Test
	public void emptyValue_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getValueFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("name", null));
			
			String output = (String) method.invoke(facade, query, "name");
			
			assertNull(output);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void emptyKey_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getValueFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("", "Franz"));
			
			String output = (String) method.invoke(facade, query, "name");
			
			assertNull(output);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void nullKey_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getValueFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject(null, "Franz"));
			
			String output = (String) method.invoke(facade, query, "name");
			
			assertNull(output);			
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}		
	}
}
