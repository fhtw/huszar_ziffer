package invoice;

public class InvoiceElement {

	private String _name;
	private int _amount;
	private double _price;
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	
	public int get_amount() {
		return _amount;
	}
	
	public void set_amount(int _amount) {
		this._amount = _amount;
	}
	
	public double get_price() {
		return _price;
	}
	
	public void set_price(double _price) {
		this._price = _price;
	}
}
