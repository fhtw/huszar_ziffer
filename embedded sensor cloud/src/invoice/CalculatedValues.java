package invoice;

public class CalculatedValues {

	double _net;
	double _ust;
	double _gross;
	
	public double get_net() {
		return _net;
	}
	
	public void set_net(double _net) {
		this._net = _net;
	}
	
	public double get_ust() {
		return _ust;
	}
	
	public void set_ust(double _ust) {
		this._ust = _ust;
	}
	
	public double get_gross() {
		return _gross;
	}
	
	public void set_gross(double _gross) {
		this._gross = _gross;
	}	
}
