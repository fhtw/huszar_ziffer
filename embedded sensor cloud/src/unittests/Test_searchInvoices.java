package unittests;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import invoice.InvoiceList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ERP_classes.DataLinkLayer;

public class Test_searchInvoices {
	
	DataLinkLayer dl;
	InvoiceList invoiceList;
	String name;
	java.sql.Date fromDate;
	java.sql.Date toDate;
	double fromAmount,toAmount;

	@Before
	public void setUp() throws Exception {
		
		dl = new DataLinkLayer();
		invoiceList = new InvoiceList();
		toDate = null;
		fromDate = null;
		fromAmount = 0;
		toAmount = Integer.MAX_VALUE;
	}

	@After
	public void tearDown() throws Exception {
		dl = null;
		invoiceList = null;
		name = null;
		fromDate = null;
		toDate = null;
	}

	@Test
	public void existing_Name_passed_should_return_2_invoices() {
		
		name = "Stefan";
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);

		assertEquals(2,invoiceList.getInvoices().get(0).get_id());
		assertEquals(2,invoiceList.getInvoices().size());
	}
	@Test
	public void existing_Name_and_toAmount_passed_should_return_1_invoice() {
		
		name = "Stefan";
		toAmount = 2000;
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);
		
		assertEquals(6,invoiceList.getInvoices().get(0).get_id());
		assertEquals(1,invoiceList.getInvoices().size());
	}
	@Test
	public void nothing_passed_should_return_all_invoices() {
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);

		assertTrue(invoiceList.getInvoices().size() > 5);
	}
	@Test
	public void trashname_passed_should_return_empty_list() {
		
		name= "hasdhjas";
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);

		assertEquals(0,invoiceList.getInvoices().size());
	}
	@Test
	public void fromDate_and_existing_name_passed_should_return_2_invoices() {
		
		name= "Stefan";
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate;
		try {
			utilDate = simpleDate.parse("2014-01-01");
			fromDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);

		assertEquals(2,invoiceList.getInvoices().size());
	}
	@Test
	public void toDate_and_existing_name_passed_should_return_empty_list() {
		
		name= "Stefan";
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date utilDate;
		try {
			utilDate = simpleDate.parse("2014-01-01");
			toDate = new java.sql.Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);

		assertEquals(0,invoiceList.getInvoices().size());
	}
	@Test
	public void fromAmount_toAmount_and_name_passed_should_return_1_invoice() {
		
		name= "Stefan";
		fromAmount = 1000;
		toAmount = 2000;
		
		invoiceList = dl.searchInvoices(name, fromDate, toDate, fromAmount, toAmount);

		assertEquals(1,invoiceList.getInvoices().size());
	}
}