package contacts;

public class Company implements Contact {

	private String _uid;
	private String _name;
	private String _address;
	private String _plz;
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

	public String get_plz() {
		return _plz;
	}

	public void set_plz(String _plz) {
		this._plz = _plz;
	}

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}
}
