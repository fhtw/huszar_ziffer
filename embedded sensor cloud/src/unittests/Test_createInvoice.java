package unittests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import invoice.Invoice;
import invoice.InvoiceElement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ERP_classes.BusinessLayer;

public class Test_createInvoice {
	
	Invoice _invoice;
	BusinessLayer _bl;
	ArrayList<InvoiceElement> _articles;
	InvoiceElement _invoiceElement;

	@Before
	public void setUp() throws Exception {
		
		_invoice = new Invoice();
		_bl = new BusinessLayer();
		_articles = new ArrayList<InvoiceElement>();
		_invoiceElement = new InvoiceElement();
	}

	@After
	public void tearDown() throws Exception {
		
		_invoice = null;
		_articles = null;
	}

	@Test
	public void test() {
		
		_invoiceElement.set_amount(5);
		_invoiceElement.set_price(33.99);
		
		_articles.add(_invoiceElement);
		_invoice.set_articles(_articles);
		
		_bl.createInvoice(_invoice);
		assertEquals(5*33.99/6*5,_invoice.get_net(),0.001);// delta > (actual - expected)
		assertEquals(5*33.99,_invoice.get_gross(),0.001);// delta > (actual - expected)
		assertEquals(5*33.99/6,_invoice.get_ust(),0.001);// delta > (actual - expected)
		
	}

}
