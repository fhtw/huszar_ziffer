package contacts;

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
	private int _plz;
	private String _city;
	
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

	public void set_plz(int _plz) {
		this._plz = _plz;
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
	
	public void set_dateOfBirth(String _dateOfBirth) {
		this._dateOfBirth = _dateOfBirth;
	}
	
	public String get_employedAt() {
		return _employedAt;
	}
	
	public void set_employedAt(String _employedAt) {
		this._employedAt = _employedAt;
	}
}
