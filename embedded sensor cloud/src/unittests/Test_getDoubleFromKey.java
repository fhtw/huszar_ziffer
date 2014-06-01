package unittests;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import plugins.MikroERP_Facade;
import server.QueryObject;

public class Test_getDoubleFromKey {

	@Test
	public void two_shouldReturn_two() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("uid", "2"));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, 2d, 1*Math.ulp(2));			
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
	public void wrongKey_shouldReturn_minusOne() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("wrong", "2"));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, -1d, 1*Math.ulp(2));		
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
	public void malformattedKey_shouldReturn_minsOne() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("uid", "abc"));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, -1d, 1*Math.ulp(2));			
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
	public void nullValue_shouldReturn_minusOne() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("uid", null));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, -1d, 1*Math.ulp(2));		
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
	public void emptyValue_shouldReturn_minsOne() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("name", ""));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, -1d, 1*Math.ulp(2));			
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
	public void emptyKey_shouldReturn_minsOne() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject("", "2"));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, -1d, 1*Math.ulp(2));			
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
	public void nullKey_shouldReturn_minsOne() {
		try {
			MikroERP_Facade facade = new MikroERP_Facade();
			Method method = MikroERP_Facade.class.getDeclaredMethod("getDoubleFromKey", List.class, String.class);
			method.setAccessible(true);
			List<QueryObject> query = new ArrayList<QueryObject>();
			query.add(new QueryObject(null, "2"));
			
			double output = (Double) method.invoke(facade, query, "uid");
			
			assertEquals(output, -1d, 1*Math.ulp(2));			
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
