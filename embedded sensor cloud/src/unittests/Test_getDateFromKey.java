package unittests;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import plugins.MikroERP_Facade;
import server.QueryObject;

public class Test_getDateFromKey {

	@Test
	public void correctDate_shouldReturn_date() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("date", "2014-05-05"));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
			assertEquals(output.toString(), "2014-05-05");			
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
	public void malformattedKey_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("date", "abcdefgh"));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
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
	public void wrongKey_shouldReturn_null() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("wrong", "2014-05-05"));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
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
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("", "2014-05-05"));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
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
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject(null, "2014-05-05"));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
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
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("date", ""));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
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
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDateFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("date", null));
			
			Date output = (Date) method.invoke(facade, query, "date");
			
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
