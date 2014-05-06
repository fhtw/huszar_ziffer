package invoice;

import java.util.ArrayList;

import invoice.Invoice;

public class InvoiceList {

	private ArrayList<Invoice> _invoiceList;

    public InvoiceList(){
    	_invoiceList = new ArrayList<Invoice>();
    }

    public void add(Invoice c){
    	_invoiceList.add(c);
    }
    
    public ArrayList<Invoice> getInvoices() {
    	return _invoiceList;
    }
}
