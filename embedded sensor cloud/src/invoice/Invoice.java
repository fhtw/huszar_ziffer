package invoice;

import java.util.ArrayList;

public class Invoice {

	private int _invoiceNumber;
	private boolean _isOutgoing;
	private String _creationDate;
	private String _expirationDate;
	private String _comment;
	private String _message;
	private String _customerName;
	private String _shippingAddress;
	private String _invoiceAddress;
	private float _ust;
	private float _gross; //brutto
	private float _net;
	private ArrayList<InvoiceElement> _articles;
	
	public int get_invoiceNumber() {
		return _invoiceNumber;
	}
	public void set_invoiceNumber(int _invoiceNumber) {
		this._invoiceNumber = _invoiceNumber;
	}
	
	public boolean is_isOutgoing() {
		return _isOutgoing;
	}
	
	public void set_isOutgoing(boolean _isOutgoing) {
		this._isOutgoing = _isOutgoing;
	}
	
	public String get_creationDate() {
		return _creationDate;
	}
	
	public void set_creationDate(String _creationDate) {
		this._creationDate = _creationDate;
	}
	
	public String get_expirationDate() {
		return _expirationDate;
	}
	
	public void set_expirationDate(String _expirationDate) {
		this._expirationDate = _expirationDate;
	}
	
	public String get_comment() {
		return _comment;
	}
	
	public void set_comment(String _comment) {
		this._comment = _comment;
	}
	
	public String get_message() {
		return _message;
	}
	
	public void set_message(String _message) {
		this._message = _message;
	}
	
	public String get_customerName() {
		return _customerName;
	}
	
	public void set_customerName(String _customerName) {
		this._customerName = _customerName;
	}
	
	public String get_shippingAddress() {
		return _shippingAddress;
	}
	
	public void set_shippingAddress(String _shippingAddress) {
		this._shippingAddress = _shippingAddress;
	}
	
	public String get_invoiceAddress() {
		return _invoiceAddress;
	}
	
	public void set_invoiceAddress(String _invoiceAddress) {
		this._invoiceAddress = _invoiceAddress;
	}
	
	public float get_ust() {
		return _ust;
	}
	
	public void set_ust(float _ust) {
		this._ust = _ust;
	}
	
	public float get_gross() {
		return _gross;
	}
	
	public void set_gross(float _gross) {
		this._gross = _gross;
	}
	
	public float get_net() {
		return _net;
	}
	
	public void set_net(float _net) {
		this._net = _net;
	}
	
	public ArrayList<InvoiceElement> get_articles() {
		return _articles;
	}
	
	public void set_articles(ArrayList<InvoiceElement> tmpInvoiceElements) {
		this._articles = tmpInvoiceElements;
	}
}
