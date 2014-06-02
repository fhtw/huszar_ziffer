package unittests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import invoice.InvoiceElement;

import java.lang.reflect.Method;
import java.util.ArrayList;

import ERP_classes.DataLinkLayer;

public class Test_getArticles {
	
	DataLinkLayer dl;
	Method method;
	ArrayList<InvoiceElement> articles;

	@Before
	public void setUp() throws Exception {
	
		dl = new DataLinkLayer();
		articles = new ArrayList<InvoiceElement>();
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		articles = null;
	}

	@Test
	public void should_return_right_articles() {
		
		articles = dl.getArticles();
		assertEquals("Hose",articles.get(0).get_name());
		assertEquals(27,articles.get(1).get_price(),0.001);
	}
}