package invoice;

import java.util.ArrayList;
import java.util.List;

import invoice.Invoice;

public class InvoiceList {

	private List<Invoice> _invoiceList;

    public InvoiceList(){
    	_invoiceList = new ArrayList<Invoice>();
    }

    public void add(Invoice c){
    	_invoiceList.add(c);
    }
    
    public List<Invoice> getInvoices() {
    	return _invoiceList;
    }
}
