package unittests;

import static org.junit.Assert.*;
import invoice.CalculatedValues;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import ERP_classes.BusinessLayer;

public class Test_calculateValue {
	
	BusinessLayer bl;
	ArrayList<Double> prices;
	CalculatedValues values;

	@Before
	public void setUp() throws Exception {
		
			bl = new BusinessLayer();
			prices = new ArrayList<Double>();
			values = new CalculatedValues();

	}
	@Test
	public void correctIntValue_shouldReturn_correctCalculatedValues() {
		
		prices.add(new Double(55));
		
		CalculatedValues values = bl.calculateValue(prices);
		
		assertEquals(values.get_gross(), prices.get(0),0.001);//(actual,expected,delta)
		assertEquals(values.get_net(), prices.get(0)/6*5,0.001);// delta > (actual - expected)
		assertEquals(values.get_ust(), prices.get(0)/6,0.001);
	}
	@Test
	public void correctNegativeDoubleValue_shouldReturn_correctCalculatedValues() {
		
		prices.add(new Double(-55.099876));
		
		CalculatedValues values = bl.calculateValue(prices);
		
		assertEquals(values.get_gross(), prices.get(0),0.001);//(actual,expected,delta)
		assertEquals(values.get_net(), prices.get(0)/6*5,0.001);// delta > (actual - expected)
		assertEquals(values.get_ust(), prices.get(0)/6,0.001);
	}
	
	@Test
	public void correctDoubleValue_shouldReturn_correctCalculatedValues() {
		
		prices.add(new Double(152.2536));
		
		CalculatedValues values = bl.calculateValue(prices);
		
		assertEquals(values.get_gross(), prices.get(0),0.001);//(actual,expected,delta)
		assertEquals(values.get_net(), prices.get(0)/6*5,0.001);// delta > (actual - expected)
		assertEquals(values.get_ust(), prices.get(0)/6,0.001);
	}
	@Test
	public void moreDoubleValues_shouldReturn_correctCalculatedValues() {
		
		prices.add(new Double(152.2536));
		prices.add(new Double(558568.254));
		prices.add(new Double(0.36589));
		
		CalculatedValues values = bl.calculateValue(prices);
		assertEquals(prices.get(0)+prices.get(1)+prices.get(2), values.get_gross(),0.001);//(expected,actual,delta)
		assertEquals(values.get_net(), prices.get(0)/6*5+prices.get(1)/6*5+prices.get(2)/6*5,0.001);// delta > (actual - expected)
		assertEquals(values.get_ust(), prices.get(0)/6+prices.get(1)/6+prices.get(2)/6,0.001);
	}
	
	

}
