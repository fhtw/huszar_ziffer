package contacts;

import invoice.InvoiceList;


public class Customer {

	private String _uid;
	private String _name;
	private String _title;
	private String _suffix;
	private String _surname;
	private String _lastname;	
	private String _dateOfBirth;
	private String _employedAt;
	private String _address;
	private String _invoiceAddress;
	private String _shippingAddress;
	private int _plz;
	private String _city;
	private InvoiceList _invoices;
	
	public String get_invoiceAddress() {
		return _invoiceAddress;
	}
	public void set_invoiceAddress(String _invoiceAddress) {
		this._invoiceAddress = _invoiceAddress;
	}
	public String get_shippingAddress() {
		return _shippingAddress;
	}
	public void set_shippingAddress(String _shippingAddress) {
		this._shippingAddress = _shippingAddress;
	}
	public InvoiceList get_invoices() {
		return _invoices;
	}
	public void set_invoices(InvoiceList _invoices) {
		this._invoices = _invoices;
	}
	public String get_uid() {
		return _uid;
	}
	public void set_uid(String _uid) {
		this._uid = _uid;
	}
	
	public String get_name() {
		return _name;
	}
	
	public void set_name(String _name) {
		this._name = _name;
	}
	
	public String get_address() {
		return _address;
	}

	public void set_address(String _address) {
		this._address = _address;
	}

	public int get_plz() {
		return _plz;
	}

	public void set_plz(int i) {
		this._plz = i;
	}

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}

	public String get_surname() {
		return _surname;
	}
	
	public void set_surname(String _surname) {
		this._surname = _surname;
	}
	
	public String get_lastname() {
		return _lastname;
	}
	
	public void set_lastname(String _lastname) {
		this._lastname = _lastname;
	}
	
	public String get_title() {
		return _title;
	}
	
	public void set_title(String _title) {
		this._title = _title;
	}
	
	public String get_suffix() {
		return _suffix;
	}
	
	public void set_suffix(String _suffix) {
		this._suffix = _suffix;
	}
	
	public String get_dateOfBirth() {
		return _dateOfBirth;
	}
	
	public void set_dateOfBirth(String date) {
		this._dateOfBirth = date;
	}
	
	public String get_employedAt() {
		return _employedAt;
	}
	
	public void set_employedAt(String _employedAt) {
		this._employedAt = _employedAt;
	}
}
